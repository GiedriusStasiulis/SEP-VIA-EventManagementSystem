import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUImembersController implements Initializable 
{
	private Member member;
	private Member selectedMember;
	private MemberList memberList = new MemberList();
	private String filename = "MemberList.txt";
	private MemberFile memberFile = new MemberFile(filename);

	private ObservableList<Member> members = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name","E-mail");
	private ObservableList<String> emailList = FXCollections.observableArrayList();
	

	@FXML private BorderPane memberPage = new BorderPane();

	@FXML private Button btnAddMember,btnClearTextFields,btnSearchMembers,btnEditMember,btnDeleteMember,
							btnSaveMemberEditChanges,btnClearEditMemberTextFields,btnCancelEditMember,
							btnGenerateMemberEmailList,btnSelectAllMemberEmails,btnSendNewsletter;

	@FXML private TextField tfMemberID,tfEnterMemberName,tfEnterMemberEmail,tfEnterSearchKeywords,
							tfShowMemberName,tfShowMemberEmail;
	
	@FXML private Label lblMemberCount;


	@FXML private TableView<Member> memberTable; 

	@FXML private TableColumn<Member, String> tcMemberID, tcMemberName, tcMemberEmail;
	
	@FXML private ListView<Member> lvMemberSearchResults;
	@FXML private ListView<String> lvMemberEmailList;
	
	@FXML private ComboBox<String> cbMemberSearchCriteria;
	
    @FXML private HBox hboxMemberEditOptions;
    
    @FXML private CheckBox cboxSelectAllMemberEmailList;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		memberPage.setMaxHeight(Double.MAX_VALUE);
		memberPage.setMaxWidth(Double.MAX_VALUE);

		hboxMemberEditOptions.setVisible(false);
		
		cbMemberSearchCriteria.setItems(searchCriteria);
		cbMemberSearchCriteria.getSelectionModel().select(0);

		tcMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		tcMemberEmail.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));

		tcMemberName.setStyle( "-fx-alignment: CENTER;");
		tcMemberEmail.setStyle( "-fx-alignment: CENTER;");

		try 
		{
			memberTable.setItems(getList());
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		memberTable.setOnMouseClicked((MouseEvent event) -> {
		    if (event.getClickCount() == 1) {
		    	showMemberDetailsFromTable();
		    }
		});

		lvMemberSearchResults.setOnMouseClicked((MouseEvent event) -> {
		    if (event.getClickCount() == 1) {
		    	showMemberDetailsFromListView();
		    }
		});		
		
		lblMemberCount.setText(String.format("Member count: %d", memberList.size()));
	}
	
	public void showMemberDetailsFromTable() {

	    if (memberTable.getSelectionModel().getSelectedItem() != null) {
	        selectedMember = memberTable.getSelectionModel().getSelectedItem();
	        tfShowMemberName.setText(selectedMember.getName());
	        tfShowMemberEmail.setText(selectedMember.getEmail());
	    }
	}
	
	public void showMemberDetailsFromListView() {

	    if (lvMemberSearchResults.getSelectionModel().getSelectedItem() != null) {
	        selectedMember = lvMemberSearchResults.getSelectionModel().getSelectedItem();
	        System.out.println(selectedMember);
	        tfShowMemberName.setText(selectedMember.getName());
	        tfShowMemberEmail.setText(selectedMember.getEmail());
	    }
	}

	public ObservableList<Member> getList() throws FileNotFoundException, ParseException 
	{
		memberList = memberFile.readMemberTextFile();

		for (int i = 0; i < memberList.size(); i++) 
		{
			members.add(new Member(memberList.getMember(i).getName(), memberList.getMember(i).getEmail()));
		}

		return members;
	}

	@FXML
	void addMember(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException 
	{				
		String memberName = tfEnterMemberName.getText();
		String memberEmail = tfEnterMemberEmail.getText();	
			
		if(tfEnterMemberName.getText().isEmpty())
		{
			memberName = "empty";
		}
		
		if(tfEnterMemberEmail.getText().isEmpty())
		{
			memberEmail = "empty";
		}			

			member = new Member(memberName, memberEmail);		
			memberList.addMemberToList(member);		
			memberFile.writeTextFile(memberList);		
			memberTable.getItems().add(member);
			clearTextFields(event);
			
			lblMemberCount.setText(String.format("Member count: %d", memberList.size()));
	}
	
	@FXML
	void clearTextFields(ActionEvent event) 
	{
		tfEnterMemberName.setText("");
		tfEnterMemberEmail.setText("");
	}
	
	@FXML
    void searchMembers(ActionEvent event) 
	{
		ObservableList<Member> searchResults = FXCollections.observableArrayList();

		int searchCriteriaComboBoxSelection = cbMemberSearchCriteria.getSelectionModel().getSelectedIndex();
		
		String searchKeyword = tfEnterSearchKeywords.getText();
		
		tfEnterSearchKeywords.setText("");
		
		switch(searchCriteriaComboBoxSelection)
		{
			case 0:
				
				for(int i = 0; i < members.size(); i ++)
				{
					if (searchKeyword.equalsIgnoreCase(members.get(i).getName()))
					{
						searchResults.add(members.get(i));
					}
					
				}
				
				if(searchResults.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "No members found with the given search keyword: \n" + searchKeyword);
				}
				
				break;
				
			case 1:
				
				for(int i = 0; i < members.size(); i ++)
				{
					if (searchKeyword.equalsIgnoreCase(members.get(i).getEmail()))
					{
						searchResults.add(members.get(i));
					}
				}
				
				if(searchResults.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "No members found with the given search keyword: " + searchKeyword);
				}
				
				break;

				default:					
					break;			
		}	
		
		lvMemberSearchResults.setItems(searchResults);	
    }
	
	@FXML
    void editMember(ActionEvent event) 
    {
    	hboxMemberEditOptions.setVisible(true);
    	tfShowMemberName.setEditable(true);
    	tfShowMemberEmail.setEditable(true);
    }
    
    @FXML
    void cancelEditMember(ActionEvent event) 
    {
    	hboxMemberEditOptions.setVisible(false);
    	tfShowMemberName.setEditable(false);
    	tfShowMemberEmail.setEditable(false);
    }

    @FXML
    void deleteMember(ActionEvent event) throws FileNotFoundException, ParseException, ArrayIndexOutOfBoundsException
    {        	
    	if(memberTable.getSelectionModel() != null)
    	{
    		if(memberList.size() > 0)
    		{
	    		try 
	    		{
			    	int index = memberList.getMemberIndex(selectedMember);
			
			    	memberList.deleteMember(index);
			    	memberFile.writeTextFile(memberList);
			    	memberTable.getItems().remove(index);
			    	
			    	tfShowMemberName.setText("");
			    	tfShowMemberEmail.setText("");	
			    	
			    	lblMemberCount.setText(String.format("Member count: %d", memberList.size()));
	    		}
	    		catch(ArrayIndexOutOfBoundsException e)
	    		{
	    			JOptionPane.showMessageDialog(null, "Please select a member from the table to delete");
	    		}
    		}    		
    		else
    		{
    			JOptionPane.showMessageDialog(null, "No more members left to delete");
    		}
    	}    	
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Please select a member from the table to delete");
    	}    	
    }

    @FXML
    void saveEditMemberChanges(ActionEvent event) throws FileNotFoundException, ParseException 
    {
    	int index = memberList.getMemberIndex(selectedMember);   
    	
    	String newMemberName = tfShowMemberName.getText();
    	String newMemberEmail = tfShowMemberEmail.getText();
    	
    	if(tfShowMemberName.getText().isEmpty())
		{
    		newMemberName = "empty";
		}
		
		if(tfShowMemberEmail.getText().isEmpty())
		{
			newMemberEmail = "empty";
		}	
    	
    	selectedMember.setName(newMemberName);
    	selectedMember.setEmail(newMemberEmail);

    	memberList.replaceMember(index,selectedMember);    	
    	memberFile.writeTextFile(memberList);

    	memberTable.getItems().set(index, selectedMember);

    	hboxMemberEditOptions.setVisible(false);
    	tfShowMemberName.setEditable(false);
    	tfShowMemberEmail.setEditable(false);
    }

    @FXML
    void clearEditMemberTextFields(ActionEvent event) 
    {
    	tfShowMemberName.setText("");
    	tfShowMemberEmail.setText("");
    }	
    
    @FXML
    void generateMemberEmailList(ActionEvent event) 
    {    	
    	
    	emailList.clear();
    	
    	for(int i = 0; i < memberList.size(); i ++)
		{
    		emailList.add(memberList.getMember(i).getEmail());
		}
    	
    	lvMemberEmailList.setItems(emailList);
    }
    
    @FXML
    void sendNewsLetter(ActionEvent event) 
    {
    	if(cboxSelectAllMemberEmailList.isSelected())
    	{
    		JOptionPane.showMessageDialog(null, "Sent newsletter to: \n" + emailList);
    	}
    	
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Sent newsletter to: \n" + lvMemberEmailList.getSelectionModel().getSelectedItem());
    	}    	
    }   
}
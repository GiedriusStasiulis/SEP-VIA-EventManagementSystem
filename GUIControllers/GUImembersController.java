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
import javafx.scene.control.ScrollPane;
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

/**
 * Controller class for GUImembers.fxml. Handles all ActionEvent and MouseEvents
 * that occur on the members page in the application. * 
 * @author Group#2 *
 */

public class GUImembersController implements Initializable 
{
	private VIAoms viaOms = new VIAoms();
	
	private Member member;
	private Member selectedMember;
	private MemberList memberList = new MemberList();
	private static final String FILENAME = "MemberList.txt";
	private FileReaderWriter memberFile = new FileReaderWriter(FILENAME);

	private ObservableList<Member> members = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name","E-mail");
	private ObservableList<String> emailList = FXCollections.observableArrayList();	
	private ObservableList<String> unpaidMembershipList = FXCollections.observableArrayList();

	@FXML private BorderPane memberPage = new BorderPane();

	@FXML private Button btnAddMember,btnClearAddMemberTextFields,btnSearchMembers,btnEditMember,btnDeleteMember,
							btnSaveMemberEditChanges,btnClearEditMemberTextFields,btnCancelEditMember,
							btnGenerateMemberEmailList,btnSelectAllMemberEmails,btnSendNewsletter,btnGenerateListNonPaidMembership,btnSendReminder;

	@FXML private TextField tfMemberID,tfEnterMemberName,tfEnterMemberEmail,tfEnterSearchKeywords,
							tfShowMemberName,tfShowMemberEmail;
	
	@FXML private Label lblMemberCount;

	@FXML private TableView<Member> memberTable; 

	@FXML private TableColumn<Member, String> tcMemberID, tcMemberName, tcMemberEmail,tcMembershipStatus;
	
	@FXML private ListView<Member> lvMemberSearchResults;
	@FXML private ListView<String> lvMemberEmailList;
	@FXML private ListView<String> lvUnpaidMembership;
	
	@FXML private ComboBox<String> cbMemberSearchCriteria;
	
    @FXML private HBox hboxMemberEditOptions;
    
    @FXML private CheckBox cboxSelectAllMemberEmailList,cbSetMembershipPaid,cbShowMembershipStatus,
    						cbSelectAllNonPaidMembers;
    
    @FXML private ScrollPane spMemberTableScrollPane;

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
		tcMembershipStatus.setCellValueFactory(new PropertyValueFactory<Member, String>("membershipStatus"));

		tcMemberName.setStyle("-fx-alignment: CENTER;");
		tcMemberEmail.setStyle("-fx-alignment: CENTER;");
		tcMembershipStatus.setStyle("-fx-alignment: CENTER;");
		
		cbShowMembershipStatus.setDisable(true);
		
		btnEditMember.setDisable(true);
		btnDeleteMember.setDisable(true);

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
		    	btnEditMember.setDisable(false);
				btnDeleteMember.setDisable(false);
		    }
		});

		lvMemberSearchResults.setOnMouseClicked((MouseEvent event) -> {
		    if (event.getClickCount() == 1) {
		    	showMemberDetailsFromListView();
		    	btnEditMember.setDisable(false);
				btnDeleteMember.setDisable(false);
		    }
		});	
		
		lblMemberCount.setText(String.format("Member count: %d", memberList.size()));
	}
	
	public void showMemberDetailsFromTable() 
	{
	    if (memberTable.getSelectionModel().getSelectedItem() != null) {
	        selectedMember = memberTable.getSelectionModel().getSelectedItem();
	        tfShowMemberName.setText(selectedMember.getName());
	        tfShowMemberEmail.setText(selectedMember.getEmail());
	        if (selectedMember.getMembershipStatus().equals("Paid"))
	        {
	        	cbShowMembershipStatus.setSelected(true);
	        }
	        else
	        {
	        	cbShowMembershipStatus.setSelected(false);
	        }
	        
	    }
	}
	
	public void showMemberDetailsFromListView() 
	{
	    if (lvMemberSearchResults.getSelectionModel().getSelectedItem() != null) {
	        selectedMember = lvMemberSearchResults.getSelectionModel().getSelectedItem();
	        tfShowMemberName.setText(selectedMember.getName());
	        tfShowMemberEmail.setText(selectedMember.getEmail());
	    }
	}	
	
	public ObservableList<Member> getList() throws FileNotFoundException, ParseException 
	{
		memberList = viaOms.getMemberList();
		//memberList = memberFile.readMemberTextFile();

		for (int i = 0; i < memberList.size(); i++) 
		{
			members.add(new Member(memberList.getMember(i).getName(), memberList.getMember(i).getEmail(), memberList.getMember(i).getMembershipStatus()));
		}

		return members;
	}

	@FXML
	void addMember(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException 
	{				
		String memberName = tfEnterMemberName.getText();
		String memberEmail = tfEnterMemberEmail.getText();	
		String membershipStatus = "";
		
		if(tfEnterMemberName.getText().isEmpty() && tfEnterMemberEmail.getText().isEmpty())
		{
			memberName = String.format("Not defined%d", viaOms.getMemberList().size() + 1);
			memberEmail = String.format("Not defined@empty%d", viaOms.getMemberList().size() + 1);
		}		
		
		else if(tfEnterMemberName.getText().isEmpty())
		{
			memberName = String.format("Not defined%d", viaOms.getMemberList().size() + 1);
		}
		
		else if(tfEnterMemberEmail.getText().isEmpty())
		{
			memberEmail = String.format("Not defined@empty%d", viaOms.getMemberList().size() + 1);
		}
		
		if(cbSetMembershipPaid.isSelected())
		{
			membershipStatus = "Paid";
		}
		
		else
		{
			membershipStatus = "Not paid";
		}
		
				
		if(memberEmail.contains("@"))
		{					
			member = new Member(memberName, memberEmail, membershipStatus);	
			
			if(viaOms.checkForDuplicates(member))
			{
				JOptionPane.showMessageDialog(null, "Member already exists in the system!");
				clearAddMemberTextFields(event);
				cbSetMembershipPaid.setSelected(false);
			}
			
			else
			{
				viaOms.addMember(memberName, memberEmail, membershipStatus);
				memberTable.getItems().add(member);
				clearAddMemberTextFields(event);
				cbSetMembershipPaid.setSelected(false);
					
				lblMemberCount.setText(String.format("Member count: %d", viaOms.getMemberList().size()));
			}
		}
		
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
		}		
	}	

	@FXML
	void clearAddMemberTextFields(ActionEvent event) 
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
		
		searchResults.clear();
		
		switch(searchCriteriaComboBoxSelection)
		{
			case 0:
				
				for(int i = 0; i < members.size(); i ++)
				{
					if (members.get(i).getName().toLowerCase().contains(searchKeyword))
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
					if (members.get(i).getEmail().toLowerCase().contains(searchKeyword))
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
		if(memberTable.getSelectionModel() != null)
		{		
			hboxMemberEditOptions.setVisible(true);
	    	tfShowMemberName.setEditable(true);
	    	tfShowMemberEmail.setEditable(true);
	    	
	    	cbShowMembershipStatus.setDisable(false);
	    	
	    	tfShowMemberName.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
	    	tfShowMemberEmail.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
		}		
    }
    
	@FXML
	void saveEditMemberChanges(ActionEvent event) throws FileNotFoundException, ParseException 
	{
			int index = viaOms.getMemberList().getMemberIndex(selectedMember);  
	    	
	    	String newMemberName = tfShowMemberName.getText();
	    	String newMemberEmail = tfShowMemberEmail.getText();
	    	String newMembershipStatus = "";
	    	
	    	if(tfShowMemberName.getText().isEmpty())
			{
	    		newMemberName = "empty";
			}
			
			if(tfShowMemberEmail.getText().isEmpty())
			{
				newMemberEmail = "empty";
			}	
			
			if(cbShowMembershipStatus.isSelected())
			{
				newMembershipStatus = "Paid";
			}
			
			else
			{
				newMembershipStatus = "Not paid";
			}			
			
			Member tempMember = new Member(newMemberName, newMemberEmail, newMembershipStatus);
	    	
			if(newMemberEmail.contains("@"))
			{
				if(viaOms.checkForDuplicates(tempMember) == true)
				{
					JOptionPane.showMessageDialog(null, "Member already exists in the system!");
					
					hboxMemberEditOptions.setVisible(false);

					tfShowMemberName.setText("");
					tfShowMemberEmail.setText("");
					
			    	tfShowMemberName.setEditable(false);
			    	tfShowMemberEmail.setEditable(false);
			    	
			    	tfShowMemberName.setStyle("-fx-border-width: 0px ;");
			    	tfShowMemberEmail.setStyle("-fx-border-width: 0px ;");
			    	
			    	btnEditMember.setDisable(true);
			    	btnDeleteMember.setDisable(true);
			    	
			    	cbShowMembershipStatus.setDisable(true);
				}
				
				else
				{
					selectedMember.setName(newMemberName);
			    	selectedMember.setEmail(newMemberEmail);
			    	selectedMember.setMembershipStatus(newMembershipStatus);

			    	viaOms.editMember(index, selectedMember);

			    	memberTable.getItems().set(index, selectedMember);

			    	hboxMemberEditOptions.setVisible(false);
			    	tfShowMemberName.setEditable(false);
			    	tfShowMemberEmail.setEditable(false);
			    	
			    	tfShowMemberName.setStyle("-fx-border-width: 0px ;");
			    	tfShowMemberEmail.setStyle("-fx-border-width: 0px ;");
			    	
			    	cbShowMembershipStatus.setDisable(true);
				}			
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
			}
	    }
	   
	@FXML
    void clearEditMemberTextFields(ActionEvent event) 
    {
    	tfShowMemberName.setText("");
    	tfShowMemberEmail.setText("");
    }		
	
	@FXML
    void cancelEditMember(ActionEvent event) 
    {
    	hboxMemberEditOptions.setVisible(false);
    	tfShowMemberName.setEditable(false);
    	tfShowMemberEmail.setEditable(false);
    	
    	tfShowMemberName.setStyle("-fx-border-width: 0px ;");
    	tfShowMemberEmail.setStyle("-fx-border-width: 0px ;");
    	
    	btnEditMember.setDisable(true);
    	btnDeleteMember.setDisable(true);
    } 

    @FXML
    void deleteMember(ActionEvent event) throws FileNotFoundException, ParseException, ArrayIndexOutOfBoundsException
    {        
    	if(memberTable.getSelectionModel() != null)
    	{
    		if(viaOms.getMemberList().size() > 0)
    		{
	    		try 
	    		{
	    	    	String[] options = {"Delete","Cancel"}; 
	    	    	int n = JOptionPane.showOptionDialog(null,
	    	                "Are you sure you want to delete member:\n" + selectedMember + " ?",
	    	                "Delete a member",
	    	                JOptionPane.YES_NO_OPTION,
	    	                JOptionPane.QUESTION_MESSAGE,
	    	                null,
	    	                options,
	    	                options[0]);	    			
	    			
	    			if (n == JOptionPane.YES_OPTION) 
	    			{
	    				int index = viaOms.getMemberList().getMemberIndex(selectedMember);

				    	viaOms.deleteMember(index);				    
				    	
				    	memberTable.getItems().remove(index);
				    	
				    	tfShowMemberName.setText("");
				    	tfShowMemberEmail.setText("");	
				    	
				    	lblMemberCount.setText(String.format("Member count: %d", viaOms.getMemberList().size()));
	    			} 	    	
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
    void generateMemberEmailList(ActionEvent event) throws FileNotFoundException, ParseException 
    {    	    	
    	emailList.clear();
    	
    	for(int i = 0; i < viaOms.getMemberList().size(); i++)
		{
    		emailList.add(viaOms.getMemberList().getMember(i).getEmail());
		}
    	
    	lvMemberEmailList.setItems(emailList);
    }
    
    @FXML
    void sendNewsLetter(ActionEvent event) 
    {
    	if(cboxSelectAllMemberEmailList.isSelected())
    	{
    		JOptionPane.showMessageDialog(null, "Sent newsletter to: \n" + emailList);
    		cboxSelectAllMemberEmailList.setSelected(false);
    	}
    	
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Sent newsletter to: \n" + lvMemberEmailList.getSelectionModel().getSelectedItem());
    	}    	
    }   
    
    @FXML 
    void generateListNonPaidMembership(ActionEvent event) throws FileNotFoundException, ParseException
    {
    	unpaidMembershipList.clear();
    	
    	for(int i = 0; i < viaOms.generateListNonPaidMembership().size(); i++)
    	{
    		unpaidMembershipList.add(viaOms.generateListNonPaidMembership().getMember(i).getName());
    	}

    	lvUnpaidMembership.setItems(unpaidMembershipList);
    }
    
    @FXML 
    void sendReminder(ActionEvent event)
    {
    	if(cbSelectAllNonPaidMembers.isSelected())
    	{
    		JOptionPane.showMessageDialog(null, "Sent newsletter to: \n" + unpaidMembershipList);
    		cboxSelectAllMemberEmailList.setSelected(false);
    	}
    	
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Sent newsletter to: \n" + lvUnpaidMembership.getSelectionModel().getSelectedItem());
    	} 
    }
}
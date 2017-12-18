import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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
	
	private Member selectedMember;

	private ObservableList<Member> members = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name","Address","Phone number","E-mail");
	private ObservableList<String> emailList = FXCollections.observableArrayList();	
	private ObservableList<String> unpaidMembershipList = FXCollections.observableArrayList();

	@FXML private BorderPane memberPage = new BorderPane();

	@FXML private Button btnAddMember,btnClearAddMemberTextFields,btnSearchMembers,btnEditMember,btnDeleteMember,
							btnSaveMemberEditChanges,btnClearEditMemberTextFields,btnCancelEditMember,
							btnGenerateMemberEmailList,btnSelectAllMemberEmails,btnSendNewsletter,btnGenerateListNonPaidMembership,btnSendReminder;

	@FXML private TextField tfMemberID,tfEnterMemberName,tfEnterMemberEmail,tfEnterSearchKeywords,
							tfShowMemberName,tfShowMemberEmail,tfEnterMemberAddress,tfEnterMemberPhoneNumber,tfShowMemberAddress,tfShowMemberPhone;
	
	@FXML private Label lblMemberCount;

	@FXML private TableView<Member> memberTable; 

	@FXML private TableColumn<Member, String> tcMemberID, tcMemberName, tcMemberEmail,tcMembershipStatus,tcMemberAddress,tcMemberPhoneNumber;
	@FXML private TableColumn<Member, LocalDate> tcMemberSince;
	
	@FXML private ListView<Member> lvMemberSearchResults;
	@FXML private ListView<String> lvMemberEmailList;
	@FXML private ListView<String> lvUnpaidMembership;
	
	@FXML private ComboBox<String> cbMemberSearchCriteria;
	
    @FXML private HBox hboxMemberEditOptions;
    
    @FXML private CheckBox cboxSelectAllMemberEmailList,cbSetMembershipPaid,cbShowMembershipStatus,
    						cbSelectAllNonPaidMembers;
    
    @FXML private ScrollPane spMemberTableScrollPane;
    
    @FXML private DatePicker dpEnterMemberSince,dpShowMemberSince;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		memberPage.setMaxHeight(Double.MAX_VALUE);
		memberPage.setMaxWidth(Double.MAX_VALUE);

		hboxMemberEditOptions.setVisible(false);
		
		dpEnterMemberSince.setEditable(false);
		dpShowMemberSince.setEditable(false);
		
		cbMemberSearchCriteria.setItems(searchCriteria);
		cbMemberSearchCriteria.getSelectionModel().select(0);

		tcMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		tcMemberAddress.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
		tcMemberPhoneNumber.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNumber"));
		tcMemberEmail.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));
		tcMemberSince.setCellValueFactory(new PropertyValueFactory<Member, LocalDate>("memberSince"));
		tcMembershipStatus.setCellValueFactory(new PropertyValueFactory<Member, String>("membershipStatus"));

		tcMemberName.setStyle("-fx-alignment: CENTER;");
		tcMemberAddress.setStyle("-fx-alignment: CENTER;");
		tcMemberPhoneNumber.setStyle("-fx-alignment: CENTER;");
		tcMemberEmail.setStyle("-fx-alignment: CENTER;");
		tcMemberSince.setStyle("-fx-alignment: CENTER;");
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
		
		try {
			lblMemberCount.setText(String.format("Member count: %d", viaOms.getMemberList().size()));
		} catch (FileNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showMemberDetailsFromTable() 
	{
	    if (memberTable.getSelectionModel().getSelectedItem() != null) {
	        selectedMember = memberTable.getSelectionModel().getSelectedItem();
	        tfShowMemberName.setText(selectedMember.getName());
	        tfShowMemberAddress.setText(selectedMember.getAddress());
	        tfShowMemberPhone.setText(selectedMember.getPhoneNumber());
	        tfShowMemberEmail.setText(selectedMember.getEmail());
	        dpShowMemberSince.setValue(selectedMember.getMemberSince());
	        if (selectedMember.getMembershipStatus().equals("2017"))
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
	        tfShowMemberAddress.setText(selectedMember.getAddress());
	        tfShowMemberPhone.setText(selectedMember.getPhoneNumber());
	        tfShowMemberEmail.setText(selectedMember.getEmail());
	        dpShowMemberSince.setValue(selectedMember.getMemberSince());
	        
	        if (selectedMember.getMembershipStatus().equals("2017"))
	        {
	        	cbShowMembershipStatus.setSelected(true);
	        }
	        else
	        {
	        	cbShowMembershipStatus.setSelected(false);
	        }
	    }
	}	
	
	public ObservableList<Member> getList() throws FileNotFoundException, ParseException 
	{
		for (int i = 0; i < viaOms.getMemberList().size(); i++) 
		{
			members.add(new Member(viaOms.getMemberList().getMember(i).getName(),viaOms.getMemberList().getMember(i).getAddress(),viaOms.getMemberList().getMember(i).getPhoneNumber(),viaOms.getMemberList().getMember(i).getEmail(),viaOms.getMemberList().getMember(i).getMemberSince(),viaOms.getMemberList().getMember(i).getMembershipStatus()));
		}

		return members;
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}	

	@FXML
	void addMember(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException 
	{				
		String memberName = tfEnterMemberName.getText();
		String memberAddress = tfEnterMemberAddress.getText();
		String memberPhoneNumber = tfEnterMemberPhoneNumber.getText();
		String memberEmail = tfEnterMemberEmail.getText();	
		LocalDate memberSince = dpEnterMemberSince.getValue();
		String membershipStatus = "";
		
		if(tfEnterMemberAddress.getText().isEmpty())
		{
			memberAddress = "Not defined";
		}
		
		if(tfEnterMemberPhoneNumber.getText().isEmpty())
		{
			memberPhoneNumber = "Not defined";
		}
		
		if(tfEnterMemberName.getText().isEmpty() && tfEnterMemberEmail.getText().isEmpty())
		{
			memberName = String.format("Not defined%d", viaOms.getMemberList().size() + 1);
			memberEmail = "Not defined@";
		}		
		
		else if(tfEnterMemberName.getText().isEmpty())
		{
			memberName = "Not defined";
		}
		
		else if(tfEnterMemberEmail.getText().isEmpty())
		{
			memberEmail = "Not defined@";
		}
		
		if(cbSetMembershipPaid.isSelected())
		{
			membershipStatus = "2017";
		}
		
		else
		{
			membershipStatus = "Not paid";
		}
		
		if(dpEnterMemberSince.getValue() == null)
		{
			try {
				dpEnterMemberSince.setValue(LOCAL_DATE("2001-01-01"));
				memberSince = dpEnterMemberSince.getValue();
		    } catch (NullPointerException e) {
		    }
		}
		
				
		if(memberEmail.contains("@"))
		{					
			Member member = new Member(memberName, memberAddress, memberPhoneNumber, memberEmail, memberSince, membershipStatus);	
			
			if(viaOms.checkForMemberDuplicates(member))
			{
				JOptionPane.showMessageDialog(null, "Member already exists in the system!");
				cbSetMembershipPaid.setSelected(false);
			}
			
			else
			{
				viaOms.addMember(memberName, memberAddress, memberPhoneNumber, memberEmail, memberSince, membershipStatus);
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
		tfEnterMemberAddress.setText("");
		tfEnterMemberPhoneNumber.setText("");
		tfEnterMemberEmail.setText("");
		
		dpEnterMemberSince.getEditor().clear();
		dpEnterMemberSince.setValue(null);
	}
	
	@FXML
    void searchMembers(ActionEvent event) 
	{
		ObservableList<Member> searchResults = FXCollections.observableArrayList();
		int searchCriteriaComboBoxSelection = cbMemberSearchCriteria.getSelectionModel().getSelectedIndex();		
		String searchKeyword = tfEnterSearchKeywords.getText();		
		
		if(tfEnterSearchKeywords.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please enter a search key-word!");
			tfEnterSearchKeywords.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
		}
		
		else
		{
			tfEnterSearchKeywords.setText("");
			tfEnterSearchKeywords.setStyle("-fx-border-width: 0px ;");
			
			searchResults.clear();
			
			switch(searchCriteriaComboBoxSelection)
			{
				case 0:
					
					for(int i = 0; i < members.size(); i ++)
					{
						if (members.get(i).getName().toLowerCase().contains(searchKeyword.toLowerCase()))
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
						if (members.get(i).getAddress().toLowerCase().contains(searchKeyword.toLowerCase()))
						{
							searchResults.add(members.get(i));
						}
					}
					
					if(searchResults.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "No members found with the given search keyword: \n" + searchKeyword);
					}				
					
					break;
					
				case 2:
					
					for(int i = 0; i < members.size(); i ++)
					{
						if (members.get(i).getPhoneNumber().toLowerCase().contains(searchKeyword.toLowerCase()))
						{
							searchResults.add(members.get(i));
						}
					}
					
					if(searchResults.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "No members found with the given search keyword: \n" + searchKeyword);
					}
					
					break;
					
				case 3:
					
					for(int i = 0; i < members.size(); i ++)
					{
						if (members.get(i).getEmail().toLowerCase().contains(searchKeyword.toLowerCase()))
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
    }
	
	@FXML
    void editMember(ActionEvent event) 
    {    	
		if(memberTable.getSelectionModel() != null)
		{		
			hboxMemberEditOptions.setVisible(true);
	    	tfShowMemberName.setEditable(true);
	    	tfShowMemberAddress.setEditable(true);
	    	tfShowMemberPhone.setEditable(true);
	    	tfShowMemberEmail.setEditable(true);
	    	dpShowMemberSince.setDisable(false);
	    	
	    	cbShowMembershipStatus.setDisable(false);
	    	
	    	tfShowMemberName.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
	    	tfShowMemberEmail.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
	    	tfShowMemberAddress.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
	    	tfShowMemberPhone.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
	    	tfShowMemberEmail.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
	    	dpShowMemberSince.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
	    	
	    	btnDeleteMember.setDisable(true);
		}		
    }
    
	@FXML
	void saveEditMemberChanges(ActionEvent event) throws FileNotFoundException, ParseException 
	{
			int index = viaOms.getMemberList().getMemberIndex(selectedMember);  
	    	
	    	String newMemberName = tfShowMemberName.getText();
	    	String newMemberAddress = tfShowMemberAddress.getText();
	    	String newMemberPhoneNumber = tfShowMemberPhone.getText();
	    	String newMemberEmail = tfShowMemberEmail.getText();
	    	LocalDate newMemberSince = dpShowMemberSince.getValue();
	    	String newMembershipStatus = "";
	    	
	    	if(tfShowMemberName.getText().isEmpty())
			{
	    		newMemberName = "empty" + viaOms.getMemberList().size();
			}
			
			if(tfShowMemberEmail.getText().isEmpty())
			{
				newMemberEmail = "empty";
			}	
			
			if(cbShowMembershipStatus.isSelected())
			{
				newMembershipStatus = "2017";
			}
			
			if(!(cbShowMembershipStatus.isSelected()))
			{
				newMembershipStatus = "Not paid";
			}			
			
			Member tempMember = new Member(newMemberName, newMemberAddress, newMemberPhoneNumber, newMemberEmail, newMemberSince, newMembershipStatus);
	    	
			if(newMemberEmail.contains("@"))
			{
				if(viaOms.checkForMemberDuplicates(tempMember))
				{
					JOptionPane.showMessageDialog(null, "Member already exists in the system!");
					
					hboxMemberEditOptions.setVisible(false);

					tfShowMemberName.setText("");
			    	tfShowMemberAddress.setText("");
			    	tfShowMemberPhone.setText("");
			    	tfShowMemberEmail.setText("");
			    	
			    	dpShowMemberSince.getEditor().clear();
			    	dpShowMemberSince.setValue(null);
					
			    	tfShowMemberName.setEditable(false);
			    	tfShowMemberAddress.setEditable(false);
			    	tfShowMemberPhone.setEditable(false);
			    	tfShowMemberEmail.setEditable(false);
			    	
			    	dpShowMemberSince.setDisable(true);
			    	
			    	tfShowMemberName.setStyle("-fx-border-width: 0px ;");
			    	tfShowMemberAddress.setStyle("-fx-border-width: 0px ;");
			    	tfShowMemberPhone.setStyle("-fx-border-width: 0px ;");
			    	tfShowMemberEmail.setStyle("-fx-border-width: 0px ;");
			    	dpShowMemberSince.setStyle("-fx-border-width: 0px ;");
			    	
			    	btnEditMember.setDisable(true);
			    	btnDeleteMember.setDisable(true);
			    	
			    	cbShowMembershipStatus.setDisable(true);
				}
				
				else
				{
					selectedMember.setName(newMemberName);
					selectedMember.setAddress(newMemberAddress);
					selectedMember.setPhoneNumber(newMemberPhoneNumber);
			    	selectedMember.setEmail(newMemberEmail);
			    	selectedMember.setMemberSince(newMemberSince);
			    	selectedMember.setMembershipStatus(newMembershipStatus);

			    	viaOms.editMember(index, selectedMember);

			    	memberTable.getItems().set(index, selectedMember);

			    	hboxMemberEditOptions.setVisible(false);
			    	tfShowMemberName.setEditable(false);
			    	tfShowMemberEmail.setEditable(false);
			    	
			    	tfShowMemberName.setStyle("-fx-border-width: 0px ;");
			    	tfShowMemberAddress.setStyle("-fx-border-width: 0px ;");
			    	tfShowMemberPhone.setStyle("-fx-border-width: 0px ;");
			    	tfShowMemberEmail.setStyle("-fx-border-width: 0px ;");
			    	dpShowMemberSince.setStyle("-fx-border-width: 0px ;");
			    	
			    	cbShowMembershipStatus.setDisable(true);
			    	
			    	dpShowMemberSince.setDisable(true);
			    	dpShowMemberSince.getEditor().clear();
			    	dpShowMemberSince.setValue(null);
			    	
			    	btnEditMember.setDisable(true);
					btnDeleteMember.setDisable(true);
					
					memberTable.requestFocus();
					memberTable.getSelectionModel().select(index);
					memberTable.getFocusModel().focus(index);
					
					MouseEvent.fireEvent(memberTable, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
			                0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
			                true, true, true, true, true, true, null));
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
    	tfShowMemberAddress.setText("");
    	tfShowMemberPhone.setText("");
    	tfShowMemberEmail.setText("");
    	
    	dpShowMemberSince.getEditor().clear();
    	dpShowMemberSince.setValue(null);
    }		
	
	@FXML
    void cancelEditMember(ActionEvent event) 
    {
    	hboxMemberEditOptions.setVisible(false);
    	tfShowMemberName.setEditable(false);
    	tfShowMemberAddress.setEditable(false);
    	tfShowMemberPhone.setEditable(false);
    	tfShowMemberEmail.setEditable(false);
    	
    	dpShowMemberSince.setDisable(true);
    	dpShowMemberSince.setEditable(false);
    	
    	tfShowMemberName.setText("");
    	tfShowMemberAddress.setText("");
    	tfShowMemberPhone.setText("");
    	tfShowMemberEmail.setText("");
    	
    	dpShowMemberSince.getEditor().clear();
    	dpShowMemberSince.setValue(null);
    	
    	cbShowMembershipStatus.setDisable(true);
    	
    	tfShowMemberName.setStyle("-fx-border-width: 0px ;");
    	tfShowMemberAddress.setStyle("-fx-border-width: 0px ;");
    	tfShowMemberPhone.setStyle("-fx-border-width: 0px ;");
    	tfShowMemberEmail.setStyle("-fx-border-width: 0px ;");
    	dpShowMemberSince.setStyle("-fx-border-width: 0px ;");
    	
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
	    	                "Delete member",
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
				    	tfShowMemberAddress.setText("");
				    	tfShowMemberPhone.setText("");
				    	
				    	dpShowMemberSince.getEditor().clear();
				    	dpShowMemberSince.setEditable(false);
				    	
				    	btnEditMember.setDisable(true);
						btnDeleteMember.setDisable(true);
						
						cbShowMembershipStatus.setSelected(false);
				    	
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
    		JOptionPane.showMessageDialog(null, "Sent reminder to: \n" + unpaidMembershipList);
    		cboxSelectAllMemberEmailList.setSelected(false);
    	}
    	
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Sent reminder to: \n" + lvUnpaidMembership.getSelectionModel().getSelectedItem());
    	} 
    }
}
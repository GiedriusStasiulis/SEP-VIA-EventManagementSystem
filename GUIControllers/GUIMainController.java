import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.Collection;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableViewSkinBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIMainController implements Initializable
{
	public GUIMainController mainController;
	
	private MemberList memberList = new MemberList();
	private String filename = "MemberList.txt";
	private MemberFile memberFile = new MemberFile(filename);
		
	private ObservableList<Member> members = FXCollections.observableArrayList();
	
	@FXML private GUICreateEventPanelController guiCreateNewEventPanelController;
	@FXML private GUISearchEventsPanelController guiSearchEventsPanelController;

	@FXML private GUIAddSponsorPanelController guiAddSponsorPanelController;
	@FXML private GUISearchSponsorsPanelController guiSearchSponsorsPanelController;

	@FXML private GUIAddLecturerPanelController guiAddLecturerPanelController;
	@FXML private GUISearchLecturersPanelController guiSearchLecturersController;

	@FXML private GUIAddMemberPanelController guiAddMemberPanelController;
	@FXML private GUISearchMembersPanelController guiSearchMembersPanelController;

	@FXML private TabPane tabPane;

	@FXML private Tab homeTab, eventsTab, sponsorsTab, lecturersTab, membersTab;

	@FXML private Button btnOpenCreateEventPanel, btnOpenSearchEventsPanel, btnOpenEditSelectedEventPanel,
			btnOpenDeleteSelectedEventPanel;
	@FXML private Button btnOpenAddSponsorPanel, btnOpenSearchSponsorsPanel, btnOpenEditSelectedSponsorPanel,
			btnOpenDeleteSelectedSponsorPanel;
	@FXML private Button btnOpenAddLecturerPanel, btnOpenSearchLecturerPanel, btnOpenEditSelectedLecturerPanel,
			btnOpenDeleteSelectedLecturerPanel;
	@FXML private Button btnOpenAddMemberPanel, btnOpenSearchMembersPanel, btnOpenEditSelectedMemberPanel,
			btnOpenDeleteSelectedMemberPanel, btnOpenEmailListPanel;

	@FXML private ComboBox<String> cbSortEvents = new ComboBox<>();
	@FXML private ComboBox<String> cbSortSponsors = new ComboBox<>();
	@FXML private ComboBox<String> cbSortLecturers = new ComboBox<>();
	@FXML private ComboBox<String> cbSortMembers = new ComboBox<>();

	@FXML private TableView<?> tableEvents;
	@FXML private TableView<?> tableSponsors;
	@FXML private TableView<?> tableLecturers;
	@FXML private TableView<Member> tableMembers;
	
    @FXML private TableColumn<Member, String> tcMemberName;
    @FXML private TableColumn<Member, String> tcMemberEmail;	
	
	@FXML private TextField tfShowTitle, tfShowType, tfShowCategory, tfShowLecturer, tfShowDate, tfShowStartTime,
			tfShowDuration, tfShowStatus, tfShowTicketsRemaining;
	@FXML private TextField tfShowSponsorName, tfShowSponsorPhoneNumber, tfShowSponsorEmail;
	@FXML private TextField tfShowLecturerName, tfShowLecturerCategory, tfShowLecturerPhonenumber, tfShowLecturerEmail;
	@FXML private TextField tfShowMemberName;
	@FXML
	private TextField tfShowMemberEmail;
	@FXML
	private TextField tfShowMemberPaymentStatus;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ObservableList<String> sortEvent = FXCollections.observableArrayList("Title", "Type", "Category", "Lecturer",
				"Date", "Start-time", "Duration", "Status", "Tickets remaining", "None");
		ObservableList<String> sortSponsors = FXCollections.observableArrayList("Name", "Phone number", "E-mail",
				"None");
		ObservableList<String> sortLecturers = FXCollections.observableArrayList("Name", "Category", "Phone number",
				"E-mail", "None");
		ObservableList<String> sortMembers = FXCollections.observableArrayList("Name", "E-mail", "Payment status",
				"None");

		cbSortEvents.setItems(sortEvent);
		cbSortSponsors.setItems(sortSponsors);
		cbSortLecturers.setItems(sortLecturers);
		cbSortMembers.setItems(sortMembers);

		cbSortEvents.setPromptText("Click to select");
		cbSortSponsors.setPromptText("Click to select");
		cbSortLecturers.setPromptText("Click to select");
		cbSortMembers.setPromptText("Click to select");

		btnOpenEditSelectedEventPanel.setDisable(true);
		btnOpenDeleteSelectedEventPanel.setDisable(true);

		btnOpenEditSelectedSponsorPanel.setDisable(true);
		btnOpenDeleteSelectedSponsorPanel.setDisable(true);

		btnOpenEditSelectedLecturerPanel.setDisable(true);
		btnOpenDeleteSelectedLecturerPanel.setDisable(true);

		btnOpenEditSelectedMemberPanel.setDisable(true);
		btnOpenDeleteSelectedMemberPanel.setDisable(true);
		
		tcMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
	    tcMemberEmail.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));

	    try {
			tableMembers.setItems(getList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	
	public ObservableList<Member> getList() throws FileNotFoundException, ParseException
	{
	    

	    MemberList memberList = memberFile.readMemberTextFile();

	    for (int i = 0; i < memberList.size(); i++)
	    {
	        members.add(new Member(memberList.getMember(i).getName(), memberList.getMember(i).getEmail()));
	    }       

	    return members;     
	}
	
	public void sayHello()
	{
		System.out.println("Heeelo");
	}
	
	public void setText(String text)
	{
		tfShowMemberName.setText(text);
	}
	
	public void showMemberTable() throws ParseException, FileNotFoundException
	{		 		
		memberList.clearMemberList();
		tcMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
	    tcMemberEmail.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));
	
	    tableMembers.setItems(getList());	    
	    //System.out.println(getList());
	}   

	
	
	
	
	
	
	

	
 	public void openCreateEventPanel(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXML/viaGUIcreateNewEventPanel.fxml"));
			loader.setController(new GUICreateEventPanelController());
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			stage.setTitle("Create new event");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openSearchEventsPanel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXML/viaGUIsearchEventsPanel.fxml"));
			loader.setController(new GUISearchEventsPanelController());
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			stage.setTitle("Search events");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openAddSponsorPanel(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXML/viaGUIaddNewSponsorPanel.fxml"));
			loader.setController(new GUIAddSponsorPanelController());
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			stage.setTitle("Add new sponsor");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openSearchSponsorsPanel(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXML/viaGUIsearchSponsorsPanel.fxml"));
			loader.setController(new GUISearchSponsorsPanelController());
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			stage.setTitle("Search sponsors");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openAddLecturerPanel(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXML/viaGUIaddNewLecturerPanel.fxml"));
			loader.setController(new GUIAddLecturerPanelController());
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			stage.setTitle("Add new lecturer");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openSearchLecturersPanel(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXML/viaGUIsearchLecturersPanel.fxml"));
			loader.setController(new GUISearchLecturersPanelController());
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			stage.setTitle("Add new lecturer");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openAddMemberPanel(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXML/viaGUIaddNewMemberPanel.fxml"));
			loader.setController(new GUIAddMemberPanelController());
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			
			stage.setTitle("Add new lecturer");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openSearchMembersPanel(ActionEvent event) throws FileNotFoundException, ParseException 
{
		//showMemberTable();	
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXML/viaGUIsearchMembersPanel.fxml"));
			loader.setController(new GUISearchMembersPanelController());
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);

			stage.setTitle("Add new lecturer");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void openEditSelectedEventPanel() {
		//
	}

	public void openDeleteSelectedEventPanel() {
		//
	}




	}



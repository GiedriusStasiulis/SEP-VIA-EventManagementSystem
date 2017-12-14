import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Controller class for GUIevents.fxml. Handles all ActionEvent and MouseEvents
 * that occur on the events page in the application. *
 * 
 * @author Group#2 *
 */

public class GUIeventsController implements Initializable {
	private File file;

	private Event eventObj;
	private Event selectedEvent;
	private EventList eventList = new EventList();
	private MemberList memberList = new MemberList();
	private NonMemberList nonMemberList = new NonMemberList();
	private LecturerList lecturerList = new LecturerList();
	private ArrayList<String> membersRegisteredList = new ArrayList<String>();

	private static final String FILENAME = "MemberList.txt";
	private static final String FILENAMEEVENT = "EventList.txt";
	private static final String LECTURERFILENAME = "LecturerList.txt";
	private String filenameNonMembersEvent = "NonMembersForEvent.txt";
	private String filenameMembersEvent = "MemberListForEvent.txt";

	private FileReaderWriter eventFile = new FileReaderWriter(FILENAMEEVENT);
	private FileReaderWriter lecturerFile = new FileReaderWriter(LECTURERFILENAME);
	private FileReaderWriter memberFile = new FileReaderWriter(FILENAME);
	private FileReaderWriter memberEventFile = new FileReaderWriter(filenameMembersEvent);
	private FileReaderWriter nonMemberEventFile = new FileReaderWriter(filenameNonMembersEvent);

	private ObservableList<String> memberNames = FXCollections.observableArrayList();
	// private ObservableList<String> emptyList=FXCollections.observableArrayList();
	private ObservableList<String> typeChoices = FXCollections.observableArrayList("Lecture", "Seminar", "Workshop",
			"Journey");
	private ObservableList<String> categoryChoices = FXCollections.observableArrayList("Dream Interpretation", "Healing",
			"Astrology", "Reincarnation", "Karma", "Alternative Health-Care");
	private ObservableList<Event> events = FXCollections.observableArrayList();
	private ObservableList<String> lecturerNames = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Title", "Type", "Category",
			"Lecturer", "Status");

	private ObservableList<String> membersAlreadyAdded = FXCollections.observableArrayList();
	private ObservableList<String> membersAlreadyAddedtemp = FXCollections.observableArrayList();

	@FXML
	private BorderPane eventsPage = new BorderPane();

	@FXML
	private Button btnCreateEvent, btnDeleteEvent, btnCancelEditEvent, btnEditEvent, btnClearTextFields,
			btnSearchEvents, btnSaveEventEditChanges, btnClearEditEventTextFields, btnAddMemberToEvent,
			btnRemoveMemberFromEvent, btnAddCategoryToEvent, btnRemoveCategoryFromEvent, btnRemoveNonMemberFromEvent, btnAddNonMemberToEvent;

	@FXML
	private TextField tfEnterSearchKeywords, tfEventEndTime, tfShowEventPrice, tfShowEventNumberOfTickets,
			tfShowEventEndTime, tfEventNumberOfTickets, tfEventPrice, tfShowEventDiscount, tfEnterEventTitle,
			tfEventStartTime, tfEventDiscount, tfShowEventTicketsRemaining, tfShowEventStartTime, tfSelectedEvent,
			tfShowEventTitle,tfSelectedEventNonMember, tfNonMemberName, tfNonMemberPhoneNumber;

	@FXML
	private ComboBox<String> cbEventLecturer, cbEventCategory, cbEventSearchCriteria, cbShowEventCategory, cbEventType,
			cbShowEventLecturer, cbShowEventType;

	@FXML
	private TableView<Event> eventsTable;

	@FXML
	private TableColumn<Event, String> tcEventTitle, tcEventType, tcEventCategory, tcEventStartTime, tcEventEndTime,
			tcEventLecturer, tcEventStatus;

	@FXML
	private TableColumn<Event, LocalDate> tcEventStartDate, tcEventEndDate;

	@FXML
	private TableColumn<Event, Integer> tcEventNumberOfTickets, tcEventDuration;

	@FXML
	private TableColumn<Event, Double> tcEventPrice, tcEventDiscount;

	@FXML
	private Label lblEventCount;

	@FXML
	private ListView<Event> lvEventSearchResults;
	@FXML
   private ListView<NonMember> lvNonMembersAddedToEvent;
	@FXML
	private ListView<String> lvMembersToAdd, lvMembersAddedToEvent, lvCategoriesToAdd, lvCategoriesAddedToEvent;

	@FXML
	private CheckBox cbSelectAllMembersToAdd, cbSelectAllMembersToRemove, cbFinalizeEvent, cbShowEventStatus;

	@FXML
	private DatePicker dpShowEventEndDate, dpShowEventStartDate, dpEventStartDate, dpEventEndDate;

	@FXML
	private HBox hboxEventEditOptions;

	@FXML
	private TitledPane tpShowEventsPane, tpShowSearchEventsPane, tpShowCreateEventPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		eventsPage.setMaxHeight(Double.MAX_VALUE);
		eventsPage.setMaxWidth(Double.MAX_VALUE);

		hboxEventEditOptions.setVisible(false);

		hboxEventEditOptions.setVisible(false);
		
		tfShowEventTitle.setEditable(false);
		cbShowEventType.setDisable(true);
		cbShowEventCategory.setDisable(true);
		cbShowEventLecturer.setDisable(true);
		dpShowEventStartDate.setDisable(true);
		dpShowEventEndDate.setDisable(true);
		tfShowEventStartTime.setEditable(false);
		tfShowEventEndTime.setEditable(false);
		tfShowEventNumberOfTickets.setEditable(false);
		tfShowEventPrice.setEditable(false);
		tfShowEventDiscount.setEditable(false);
		cbShowEventStatus.setDisable(true);
		tfSelectedEventNonMember.setEditable(false);
		

		cbEventType.setItems(typeChoices);
		cbEventType.getSelectionModel().select(0);
		cbEventCategory.setItems(categoryChoices);
		cbEventCategory.getSelectionModel().select(0);
		cbShowEventCategory.setItems(categoryChoices);
		cbShowEventType.setItems(typeChoices);

		tcEventTitle.setCellValueFactory(new PropertyValueFactory<Event, String>("eventTitle"));
		tcEventType.setCellValueFactory(new PropertyValueFactory<Event, String>("eventType"));
		tcEventCategory.setCellValueFactory(new PropertyValueFactory<Event, String>("eventCategory"));
		tcEventLecturer.setCellValueFactory(new PropertyValueFactory<Event, String>("eventLecturer"));
		tcEventStartDate.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("eventStartDate"));
		tcEventEndDate.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("eventEndDate"));
		tcEventStartTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventStartTime"));
		tcEventEndTime.setCellValueFactory(new PropertyValueFactory<Event, String>("eventEndTime"));
		tcEventPrice.setCellValueFactory(new PropertyValueFactory<Event, Double>("eventPrice"));
		tcEventDiscount.setCellValueFactory(new PropertyValueFactory<Event, Double>("eventDiscount"));
		tcEventNumberOfTickets.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventNumberOfTickets"));
		tcEventDuration.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventDuration"));
		tcEventStatus.setCellValueFactory(new PropertyValueFactory<Event, String>("eventStatus"));

		tcEventType.setStyle("-fx-alignment: CENTER;");
		tcEventCategory.setStyle("-fx-alignment: CENTER;");
		tcEventTitle.setStyle("-fx-alignment: CENTER;");
		tcEventStartDate.setStyle("-fx-alignment: CENTER;");
		tcEventEndDate.setStyle("-fx-alignment: CENTER;");
		tcEventStartTime.setStyle("-fx-alignment: CENTER;");
		tcEventEndTime.setStyle("-fx-alignment: CENTER;");
		tcEventPrice.setStyle("-fx-alignment: CENTER;");
		tcEventDiscount.setStyle("-fx-alignment: CENTER;");
		tcEventNumberOfTickets.setStyle("-fx-alignment: CENTER;");
		tcEventLecturer.setStyle("-fx-alignment: CENTER;");
		tcEventDuration.setStyle("-fx-alignment: CENTER;");
		tcEventStatus.setStyle("-fx-alignment: CENTER;");

		cbEventSearchCriteria.setItems(searchCriteria);
		cbEventSearchCriteria.getSelectionModel().select(0);

		btnEditEvent.setDisable(true);
		btnDeleteEvent.setDisable(true);

		try {
			cbEventLecturer.setItems(getLecturerNames());
			cbEventLecturer.getSelectionModel().select(0);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			eventsTable.setItems(getList());
			// System.out.println(eventList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		eventsTable.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
				showEventDetailsFromTable();
				tpShowEventsPane.setExpanded(true);
				btnEditEvent.setDisable(false);
				btnDeleteEvent.setDisable(false);

				try {

					generateAllMemberNameList();
				} catch (FileNotFoundException | ParseException e) {
					e.printStackTrace();
				}
				;
			}
		});

		/*lvMembersToAdd.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {

			}
		});*/

		lvEventSearchResults.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
				showEventDetailsFromListView();
				tpShowEventsPane.setExpanded(true);
				btnEditEvent.setDisable(false);
				btnDeleteEvent.setDisable(false);

			}
		});
		
		lblEventCount.setText(String.format("Event count: %d", eventList.size()));
	}

	public void showEventDetailsFromTable() {
		if (eventsTable.getSelectionModel().getSelectedItem() != null) {
			selectedEvent = eventsTable.getSelectionModel().getSelectedItem();

			tfShowEventTitle.setText(selectedEvent.getEventTitle());
			cbShowEventType.getSelectionModel().select(selectedEvent.getEventType());
			cbShowEventCategory.getSelectionModel().select(selectedEvent.getEventCategory());
			cbShowEventLecturer.getSelectionModel().select(selectedEvent.getEventLecturer());
			dpShowEventStartDate.setValue(selectedEvent.getEventStartDate());
			dpShowEventEndDate.setValue(selectedEvent.getEventEndDate());
			tfShowEventStartTime.setText(selectedEvent.getEventStartTime());
			tfShowEventEndTime.setText(selectedEvent.getEventEndTime());
			tfShowEventNumberOfTickets.setText(Integer.toString(selectedEvent.getEventNumberOfTickets()));
			tfShowEventPrice.setText(Double.toString(selectedEvent.getEventPrice()));
			tfShowEventDiscount.setText(Double.toString(selectedEvent.getEventDiscount()));
			tfSelectedEventNonMember.setText(selectedEvent.getEventTitle());

			if (selectedEvent.getEventStatus().equals("Finalized")) {
				cbShowEventStatus.setSelected(true);
			}

			else {
				cbShowEventStatus.setSelected(false);
			}
		}
	}

	public void showEventDetailsFromListView() {
		if (lvEventSearchResults.getSelectionModel().getSelectedItem() != null) {
			selectedEvent = lvEventSearchResults.getSelectionModel().getSelectedItem();

			tfShowEventTitle.setText(selectedEvent.getEventTitle());
			cbShowEventType.getSelectionModel().select(selectedEvent.getEventType());
			cbShowEventCategory.getSelectionModel().select(selectedEvent.getEventCategory());
			cbShowEventLecturer.getSelectionModel().select(selectedEvent.getEventLecturer());
			dpShowEventStartDate.setValue(selectedEvent.getEventStartDate());
			dpShowEventEndDate.setValue(selectedEvent.getEventEndDate());
			tfShowEventStartTime.setText(selectedEvent.getEventStartTime());
			tfShowEventEndTime.setText(selectedEvent.getEventEndTime());
			tfShowEventNumberOfTickets.setText(Integer.toString(selectedEvent.getEventNumberOfTickets()));
			tfShowEventPrice.setText(Double.toString(selectedEvent.getEventPrice()));
			tfShowEventDiscount.setText(Double.toString(selectedEvent.getEventDiscount()));
			tfSelectedEventNonMember.setText(selectedEvent.getEventTitle());
			

			if (selectedEvent.getEventStatus().equals("Finalized")) {
				cbShowEventStatus.setSelected(true);
			}

			else {
				cbShowEventStatus.setSelected(false);
			}
		}
	}
	
	public ObservableList<Event> getList() throws ParseException, IOException {
		eventList = eventFile.readEventsTextFile();

		for (int i = 0; i < eventList.size(); i++) {
			events.add(new Event(eventList.getEvent(i).getEventTitle(), eventList.getEvent(i).getEventType(),
					eventList.getEvent(i).getEventCategory(), eventList.getEvent(i).getEventLecturer(),
					eventList.getEvent(i).getEventStartDate(), eventList.getEvent(i).getEventStartTime(),
					eventList.getEvent(i).getEventEndDate(), eventList.getEvent(i).getEventEndTime(),
					eventList.getEvent(i).getEventNumberOfTickets(), eventList.getEvent(i).getEventPrice(),
					eventList.getEvent(i).getEventDiscount(), eventList.getEvent(i).getEventStatus()));
		}
		return events;
	}

	public ObservableList<String> getLecturerNames() throws FileNotFoundException, ParseException {
		lecturerList = lecturerFile.readLecturerTextFile();

		for (int i = 0; i < lecturerList.size(); i++) {
			lecturerNames.add(lecturerList.getLecturer(i).getLecturerName());
		}

		return lecturerNames;
	}

	public ObservableList<String> getMembersAdded() throws FileNotFoundException, ParseException 
	{
		membersAlreadyAddedtemp.clear();

		memberEventFile.setFile(eventsTable.getSelectionModel().getSelectedItem().getEventTitle().toString()
				+ "MemberListForEvent.txt");

		membersRegisteredList = memberEventFile.readEventMemberFile();

		for (int i = 0; i < membersRegisteredList.size(); i++) {
			membersAlreadyAddedtemp.add(membersRegisteredList.get(i));
		}
		return membersAlreadyAddedtemp;
	}	
	
	@FXML
	public void clearMemberListView() {

	}

	@FXML
	public void generateAllMemberNameList() throws FileNotFoundException, ParseException {

		memberNames.clear();

		memberList = memberFile.readMemberTextFile();

		for (int i = 0; i < memberList.size(); i++) 
		{
			memberNames.add(memberList.getMember(i).getName());

		}
		
		lvMembersToAdd.setItems(memberNames);

		int size = memberList.size();
		for (int i = 0; i < size; i++) 
		{
			memberList.deleteMember(0);
		}
	}

	// @FXML
	/*
	 * void showRegisteredMemberList() {
	 * lvMemberList.setItems(generateRegisteredMemberList()); }
	 */
	
	@FXML
	void createEvent(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException 
	{
		String eventTitle = tfEnterEventTitle.getText();

		String eventType = cbEventType.getValue();
		String eventCategory = cbEventCategory.getValue();
		String eventLecturer = cbEventLecturer.getValue();

		// DatePicker datePicker = new DatePicker();
		LocalDate localStartDate = dpEventStartDate.getValue();
		LocalDate localEndDate = dpEventEndDate.getValue();

		String startTime = tfEventStartTime.getText();
		String endTime = tfEventEndTime.getText();
		double price = Double.parseDouble(tfEventPrice.getText());
		double discount = Double.parseDouble(tfEventDiscount.getText());
		int maxMembers = Integer.parseInt(tfEventNumberOfTickets.getText());

		String finalized = "";

		if (cbFinalizeEvent.isSelected()) {
			finalized = "Finalized";
		}

		else {
			finalized = "Not finalized";
		}

		eventObj = new Event(eventTitle, eventType, eventCategory, eventLecturer, localStartDate, startTime,
				localEndDate, endTime, maxMembers, price, discount, finalized);
		
		if(eventList.checkForDuplicates(eventList, eventObj))
		{
			JOptionPane.showMessageDialog(null, "Event already exists in the system!");
			clearCreateEventTextFields(event);
		}
		
		else
		{
			eventList.addEventToList(eventObj);
			eventFile.writeEventTextFile(eventList);
			eventsTable.getItems().add(eventObj);

			
			lblEventCount.setText(String.format("Event count: %d", eventList.size()));
			////////// create a file to store lecturer names for this event///////
	/*
			String filename = eventTitle + "LecturerListForEvent.txt";
			File file = new File(filename);
			file.createNewFile();
			/////////////////////////////////////////////////////////////////////
	*/
			/////////// Create a file to store members for this event///////////
			String filename2 = eventTitle + "MemberListForEvent.txt";
			String filename3 =eventTitle + "NonMembersForEvent.txt";
			File file2 = new File(filename2);
			file2.createNewFile();
			
			File file3 = new File(filename3);
			file3.createNewFile();

			clearCreateEventTextFields(event);
		}		
	}

	/*
	 * public ObservableList<String> getLecturerNames() throws
	 * FileNotFoundException,ParseException { lecturerList =
	 * lecturerFile.readLecturerTextFile();
	 * 
	 * for (int i=0; i<lecturerList.size(); i ++) {
	 * lecturerNames.add(lecturerList.getLecturer(i).getName()); } return
	 * lecturerNames; }
	 */

	@FXML
	void clearCreateEventTextFields(ActionEvent event) 
	{
		tfEnterEventTitle.setText("");
		cbEventType.getSelectionModel().select(0);
		cbEventCategory.getSelectionModel().select(0);
		cbShowEventLecturer.getSelectionModel().select(0);
		dpEventStartDate.getEditor().clear();
		dpEventStartDate.setValue(null);
		dpEventEndDate.getEditor().clear();
		dpEventEndDate.setValue(null);
		tfEventStartTime.setText("");
		tfEventEndTime.setText("");
		tfEventPrice.setText("");
		tfEventDiscount.setText("");
		tfEventNumberOfTickets.setText("");
		cbFinalizeEvent.setSelected(false);
	}

	@FXML
	void searchEvents(ActionEvent event) 
	{
		System.out.println("Search event");
		ObservableList<Event> searchResults = FXCollections.observableArrayList();
		int searchCriteriaComboBoxSelection = cbEventSearchCriteria.getSelectionModel().getSelectedIndex();
		String searchKeyword = tfEnterSearchKeywords.getText();
		tfEnterSearchKeywords.setText("");

		switch (searchCriteriaComboBoxSelection) {
		case 0:

			for (int i = 0; i < events.size(); i++) {
				if (events.get(i).getEventTitle().toLowerCase().contains(searchKeyword)) {
					searchResults.add(events.get(i));
				}
			}

			if (searchResults.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}

			break;

		case 1:

			for (int i = 0; i < events.size(); i++) {
				if (events.get(i).getEventType().toLowerCase().contains(searchKeyword)) {
					searchResults.add(events.get(i));
				}
			}

			if (searchResults.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}

			break;

		case 2:

			for (int i = 0; i < events.size(); i++) {
				if (events.get(i).getEventCategory().toLowerCase().contains(searchKeyword)) {
					searchResults.add(events.get(i));
				}
			}

			if (searchResults.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}

			break;

		case 3:

			for (int i = 0; i < events.size(); i++) {
				if (events.get(i).getEventLecturer().toLowerCase().contains(searchKeyword)) {
					searchResults.add(events.get(i));
				}
			}

			if (searchResults.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}

			break;

		case 4:

			for (int i = 0; i < events.size(); i++) {
				if (events.get(i).getEventStatus().toLowerCase().contains(searchKeyword)) {
					searchResults.add(events.get(i));
				}
			}

			if (searchResults.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}

			break;
		}

		lvEventSearchResults.setItems(searchResults);
	}

	@FXML
	void editEvent(ActionEvent event) {
		if (eventsTable.getSelectionModel() != null) {
			System.out.println(eventsTable.getSelectionModel());

			hboxEventEditOptions.setVisible(true);
			tfShowEventTitle.setEditable(true);
			cbShowEventType.setDisable(false);
			cbShowEventCategory.setDisable(false);
			cbShowEventLecturer.setDisable(false);
			dpShowEventStartDate.setDisable(false);
			dpShowEventEndDate.setDisable(false);
			tfShowEventStartTime.setEditable(true);
			tfShowEventEndTime.setEditable(true);
			tfShowEventNumberOfTickets.setEditable(true);
			tfShowEventPrice.setEditable(true);
			tfShowEventDiscount.setEditable(true);
			cbShowEventStatus.setDisable(false);

			// tfShowMemberName.setStyle("-fx-border-color: orange ; -fx-border-width: 1px
			// ;");
			// tfShowMemberEmail.setStyle("-fx-border-color: orange ; -fx-border-width: 1px
			// ;");
		}
	}

	@FXML
	void saveEditEventChanges(ActionEvent event) throws ParseException, IOException 
	{
		int index = eventsTable.getSelectionModel().getSelectedIndex();

		String newEventTitle = tfShowEventTitle.getText();

		String newEventType = cbShowEventType.getValue();
		String newEventCategory = cbShowEventCategory.getValue();
		String newEventLecturer = cbShowEventLecturer.getValue();

		// DatePicker datePicker = new DatePicker();
		LocalDate newLocalStartDate = dpShowEventStartDate.getValue();
		LocalDate newLocalEndDate = dpShowEventEndDate.getValue();

		String newStartTime = tfShowEventStartTime.getText();
		String newEndTime = tfShowEventEndTime.getText();
		double newPrice = Double.parseDouble(tfShowEventPrice.getText());
		double newDiscount = Double.parseDouble(tfShowEventDiscount.getText());
		int newMaxMembers = Integer.parseInt(tfShowEventNumberOfTickets.getText());

		String newFinalized = "";

		if (cbShowEventStatus.isSelected()) {
			newFinalized = "Finalized";
		}

		else {
			newFinalized = "Not finalized";
		}
		
		Event tempEvent = new Event(newEventTitle,newEventType,newEventCategory,newEventLecturer,newLocalStartDate,newStartTime,
									newLocalEndDate,newEndTime,newMaxMembers,newPrice,newDiscount,newFinalized);	
		
		if(eventList.checkForDuplicates(eventList, tempEvent))
		{
			JOptionPane.showMessageDialog(null, "Event already exists in the system!");
			
			hboxEventEditOptions.setVisible(false);
			tfShowEventTitle.setEditable(false);
			cbShowEventType.setDisable(false);
			cbShowEventCategory.setDisable(true);
			cbShowEventLecturer.setDisable(true);
			dpShowEventStartDate.setDisable(true);
			dpShowEventEndDate.setDisable(true);
			tfShowEventStartTime.setEditable(false);
			tfShowEventEndTime.setEditable(false);
			tfShowEventNumberOfTickets.setEditable(false);
			tfShowEventPrice.setEditable(false);
			tfShowEventDiscount.setEditable(false);
			cbShowEventStatus.setDisable(true);
			
			tfShowEventTitle.setText("");
			cbShowEventType.getSelectionModel().select(0);
			cbShowEventCategory.getSelectionModel().select(0);
			cbShowEventLecturer.getSelectionModel().select(0);
			dpShowEventStartDate.getEditor().clear();
			dpShowEventStartDate.setValue(null);
			dpShowEventEndDate.getEditor().clear();
			dpShowEventEndDate.setValue(null);
			tfShowEventStartTime.setText("");
			tfShowEventEndTime.setText("");
			tfShowEventNumberOfTickets.setText("");
			tfShowEventPrice.setText("");
			tfShowEventDiscount.setText("");
			
			btnEditEvent.setDisable(true);
			btnDeleteEvent.setDisable(true);
		}
		
		else
		{
			selectedEvent.setEventTitle(newEventTitle);
			selectedEvent.setEventType(newEventType);
			selectedEvent.setEventCategory(newEventCategory);
			selectedEvent.setEventLecturer(newEventLecturer);
			selectedEvent.setEventStartDate(newLocalStartDate);
			selectedEvent.setEventEndDate(newLocalEndDate);
			selectedEvent.setEventStartTime(newStartTime);
			selectedEvent.setEventEndTime(newEndTime);
			selectedEvent.setEventPrice(newPrice);
			selectedEvent.setEventDiscount(newDiscount);
			selectedEvent.setEventNumberOfTickets(newMaxMembers);
			selectedEvent.setEventStatus(newFinalized);

			eventList.replaceEvent(index, eventsTable.getSelectionModel().getSelectedItem());
			eventFile.writeEventTextFile(eventList);

			eventsTable.getItems().set(index, eventsTable.getSelectionModel().getSelectedItem());

			hboxEventEditOptions.setVisible(false);
			tfShowEventTitle.setEditable(false);
			cbShowEventType.setDisable(false);
			cbShowEventCategory.setDisable(true);
			cbShowEventLecturer.setDisable(true);
			dpShowEventStartDate.setDisable(true);
			dpShowEventEndDate.setDisable(true);
			tfShowEventStartTime.setEditable(false);
			tfShowEventEndTime.setEditable(false);
			tfShowEventNumberOfTickets.setEditable(false);
			tfShowEventPrice.setEditable(false);
			tfShowEventDiscount.setEditable(false);
			cbShowEventStatus.setDisable(true);
			
			clearEditEventTextFields(event);
			
			btnEditEvent.setDisable(true);
			btnDeleteEvent.setDisable(true);
		}	
	}

	@FXML
	void clearEditEventTextFields(ActionEvent event) 
	{
		tfShowEventTitle.setText("");
		cbShowEventType.getSelectionModel().select(0);
		cbShowEventCategory.getSelectionModel().select(0);
		cbShowEventLecturer.getSelectionModel().select(0);
		dpShowEventStartDate.getEditor().clear();
		dpShowEventStartDate.setValue(null);
		dpShowEventEndDate.getEditor().clear();
		dpShowEventEndDate.setValue(null);
		tfShowEventStartTime.setText("");
		tfShowEventEndTime.setText("");
		tfShowEventNumberOfTickets.setText("");
		tfShowEventPrice.setText("");
		tfShowEventDiscount.setText("");
	}

	@FXML
	void cancelEditEvent(ActionEvent event) 
	{
		hboxEventEditOptions.setVisible(false);
		tfShowEventTitle.setEditable(false);
		cbShowEventType.setDisable(false);
		cbShowEventCategory.setDisable(true);
		cbShowEventLecturer.setDisable(true);
		dpShowEventStartDate.setDisable(true);
		dpShowEventEndDate.setDisable(true);
		tfShowEventStartTime.setEditable(false);
		tfShowEventEndTime.setEditable(false);
		tfShowEventNumberOfTickets.setEditable(false);
		tfShowEventPrice.setEditable(false);
		tfShowEventDiscount.setEditable(false);
		cbShowEventStatus.setDisable(true);
		
		tfShowEventTitle.setText("");
		cbShowEventType.getSelectionModel().select(0);
		cbShowEventCategory.getSelectionModel().select(0);
		cbShowEventLecturer.getSelectionModel().select(0);
		dpShowEventStartDate.getEditor().clear();
		dpShowEventStartDate.setValue(null);
		dpShowEventEndDate.getEditor().clear();
		dpShowEventEndDate.setValue(null);
		tfShowEventStartTime.setText("");
		tfShowEventEndTime.setText("");
		tfShowEventNumberOfTickets.setText("");
		tfShowEventPrice.setText("");
		tfShowEventDiscount.setText("");
		
		btnEditEvent.setDisable(true);
		btnDeleteEvent.setDisable(true);
	}

	@FXML
	void deleteEvent(ActionEvent event) throws FileNotFoundException 
	{
		if (eventsTable.getSelectionModel() != null) 
		{
			if (eventList.size() > 0) 
			{
				try {
					String[] options = { "Delete", "Cancel" };
					int n = JOptionPane.showOptionDialog(null,
							"Are you sure you want to delete event:\n" + selectedEvent + " ?", "Delete a member",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					if (n == JOptionPane.YES_OPTION) {

						int index = eventList.getEventIndex(selectedEvent);

						eventList.deleteEvent(index);
						eventFile.writeEventTextFile(eventList);
						eventsTable.getItems().remove(index);
						lblEventCount.setText(String.format("Event count: %d", eventList.size()));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					// e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please select an event from the table to delete");
				}
			} else {
				JOptionPane.showMessageDialog(null, "No more events left to delete");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please select an event from the table to delete");
		}
	}

	@FXML
	void addMemberToEvent(ActionEvent event) throws FileNotFoundException {

		if (checkForDuplicates(membersRegisteredList, lvMembersToAdd.getSelectionModel().getSelectedItem())) {
			JOptionPane.showMessageDialog(null, "Member is already added to event!");
		}

		else {
			eventsTable.getSelectionModel().getSelectedItem()
					.addMemberToEvent(lvMembersToAdd.getSelectionModel().getSelectedItem());
			// membersRegisteredList.clear();
			membersRegisteredList.add(lvMembersToAdd.getSelectionModel().getSelectedItem());

			memberEventFile.writeEventMemberFile(membersRegisteredList,
					eventsTable.getSelectionModel().getSelectedItem().getEventTitle());

			lvMembersAddedToEvent.getItems().add(lvMembersToAdd.getSelectionModel().getSelectedItem());
		}
	}

	public boolean checkForDuplicates(ArrayList<String> memberList, String member) {
		boolean status = false;

		for (int i = 0; i < membersRegisteredList.size(); i++) {
			if (member.equals(membersRegisteredList.get(i)))
				return true;
		}

		return status;
	}

	@FXML
	void removeMemberFromEvent(ActionEvent event) throws FileNotFoundException {
		System.out.println("Remove member from event");

		if (lvMembersAddedToEvent.getSelectionModel() != null) {
			if (membersRegisteredList.size() > 0) {
				int index = lvMembersAddedToEvent.getSelectionModel().getSelectedIndex();
				membersRegisteredList.remove(index);
				memberEventFile.writeEventMemberFile(membersRegisteredList,
						eventsTable.getSelectionModel().getSelectedItem().getEventTitle());
				lvMembersAddedToEvent.getItems().remove(index);
			}
		}
	}

	@FXML
	void addCategoryToEvent(ActionEvent event) {
		System.out.println("Add lecturer to event");
	}

	@FXML
	void removeCategoryFromEvent(ActionEvent event) {
		System.out.println("Remove lecturer from event");
	}
	 @FXML
    void addNonMemberToEvent(ActionEvent event) throws FileNotFoundException {
	    lvNonMembersAddedToEvent.getItems().clear();
	      NonMember nonMember=new NonMember(tfNonMemberName.getText(),tfNonMemberPhoneNumber.getText());
	      /*nonMemberList.addNonMemberToList(nonMember);
	      for(int i=0;i<nonMemberList.size();i++)
	      {
	      lvNonMembersAddedToEvent.getItems().add(nonMemberList.getNonMember(i).toString());
	      }
	      */
	      
	      String nonMemberName = tfNonMemberName.getText();
	      String nonMemberPhoneNumber = tfNonMemberPhoneNumber.getText(); 
	      
	      if(tfNonMemberName.getText().isEmpty() && tfNonMemberPhoneNumber.getText().isEmpty())
	      {
	         nonMemberName = String.format("empty%d", nonMemberList.size() + 1);
	         nonMemberPhoneNumber = String.format("empty@empty%d", nonMemberList.size() + 1);
	      }     
	      
	      else if(tfNonMemberName.getText().isEmpty())
	      {
	         nonMemberName = String.format("empty%d", nonMemberList.size() + 1);
	      }
	      
	      else if(tfNonMemberPhoneNumber.getText().isEmpty())
	      {
	         nonMemberPhoneNumber = String.format("empty@empty%d", nonMemberList.size() + 1);
	      }
	      
	         
	         
	         if(nonMemberList.checkForDuplicates(nonMemberList, nonMember))
	         {
	            JOptionPane.showMessageDialog(null, "NonMember already exists in the system!");
	            
	         }
	         
	         else
	         {
	            nonMemberList.addNonMemberToList(nonMember);
	            
	            nonMemberEventFile.writeNonMemberTextFile(nonMemberList);     
	            
	            for(int i=0;i<nonMemberList.size();i++)
	            {
	               lvNonMembersAddedToEvent.getItems().add(nonMemberList.getNonMember(i));
	            }  
	           
	         }
	         tfNonMemberName.clear();
	         tfNonMemberPhoneNumber.clear();

    }

    @FXML
    void removeNonMemberFromEvent(ActionEvent event) {

    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Controller class for GUIevents.fxml. Handles all ActionEvent and MouseEvents
 * that occur on the events page in the application. *
 * 
 * @author Group#2 *
 */

public class GUIeventsController implements Initializable 
{
	private VIAoms viaOms = new VIAoms();

	private Event selectedEvent;
	private EventList eventList = new EventList();
	private MemberList memberList = new MemberList();
	private ArrayList<String> membersRegisteredList = new ArrayList<String>();
	private ArrayList<String> lecturersAlreadyRegistered = new ArrayList<String>();
	private ArrayList<String> nonMembers = new ArrayList<String>();
	private ArrayList<String> eventCategories = new ArrayList<String>();

	private ObservableList<String> memberNames = FXCollections.observableArrayList();
	private ObservableList<String> typeChoices = FXCollections.observableArrayList("Lecture", "Seminar", "Workshop",
			"Journey");
	private ObservableList<String> categoryChoices = FXCollections.observableArrayList("Dream Interpretation",
			"Healing", "Astrology", "Reincarnation", "Karma", "Alternative Health-Care");
	private ObservableList<Event> events = FXCollections.observableArrayList();
	private ObservableList<String> lecturerNames = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Title", "Type", "Category",
			"Lecturer", "Status");
	private ObservableList<String> membersAlreadyAdded = FXCollections.observableArrayList();
	private ObservableList<String> nonMembersAlreadyAdded = FXCollections.observableArrayList();

	private ObservableList<String> categoriesAdded = FXCollections.observableArrayList();
	private ObservableList<String> lecturersAdded = FXCollections.observableArrayList();


	@FXML
	private BorderPane eventsPage = new BorderPane();

	@FXML
	private Button btnCreateEvent, btnDeleteEvent, btnCancelEditEvent, btnEditEvent, btnClearTextFields,
			btnSearchEvents, btnSaveEventEditChanges, btnClearEditEventTextFields, btnAddMemberToEvent,
			btnRemoveMemberFromEvent, btnAddCategoryToEvent, btnRemoveCategoryFromEvent, btnRemoveNonMemberFromEvent,
			btnAddNonMemberToEvent, btnAddLecturerToEvent, btnRemoveLecturerFromEvent;

	@FXML
	private TextField tfEnterSearchKeywords, tfEventEndTime, tfShowEventPrice, tfShowEventNumberOfTickets,
			tfShowEventEndTime, tfEventNumberOfTickets, tfEventPrice, tfShowEventDiscount, tfEnterEventTitle,
			tfEventStartTime, tfEventDiscount, tfShowEventTicketsRemaining, tfShowEventStartTime, tfSelectedEvent1,
			tfSelectedEvent2, tfShowEventTitle, tfSelectedEventNonMember, tfNonMemberName, tfNonMemberPhoneNumber,
			tfSelectedEventCategory, tfSelectedEvent3, tfSelectedEventCategory2,tfMainEventLecturer;

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
	private TableColumn<Event, Integer> tcEventNumberOfTickets, tcEventDuration, tcTicketsRemaining;

	@FXML
	private TableColumn<Event, Double> tcEventPrice, tcEventDiscount;

	@FXML
	private Label lblEventCount, lblEventLecturer, lblEventStartDate, lblEventEndDate, lblEventStartTime,
			lblEventEndTime, lblEventNumberOfTickets, lblEventPrice, lblEventDiscount, lblMembersAdded, lblNonMembersAdded, lblLecturersAdded;

	@FXML
	private ListView<Event> lvEventSearchResults;
	@FXML
	private ListView<String> lvNonMembersAddedToEvent;
	@FXML
	private ListView<String> lvMembersToAdd, lvMembersAddedToEvent, lvCategoriesToAdd, lvCategoriesAddedToEvent, lvLecturersToAdd, lvLecturersAlreadyAdded;

	@FXML
	private CheckBox cbSelectAllMembersToAdd, cbSelectAllMembersToRemove, cbShowEventStatus;

	@FXML
	private DatePicker dpShowEventEndDate, dpShowEventStartDate, dpEventStartDate, dpEventEndDate;

	@FXML
	private HBox hboxEventEditOptions;

	@FXML
	private TitledPane tpShowEventsPane, tpShowSearchEventsPane, tpShowCreateEventPane, tpAddEventCategory,
			tpAddMembersToEvent, tpAddNonMembersToEvent;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		eventsPage.setMaxHeight(Double.MAX_VALUE);
		eventsPage.setMaxWidth(Double.MAX_VALUE);

		hboxEventEditOptions.setVisible(false);		
		dpEventStartDate.setEditable(false);
		dpEventEndDate.setEditable(false);		
		tpShowEventsPane.setDisable(true);
		tpAddEventCategory.setDisable(true);
		tpAddMembersToEvent.setDisable(true);
		tpAddNonMembersToEvent.setDisable(true);
		cbEventLecturer.setVisible(false);
		dpEventStartDate.setVisible(false);
		dpEventEndDate.setVisible(false);
		tfEventStartTime.setVisible(false);
		tfEventEndTime.setVisible(false);
		tfEventEndTime.setVisible(false);
		tfEventNumberOfTickets.setVisible(false);
		tfEventPrice.setVisible(false);
		tfEventDiscount.setVisible(false);
		lblEventLecturer.setVisible(false);
		lblEventStartDate.setVisible(false);
		lblEventEndDate.setVisible(false);
		lblEventStartTime.setVisible(false);
		lblEventEndTime.setVisible(false);
		lblEventNumberOfTickets.setVisible(false);
		lblEventPrice.setVisible(false);
		lblEventDiscount.setVisible(false);
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
		tcTicketsRemaining.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventTicketsRemaining"));
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
		tcTicketsRemaining.setStyle("-fx-alignment: CENTER;");
		tcEventLecturer.setStyle("-fx-alignment: CENTER;");
		tcEventDuration.setStyle("-fx-alignment: CENTER;");
		tcEventStatus.setStyle("-fx-alignment: CENTER;");

		cbEventSearchCriteria.setItems(searchCriteria);
		cbEventSearchCriteria.getSelectionModel().select(0);

		btnEditEvent.setDisable(true);
		btnDeleteEvent.setDisable(true);
		btnCreateEvent.setDisable(true);

		try 
		{
			eventsTable.setItems(getList());
			eventsTable.getSelectionModel().select(0);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	

		eventsTable.setOnMouseClicked((MouseEvent event) -> 
		{
			if (event.getClickCount() == 1) 
			{
				showEventDetailsFromTable();
				
				try 
				{
					if(eventsTable.getSelectionModel().getSelectedItem() != null)
					{
						generateAllMemberNameList();
						
						lvLecturersToAdd.setItems(lecturerNames);
						btnEditEvent.setDisable(false);
						btnDeleteEvent.setDisable(false);
						tpShowEventsPane.setDisable(false);
						tfMainEventLecturer.setText(selectedEvent.getEventLecturer());
						lvMembersAddedToEvent.setItems(getMembersAdded());
						lvNonMembersAddedToEvent.setItems(getNonMembersAdded());
						tfSelectedEvent1.setText(selectedEvent.getEventTitle());
						tfSelectedEvent2.setText(selectedEvent.getEventTitle());
						tfSelectedEvent3.setText(selectedEvent.getEventTitle());
						tfSelectedEventCategory2.setText(selectedEvent.getEventCategory());
						tfSelectedEventCategory.setText(selectedEvent.getEventCategory());
						lvCategoriesToAdd.setItems(categoryChoices);
						lvCategoriesAddedToEvent.setItems(getCategories());
						lvLecturersAlreadyAdded.setItems(getLecturersAdded());
						
						lblNonMembersAdded.setText("Non-members already added: " + nonMembers.size());
						lblMembersAdded.setText("Members already added: " + membersRegisteredList.size());
	
						if (selectedEvent.getEventType().equals("Lecture") || selectedEvent.getEventType().equals("Journey")) 
						{
							tpAddEventCategory.setDisable(true);
						}
	
						if (selectedEvent.getEventType().equals("Seminar") || selectedEvent.getEventType().equals("Workshop")) 
						{
							tpAddEventCategory.setDisable(false);
						}
	
						if (selectedEvent.getEventStatus().equals("Not finalized")) 
						{
							tpAddMembersToEvent.setDisable(true);
							tpAddNonMembersToEvent.setDisable(true);
						}
	
						if (selectedEvent.getEventStatus().equals("Finalized")) 
						{
							tpAddMembersToEvent.setDisable(false);
							tpAddNonMembersToEvent.setDisable(false);
						}
					}
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (ParseException e) 
				{
					e.printStackTrace();
				}
				tpShowEventsPane.setExpanded(true);
				btnEditEvent.setDisable(false);
				btnDeleteEvent.setDisable(false);
				;}
		});

		cbEventCategory.setOnMouseClicked((MouseEvent event) -> 
		{
			if (event.getClickCount() == 1) {
				cbEventLecturer.setVisible(false);
				dpEventStartDate.setVisible(false);
				dpEventEndDate.setVisible(false);
				tfEventStartTime.setVisible(false);
				tfEventEndTime.setVisible(false);
				tfEventEndTime.setVisible(false);
				tfEventNumberOfTickets.setVisible(false);
				tfEventPrice.setVisible(false);
				tfEventDiscount.setVisible(false);

				lblEventLecturer.setVisible(false);
				lblEventStartDate.setVisible(false);
				lblEventEndDate.setVisible(false);
				lblEventStartTime.setVisible(false);
				lblEventEndTime.setVisible(false);
				lblEventNumberOfTickets.setVisible(false);
				lblEventPrice.setVisible(false);
				lblEventDiscount.setVisible(false);
			}
		});
		
		cbEventType.setOnAction((ActionEvent event) -> 
		{
				String type = cbEventType.getValue();

				switch (type) 
				{
				case "Journey":

					tfEventDiscount.setText("0");
					tfEventDiscount.setEditable(false);
					
					break;

				default:
					
					tfEventDiscount.setText("");
					tfEventDiscount.setEditable(true);
					
					break;
				}				
			});		
		
		cbEventLecturer.setOnMouseClicked((MouseEvent event) -> 
		{
			if (event.getClickCount() == 1) 
			{
				String category = cbEventCategory.getValue();
				lecturerNames.clear();
				
				switch (category) 
				{
				case "Dream Interpretation":
					
					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					} 
					catch (ParseException e) 
					{
						e.printStackTrace();
					}

					cbEventLecturer.setItems(lecturerNames);
					cbEventLecturer.getSelectionModel().select(0);

					break;

				case "Healing":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbEventLecturer.setItems(lecturerNames);
					cbEventLecturer.getSelectionModel().select(0);

					break;

				case "Astrology":

					try {
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbEventLecturer.setItems(lecturerNames);
					cbEventLecturer.getSelectionModel().select(0);

					break;
					
				case "Reincarnation":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbEventLecturer.setItems(lecturerNames);
					cbEventLecturer.getSelectionModel().select(0);

					break;

				case "Karma":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbEventLecturer.setItems(lecturerNames);
					cbEventLecturer.getSelectionModel().select(0);

					break;

				case "Alternative Health-Care":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbEventLecturer.setItems(lecturerNames);
					cbEventLecturer.getSelectionModel().select(0);

					break;
				}
			}
		});	
		
		cbShowEventLecturer.setOnMouseClicked((MouseEvent event) -> 
		{
			if (event.getClickCount() == 1) 
			{
				String category = cbShowEventCategory.getValue();
				lecturerNames.clear();
				switch (category) {
				case "Dream Interpretation":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					} 
					catch (ParseException e) 
					{
						e.printStackTrace();
					}

					cbShowEventLecturer.setItems(lecturerNames);
					cbShowEventLecturer.getSelectionModel().select(0);

					break;

				case "Healing":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbShowEventLecturer.setItems(lecturerNames);
					cbShowEventLecturer.getSelectionModel().select(0);

					break;

				case "Astrology":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbShowEventLecturer.setItems(lecturerNames);
					cbShowEventLecturer.getSelectionModel().select(0);

					break;
					
				case "Reincarnation":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbShowEventLecturer.setItems(lecturerNames);
					cbShowEventLecturer.getSelectionModel().select(0);

					break;

				case "Karma":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbShowEventLecturer.setItems(lecturerNames);
					cbShowEventLecturer.getSelectionModel().select(0);

					break;

				case "Alternative Health-Care":

					try 
					{
						for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
						{
							if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
							{
								lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
							}
						}
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}

					cbShowEventLecturer.setItems(lecturerNames);
					cbShowEventLecturer.getSelectionModel().select(0);

					break;
				}
			}
		});	
		
		dpEventStartDate.setOnAction((ActionEvent event) -> 
		{
			dpEventEndDate.setValue(dpEventStartDate.getValue());

		});

		lvEventSearchResults.setOnMouseClicked((MouseEvent event) -> 
		{
			if (event.getClickCount() == 1) {
				showEventDetailsFromListView();
				tpShowEventsPane.setDisable(false);
				tpShowEventsPane.setExpanded(true);
				btnEditEvent.setDisable(false);
				btnDeleteEvent.setDisable(false);
			}
		});
		
		cbShowEventType.setOnAction((ActionEvent event) -> 
		{
			String type = cbShowEventType.getValue();
			
			switch(type)
			{
			case "Journey":
				
				tfShowEventDiscount.setText("0");
				tfShowEventDiscount.setEditable(false);
				
				break;
				
				default:
					
					tfShowEventDiscount.setEditable(true);
					
					break;
			}		
		});		
		
		cbShowEventCategory.setOnAction((ActionEvent event) -> 
		{			
			String category = cbShowEventCategory.getValue();
			lecturerNames.clear();
			switch (category) {
			case "Dream Interpretation":

				try 
				{
					for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
					{
						if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
						{
							lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
						}
					}
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (ParseException e) 
				{
					e.printStackTrace();
				}

				cbShowEventLecturer.setItems(lecturerNames);
				lvLecturersToAdd.setItems(lecturerNames);
				cbShowEventLecturer.getSelectionModel().select(0);

				break;

			case "Healing":

				try 
				{
					for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
					{
						if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
						{
							lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
						}
					}
				} 
				catch (FileNotFoundException | ParseException e) 
				{
					e.printStackTrace();
				}

				cbShowEventLecturer.setItems(lecturerNames);
				cbShowEventLecturer.getSelectionModel().select(0);

				break;

			case "Astrology":

				try 
				{
					for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
					{
						if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
						{
							lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
						}
					}
				} catch (FileNotFoundException | ParseException e) 
				{
					e.printStackTrace();
				}

				cbShowEventLecturer.setItems(lecturerNames);
				cbShowEventLecturer.getSelectionModel().select(0);

				break;
				
			case "Reincarnation":

				try 
				{
					for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
					{
						if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
						{
							lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
						}
					}
				} 
				catch (FileNotFoundException | ParseException e) 
				{
					e.printStackTrace();
				}

				cbShowEventLecturer.setItems(lecturerNames);
				cbShowEventLecturer.getSelectionModel().select(0);

				break;

			case "Karma":

				try 
				{
					for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
					{
						if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
						{
							lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
						}
					}
				} 
				catch (FileNotFoundException | ParseException e) 
				{
					e.printStackTrace();
				}

				cbShowEventLecturer.setItems(lecturerNames);
				cbShowEventLecturer.getSelectionModel().select(0);

				break;

			case "Alternative Health-Care":

				try 
				{
					for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
					{
						if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
						{
							lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
						}
					}
				} 
				catch (FileNotFoundException | ParseException e) 
				{
					e.printStackTrace();
				}

				cbShowEventLecturer.setItems(lecturerNames);
				cbShowEventLecturer.getSelectionModel().select(0);

				break;
			}
		});

		lblEventCount.setText(String.format("Event count: %d", eventList.size()));		
	}

	public void showEventDetailsFromTable() 
	{
		if (eventsTable.getSelectionModel().getSelectedItem() != null) 
		{
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
			tfShowEventTicketsRemaining.setText(Integer.toString(selectedEvent.getEventTicketsRemaining()));
			tfShowEventPrice.setText(Double.toString(selectedEvent.getEventPrice()));
			tfShowEventDiscount.setText(Double.toString(selectedEvent.getEventDiscount()));
			tfSelectedEventNonMember.setText(selectedEvent.getEventTitle());

			if (selectedEvent.getEventStatus().equals("Finalized")) 
			{
				cbShowEventStatus.setSelected(true);
			}
			else {
				cbShowEventStatus.setSelected(false);
			}
		}
	}

	public void showEventDetailsFromListView() 
	{
		if (lvEventSearchResults.getSelectionModel().getSelectedItem() != null) 
		{
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

			if (selectedEvent.getEventStatus().equals("Finalized")) 
			{
				cbShowEventStatus.setSelected(true);
			}

			else 
			{
				cbShowEventStatus.setSelected(false);
			}
		}
	}

	public ObservableList<Event> getList() throws ParseException, IOException 
	{
		eventList = viaOms.getEventList();

		for (int i = 0; i < eventList.size(); i++) 
		{
			events.add(new Event(eventList.getEvent(i).getEventTitle(), eventList.getEvent(i).getEventType(),
					eventList.getEvent(i).getEventCategory(), eventList.getEvent(i).getEventLecturer(),
					eventList.getEvent(i).getEventStartDate(), eventList.getEvent(i).getEventStartTime(),
					eventList.getEvent(i).getEventEndDate(), eventList.getEvent(i).getEventEndTime(),
					eventList.getEvent(i).getEventNumberOfTickets(), eventList.getEvent(i).getEventTicketsRemaining(),
					eventList.getEvent(i).getEventPrice(), eventList.getEvent(i).getEventDiscount(),
					eventList.getEvent(i).getEventStatus()));
		}
		
		return events;
	}

	public ObservableList<String> getMembersAdded() throws FileNotFoundException, ParseException 
	{
		membersAlreadyAdded.clear();

		if (eventsTable.getSelectionModel().getSelectedItem() != null) 
		{
			membersRegisteredList = viaOms.getEventMembers(selectedEvent.getEventTitle());

			for (int i = 0; i < membersRegisteredList.size(); i++) 
			{
				membersAlreadyAdded.add(membersRegisteredList.get(i));
			}
		}
		
		return membersAlreadyAdded;
	}

	public ObservableList<String> getNonMembersAdded() throws FileNotFoundException, ParseException 
	{
		nonMembersAlreadyAdded.clear();

		if (eventsTable.getSelectionModel().getSelectedItem() != null) 
		{
			ArrayList<String> nonMembersRegisteredList = viaOms.getEventNonMembers(selectedEvent.getEventTitle());

			for (int i = 0; i < nonMembersRegisteredList.size(); i++) 
			{
				nonMembersAlreadyAdded.add(nonMembersRegisteredList.get(i));
			}
		}
		
		return nonMembersAlreadyAdded;
	}

	public ObservableList<String> getCategories() throws FileNotFoundException 
	{
		categoriesAdded.clear();

		ArrayList<String> categoriesAddedToEvent = viaOms.getEventCategories(selectedEvent.getEventTitle());

		for (int i = 0; i < categoriesAddedToEvent.size(); i++) 
		{
			categoriesAdded.add(categoriesAddedToEvent.get(i));
		}

		return categoriesAdded;
	}
	
	public ObservableList<String> getLecturersAdded() throws FileNotFoundException
	{
		lecturersAdded.clear();
		
		ArrayList<String> lecturersAddedToEvent = viaOms.getEventLecturers(selectedEvent.getEventTitle());
		
		for(int i = 0; i < lecturersAddedToEvent.size(); i ++)
		{
			lecturersAdded.add(lecturersAddedToEvent.get(i));
		}

		return lecturersAdded;
	}

	public void generateAllMemberNameList() throws FileNotFoundException, ParseException 
	{
		memberNames.clear();
		memberList = viaOms.getMemberList();

		for (int i = 0; i < memberList.size(); i++) 
		{
			memberNames.add(memberList.getMember(i).getName());
		}

		lvMembersToAdd.setItems(memberNames);

		int size = memberList.size();
		for (int i = 0; i < size; i++) 
		{
			memberList.deleteMemberFromList(0);
		}
	}

	public static final LocalDate LOCAL_DATE (String dateString)
	{
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}	
	
	@FXML
	void createEvent(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException 
	{				
		if(tfEventPrice.getText().isEmpty())
		{
			tfEventPrice.setText("0.0");
		}
		
		if(tfEventDiscount.getText().isEmpty())
		{
			tfEventDiscount.setText("0.0");
		}
		
		if(tfEventNumberOfTickets.getText().isEmpty())
		{
			tfEventNumberOfTickets.setText("0");
		}
		
		String eventTitle = "";
		String eventType = cbEventType.getValue();
		String eventCategory = cbEventCategory.getValue();
		String eventLecturer = cbEventLecturer.getValue();
		LocalDate eventStartDate = dpEventStartDate.getValue();
		LocalDate eventEndDate = dpEventEndDate.getValue();
		String eventStartTime = tfEventStartTime.getText();
		String eventEndTime = tfEventEndTime.getText();
		double eventPrice = 0.0;
		double eventDiscount = 0.0;
		int eventNumberOfTickets = 0;
		String eventStatus = "Not finalized";
		int eventTicketsRemaining = eventNumberOfTickets;
		
		if(cbEventLecturer.getSelectionModel().getSelectedItem() == null)
		{
			eventLecturer = "Not specified";
		}
		
		if(dpEventStartDate.getValue() == null && dpEventEndDate.getValue() == null)
		{
			try 
			{
				dpEventStartDate.setValue(LOCAL_DATE("2001-01-01"));
				dpEventEndDate.setValue(LOCAL_DATE("2001-01-01"));
				eventStartDate = dpEventStartDate.getValue();
				eventEndDate = dpEventEndDate.getValue();
		    } 
			catch (NullPointerException e) 
			{
				e.printStackTrace();
		    }
		}
		
		if(tfEventStartTime.getText().isEmpty())
		{
			eventStartTime = "Not specified";
		}
		
		if(tfEventEndTime.getText().isEmpty())
		{
			eventEndTime = "Not specified";
		}
		
		if(tfEnterEventTitle.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please enter event title!");
			tfEnterEventTitle.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
		}
		
		else
		{
			tfEnterEventTitle.setStyle("-fx-border-width: 0px ;");
			eventTitle = tfEnterEventTitle.getText();
			
			try
			{
				tfEventNumberOfTickets.setStyle("-fx-border-width: 0px ;");
				eventNumberOfTickets = Integer.parseInt(tfEventNumberOfTickets.getText());
				
				try
				{
					tfEventPrice.setStyle("-fx-border-width: 0px ;");
					eventPrice = Double.parseDouble(tfEventPrice.getText());
					
					try
					{
						tfEventDiscount.setStyle("-fx-border-width: 0px ;");
						eventDiscount = Double.parseDouble(tfEventDiscount.getText());
						
						Event eventObj = new Event(eventTitle, eventType, eventCategory, eventLecturer, eventStartDate, eventStartTime,
								eventEndDate, eventEndTime, eventNumberOfTickets, eventNumberOfTickets, eventPrice, eventDiscount,
								eventStatus);
						if (viaOms.checkForEventDuplicates(eventObj)) {
							JOptionPane.showMessageDialog(null, "Event already exists in the system!");
							
						}

						else 
						{
							viaOms.createEvent(eventTitle, eventType, eventCategory, eventLecturer, eventStartDate, eventStartTime,
									eventEndDate, eventEndTime, eventNumberOfTickets, eventTicketsRemaining, eventPrice, eventDiscount,
									eventStatus);
							eventsTable.getItems().add(eventObj);

							String filename2 = eventTitle + "MemberListForEvent.txt";
							String filename3 = eventTitle + "NonMembersForEvent.txt";
							String filename4 = eventTitle + "CategoriesForEvent.txt";
							String filename5 = eventTitle + "LecturersForEvent.txt";
							
							File file2 = new File(filename2);
							file2.createNewFile();

							File file3 = new File(filename3);
							file3.createNewFile();

							File file4 = new File(filename4);
							file4.createNewFile();
							
							File file5 = new File(filename5);
							file5.createNewFile();

							clearCreateEventTextFields(event);
							btnCreateEvent.setDisable(true);
							cbEventLecturer.setVisible(false);
							dpEventStartDate.setVisible(false);
							dpEventEndDate.setVisible(false);
							tfEventStartTime.setVisible(false);
							tfEventEndTime.setVisible(false);
							tfEventEndTime.setVisible(false);
							tfEventNumberOfTickets.setVisible(false);
							tfEventPrice.setVisible(false);
							tfEventDiscount.setVisible(false);

							lblEventLecturer.setVisible(false);
							lblEventStartDate.setVisible(false);
							lblEventEndDate.setVisible(false);
							lblEventStartTime.setVisible(false);
							lblEventEndTime.setVisible(false);
							lblEventNumberOfTickets.setVisible(false);
							lblEventPrice.setVisible(false);
							lblEventDiscount.setVisible(false);
							lblEventCount.setText(String.format("Event count: %d", eventList.size()));
						}			
					}
					
					catch(NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid number for event discount!");
						tfEventDiscount.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
					}
				//	
				}
				
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid number for event price!");
					tfEventPrice.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				}
			}
			
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid number for number of tickets!");
				tfEventNumberOfTickets.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			}			
		}		
	}

	@FXML
	void continueCreateEvent() throws FileNotFoundException, ParseException 
	{	
		btnCreateEvent.setDisable(false);
		String type = cbEventType.getValue();
		String category = cbEventCategory.getValue();

		lecturerNames.clear();

		switch (type) 
		{

		case "Journey":

			tfEventDiscount.setEditable(false);
			tfEventDiscount.setText("0");

			break;

		default:
			tfEventDiscount.setEditable(true);
			break;
		}

		switch (category) 
		{
		
		case "Dream Interpretation":

			for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
			{
				if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
				{
					lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
				}
			}

			cbEventLecturer.setItems(lecturerNames);
			cbEventLecturer.getSelectionModel().select(0);

			break;

		case "Healing":

			for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
			{
				if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
				{
					lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
				}
			}

			cbEventLecturer.setItems(lecturerNames);
			cbEventLecturer.getSelectionModel().select(0);

			break;

		case "Astrology":

			for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
			{
				if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
				{
					lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
				}
			}

			cbEventLecturer.setItems(lecturerNames);
			cbEventLecturer.getSelectionModel().select(0);

			break;
		case "Reincarnation":

			for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
			{
				if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
				{
					lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
				}
			}

			cbEventLecturer.setItems(lecturerNames);
			cbEventLecturer.getSelectionModel().select(0);

			break;

		case "Karma":

			for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
			{
				if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
				{
					lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
				}
			}

			cbEventLecturer.setItems(lecturerNames);
			cbEventLecturer.getSelectionModel().select(0);

			break;

		case "Alternative Health-Care":

			for (int i = 0; i < viaOms.getLecturerList().size(); i++) 
			{
				if (viaOms.getLecturerList().getLecturer(i).getLecturerCategory().equals(category)) 
				{
					lecturerNames.add(viaOms.getLecturerList().getLecturer(i).getLecturerName());
				}
			}

			cbEventLecturer.setItems(lecturerNames);
			cbEventLecturer.getSelectionModel().select(0);

			break;
		}

		cbEventLecturer.setVisible(true);
		dpEventStartDate.setVisible(true);
		dpEventEndDate.setVisible(true);
		tfEventStartTime.setVisible(true);
		tfEventEndTime.setVisible(true);
		tfEventEndTime.setVisible(true);
		tfEventNumberOfTickets.setVisible(true);
		tfEventPrice.setVisible(true);
		tfEventDiscount.setVisible(true);

		lblEventLecturer.setVisible(true);
		lblEventStartDate.setVisible(true);
		lblEventEndDate.setVisible(true);
		lblEventStartTime.setVisible(true);
		lblEventEndTime.setVisible(true);
		lblEventNumberOfTickets.setVisible(true);
		lblEventPrice.setVisible(true);
		lblEventDiscount.setVisible(true);
	}

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
		
		btnCreateEvent.setDisable(true);
		cbEventLecturer.setVisible(false);
		dpEventStartDate.setVisible(false);
		dpEventEndDate.setVisible(false);
		tfEventStartTime.setVisible(false);
		tfEventEndTime.setVisible(false);
		tfEventEndTime.setVisible(false);
		tfEventNumberOfTickets.setVisible(false);
		tfEventPrice.setVisible(false);
		tfEventDiscount.setVisible(false);

		lblEventLecturer.setVisible(false);
		lblEventStartDate.setVisible(false);
		lblEventEndDate.setVisible(false);
		lblEventStartTime.setVisible(false);
		lblEventEndTime.setVisible(false);
		lblEventNumberOfTickets.setVisible(false);
		lblEventPrice.setVisible(false);
		lblEventDiscount.setVisible(false);
	}

	@FXML
	void searchEvents(ActionEvent event) 
	{
		ObservableList<Event> searchResults = FXCollections.observableArrayList();
		int searchCriteriaComboBoxSelection = cbEventSearchCriteria.getSelectionModel().getSelectedIndex();
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

			switch (searchCriteriaComboBoxSelection) {
			case 0:

				for (int i = 0; i < events.size(); i++) {
					if (events.get(i).getEventTitle().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(events.get(i));
					}
				}

				if (searchResults.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
				}

				break;

			case 1:

				for (int i = 0; i < events.size(); i++) 
				{
					if (events.get(i).getEventType().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(events.get(i));
					}
				}

				if (searchResults.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
				}

				break;

			case 2:

				for (int i = 0; i < events.size(); i++) 
				{
					if (events.get(i).getEventCategory().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(events.get(i));
					}
				}

				if (searchResults.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
				}

				break;

			case 3:

				for (int i = 0; i < events.size(); i++) 
				{
					if (events.get(i).getEventLecturer().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(events.get(i));
					}
				}

				if (searchResults.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
				}

				break;

			case 4:

				for (int i = 0; i < events.size(); i++) 
				{
					if (events.get(i).getEventStatus().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(events.get(i));
					}
				}

				if (searchResults.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
				}

				break;
			}

			lvEventSearchResults.setItems(searchResults);
		}	
	}

	@FXML
	void editEvent(ActionEvent event) 
	{
		if (eventsTable.getSelectionModel() != null) 
		{
			hboxEventEditOptions.setVisible(true);
			tfShowEventTitle.setEditable(false);
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
			
			tfShowEventTitle.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			cbShowEventType.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			cbShowEventCategory.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			cbShowEventLecturer.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			dpShowEventStartDate.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			dpShowEventEndDate.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowEventStartTime.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowEventEndTime.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowEventNumberOfTickets.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowEventPrice.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowEventDiscount.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowEventTicketsRemaining.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			
			btnDeleteEvent.setDisable(true);
		}
	}

	@FXML
	void saveEditEventChanges(ActionEvent event) throws ParseException, IOException 
	{
		if(tfShowEventPrice.getText().isEmpty())
		{
			tfShowEventPrice.setText("0.0");
		}
		
		if(tfShowEventDiscount.getText().isEmpty())
		{
			tfShowEventDiscount.setText("0.0");
		}
		
		if(tfShowEventNumberOfTickets.getText().isEmpty())
		{
			tfShowEventNumberOfTickets.setText("0");
		}
		
		int index = eventsTable.getSelectionModel().getSelectedIndex();

		String eventTitle = tfShowEventTitle.getText();
		String eventType = cbShowEventType.getValue();
		String eventCategory = cbShowEventCategory.getValue();
		String eventLecturer = cbShowEventLecturer.getValue();
		LocalDate eventStartDate = dpShowEventStartDate.getValue();
		LocalDate eventEndDate = dpShowEventEndDate.getValue();
		String eventStartTime = tfShowEventStartTime.getText();
		String eventEndTime = tfShowEventEndTime.getText();
		double eventPrice = 0.0;
		double eventDiscount = 0.0;
		int eventNumberOfTickets = 0;
		int eventTicketsRemaining = eventsTable.getSelectionModel().getSelectedItem().getEventTicketsRemaining();
		String eventStatus = "";

		if(cbShowEventLecturer.getSelectionModel().getSelectedItem() == null)
		{
			eventLecturer = "None";
		}				
	
		if(tfShowEventStartTime.getText().isEmpty())
		{
			eventStartTime = "Not specified";
		}
		
		if(tfShowEventEndTime.getText().isEmpty())
		{
			eventEndTime = "Not specified";
		}
		
		if (cbShowEventStatus.isSelected()) 
		{
			eventStatus = "Finalized";
		}

		if (!(cbShowEventStatus.isSelected()))
		{
			eventStatus = "Not finalized";
		}	
		
		try
		{
			tfShowEventTicketsRemaining.setStyle("-fx-border-width: 0px ;");
			eventNumberOfTickets = Integer.parseInt(tfShowEventNumberOfTickets.getText());
			
			try
			{
				tfShowEventPrice.setStyle("-fx-border-width: 0px ;");
				eventPrice = Double.parseDouble(tfShowEventPrice.getText());
				
				try
				{
					tfShowEventDiscount.setStyle("-fx-border-width: 0px ;");
					eventDiscount = Double.parseDouble(tfShowEventDiscount.getText());
					
					Event tempEvent = new Event(eventTitle, eventType, eventCategory, eventLecturer, eventStartDate, eventStartTime,
							eventEndDate, eventEndTime, eventNumberOfTickets, eventTicketsRemaining, eventPrice, eventDiscount,
							eventStatus);

					if (viaOms.checkForEventDuplicates(tempEvent)) {
						JOptionPane.showMessageDialog(null, "Event already exists in the system!");

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
						tfShowEventTicketsRemaining.setText("");
						tfShowEventPrice.setText("");
						tfShowEventDiscount.setText("");
						
						tfShowEventTitle.setStyle("-fx-border-width: 0px ;");
						cbShowEventType.setStyle("-fx-border-width: 0px ;");
						cbShowEventCategory.setStyle("-fx-border-width: 0px ;");
						cbShowEventLecturer.setStyle("-fx-border-width: 0px ;");
						dpShowEventStartDate.setStyle("-fx-border-width: 0px ;");
						dpShowEventEndDate.setStyle("-fx-border-width: 0px ;");
						tfShowEventStartTime.setStyle("-fx-border-width: 0px ;");
						tfShowEventEndTime.setStyle("-fx-border-width: 0px ;");
						tfShowEventNumberOfTickets.setStyle("-fx-border-width: 0px ;");
						tfShowEventTicketsRemaining.setStyle("-fx-border-width: 0px ;"); 
						tfShowEventPrice.setStyle("-fx-border-width: 0px ;"); 
						tfShowEventDiscount.setStyle("-fx-border-width: 0px ;"); 

						btnEditEvent.setDisable(true);
						btnDeleteEvent.setDisable(true);
					}

					else 
					{						
						selectedEvent.setEventTitle(eventTitle);
						selectedEvent.setEventType(eventType);
						selectedEvent.setEventCategory(eventCategory);
						selectedEvent.setEventLecturer(eventLecturer);
						selectedEvent.setEventStartDate(eventStartDate);
						selectedEvent.setEventEndDate(eventEndDate);
						selectedEvent.setEventStartTime(eventStartTime);
						selectedEvent.setEventEndTime(eventEndTime);
						selectedEvent.setEventPrice(eventPrice);
						selectedEvent.setEventDiscount(eventDiscount);
						selectedEvent.setEventNumberOfTickets(eventNumberOfTickets);
						
						selectedEvent.setEventTicketsRemaining(selectedEvent.calculateTicketsRemaining(selectedEvent.getEventNumberOfTickets(), membersRegisteredList.size()));						
						eventTicketsRemaining = selectedEvent.getEventTicketsRemaining();
						
						selectedEvent.setEventTicketsRemaining(eventTicketsRemaining);
						selectedEvent.setEventStatus(eventStatus);

						viaOms.editEvent(index, selectedEvent);
						eventsTable.getItems().set(index, selectedEvent);

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
						
						tfShowEventTitle.setStyle("-fx-border-width: 0px ;");
						cbShowEventType.setStyle("-fx-border-width: 0px ;");
						cbShowEventCategory.setStyle("-fx-border-width: 0px ;");
						cbShowEventLecturer.setStyle("-fx-border-width: 0px ;");
						dpShowEventStartDate.setStyle("-fx-border-width: 0px ;");
						dpShowEventEndDate.setStyle("-fx-border-width: 0px ;");
						tfShowEventStartTime.setStyle("-fx-border-width: 0px ;");
						tfShowEventEndTime.setStyle("-fx-border-width: 0px ;");
						tfShowEventNumberOfTickets.setStyle("-fx-border-width: 0px ;");
						tfShowEventTicketsRemaining.setStyle("-fx-border-width: 0px ;"); 
						tfShowEventPrice.setStyle("-fx-border-width: 0px ;"); 
						tfShowEventDiscount.setEditable(false);

						clearEditEventTextFields(event);

						btnEditEvent.setDisable(true);
						btnDeleteEvent.setDisable(true);
						
						eventsTable.requestFocus();
						eventsTable.getSelectionModel().select(index);
						eventsTable.getFocusModel().focus(index);
						
						MouseEvent.fireEvent(eventsTable, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
				                0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
				                true, true, true, true, true, true, null));
					}					
				}
				
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid number for event discount!");
					tfShowEventDiscount.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				}	
			}
			
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid number for event price!");
				tfShowEventPrice.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			}			
		}

		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Please enter a valid number for number of tickets!");
			tfShowEventNumberOfTickets.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
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
		tfShowEventTicketsRemaining.setText("");
		tfShowEventPrice.setText("");
		tfShowEventDiscount.setText("");
		
		tfShowEventTitle.setStyle("-fx-border-width: 0px ;");
		cbShowEventType.setStyle("-fx-border-width: 0px ;");
		cbShowEventCategory.setStyle("-fx-border-width: 0px ;");
		cbShowEventLecturer.setStyle("-fx-border-width: 0px ;");
		dpShowEventStartDate.setStyle("-fx-border-width: 0px ;");
		dpShowEventEndDate.setStyle("-fx-border-width: 0px ;");
		tfShowEventStartTime.setStyle("-fx-border-width: 0px ;");
		tfShowEventEndTime.setStyle("-fx-border-width: 0px ;");
		tfShowEventNumberOfTickets.setStyle("-fx-border-width: 0px ;");
		tfShowEventTicketsRemaining.setStyle("-fx-border-width: 0px ;"); 
		tfShowEventPrice.setStyle("-fx-border-width: 0px ;"); 
		tfShowEventDiscount.setStyle("-fx-border-width: 0px ;"); 
	}

	@FXML
	void cancelEditEvent(ActionEvent event) 
	{
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
		tfShowEventTicketsRemaining.setText("");
		tfShowEventPrice.setText("");
		tfShowEventDiscount.setText("");
		
		tfShowEventTitle.setStyle("-fx-border-width: 0px ;");
		cbShowEventType.setStyle("-fx-border-width: 0px ;");
		cbShowEventCategory.setStyle("-fx-border-width: 0px ;");
		cbShowEventLecturer.setStyle("-fx-border-width: 0px ;");
		dpShowEventStartDate.setStyle("-fx-border-width: 0px ;");
		dpShowEventEndDate.setStyle("-fx-border-width: 0px ;");
		tfShowEventStartTime.setStyle("-fx-border-width: 0px ;");
		tfShowEventEndTime.setStyle("-fx-border-width: 0px ;");
		tfShowEventNumberOfTickets.setStyle("-fx-border-width: 0px ;");
		tfShowEventTicketsRemaining.setStyle("-fx-border-width: 0px ;"); 
		tfShowEventPrice.setStyle("-fx-border-width: 0px ;"); 
		tfShowEventDiscount.setStyle("-fx-border-width: 0px ;"); 

		btnEditEvent.setDisable(true);
		btnDeleteEvent.setDisable(true);
	}

	@FXML
	void deleteEvent(ActionEvent event) throws ParseException, IOException 
	{
		if (eventsTable.getSelectionModel() != null) 
		{
			if (eventList.size() > 0) 
			{
				try 
				{
					String[] options = { "Delete event", "Cancel" };
					int n = JOptionPane.showOptionDialog(null,
							"Are you sure you want to delete event:\n" + selectedEvent + " ?", "Delete event",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					if (n == JOptionPane.YES_OPTION) 
					{
						int index = viaOms.getEventList().getEventIndex(selectedEvent);

						viaOms.deleteEvent(index, selectedEvent.getEventTitle());
						eventsTable.getItems().remove(index);
						lblEventCount.setText(String.format("Event count: %d", eventList.size()));
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) 
				{
					JOptionPane.showMessageDialog(null, "Please select an event from the table to delete");
				}
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "No more events left to delete");
			}
		} 
		else 
		{
			JOptionPane.showMessageDialog(null, "Please select an event from the table to delete");
		}
	}

	@FXML
	void addMemberToEvent(ActionEvent event) throws ParseException, IOException 
	{
		if (checkForDuplicates(membersRegisteredList, lvMembersToAdd.getSelectionModel().getSelectedItem())) 
		{
			JOptionPane.showMessageDialog(null, "Member is already added to event!");
		}

		else 
		{
			int index = eventsTable.getSelectionModel().getSelectedIndex();

			membersRegisteredList.add(lvMembersToAdd.getSelectionModel().getSelectedItem());

			selectedEvent.setEventTicketsRemaining(selectedEvent
					.calculateTicketsRemaining(selectedEvent.getEventNumberOfTickets(), membersRegisteredList.size()));

			viaOms.editEvent(index, selectedEvent);
			eventsTable.getItems().set(index, selectedEvent);

			viaOms.addMembersToEvent(membersRegisteredList, selectedEvent.getEventTitle());
			lvMembersAddedToEvent.getItems().add(lvMembersToAdd.getSelectionModel().getSelectedItem());
			lblMembersAdded.setText("Members already added: " + membersRegisteredList.size());
		}
	}
	
	@FXML
	void removeMemberFromEvent(ActionEvent event) throws ParseException, IOException 
	{	
		if (lvMembersAddedToEvent.getSelectionModel() != null) 
		{
			try
			{
				if (membersRegisteredList.size() > 0) 
				{
					int index = lvMembersAddedToEvent.getSelectionModel().getSelectedIndex();
					
					membersRegisteredList.remove(lvMembersAddedToEvent.getSelectionModel().getSelectedIndex());

					viaOms.editEvent(eventsTable.getSelectionModel().getSelectedIndex(), selectedEvent);
					eventsTable.getItems().set(eventsTable.getSelectionModel().getSelectedIndex(), selectedEvent);
					
					viaOms.addMembersToEvent(membersRegisteredList, selectedEvent.getEventTitle());
					
					lvMembersAddedToEvent.getItems().remove(index);
					selectedEvent.setEventTicketsRemaining(selectedEvent.calculateTicketsRemaining(selectedEvent.getEventNumberOfTickets(), membersRegisteredList.size()));
					lblMembersAdded.setText("Members already added: " + membersRegisteredList.size());
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				JOptionPane.showMessageDialog(null, "Please select a member to remove!");
			}				
		}
	}

	@FXML
	void addNonMemberToEvent(ActionEvent event) throws ParseException, IOException 
	{
		nonMembers = viaOms.getEventNonMembers(selectedEvent.getEventTitle());

		String nonMemberName = tfNonMemberName.getText();
		String nonMemberPhoneNumber = tfNonMemberPhoneNumber.getText();

		if (tfNonMemberName.getText().isEmpty() && tfNonMemberPhoneNumber.getText().isEmpty()) 
		{
			nonMemberName = "not specified";
			nonMemberPhoneNumber = "not specified";
		}

		if (tfNonMemberName.getText().isEmpty()) 
		{
			nonMemberName = "not specified";
		}

		if (tfNonMemberPhoneNumber.getText().isEmpty()) 
		{
			nonMemberPhoneNumber = "not specified";
		}
		
		int index = eventsTable.getSelectionModel().getSelectedIndex();

		String nonMember = String.format("%s,%s", nonMemberName, nonMemberPhoneNumber);

		nonMembers.add(nonMember);
		
		selectedEvent.setEventTicketsRemaining(selectedEvent
				.calculateTicketsRemaining(selectedEvent.getEventNumberOfTickets(), nonMembers.size()));

		viaOms.editEvent(index, selectedEvent);
		eventsTable.getItems().set(index, selectedEvent);

		viaOms.addNonMembersToEvent(nonMembers, selectedEvent.getEventTitle());
		nonMembersAlreadyAdded.add(nonMember);

		tfNonMemberName.setText("");
		tfNonMemberPhoneNumber.setText("");
		
		lblNonMembersAdded.setText("Non-members already added: " + nonMembers.size());
	}	
	
	@FXML
	void removeNonMemberFromEvent(ActionEvent event) throws FileNotFoundException 
	{
		nonMembers = viaOms.getEventNonMembers(selectedEvent.getEventTitle());

		if (lvNonMembersAddedToEvent.getSelectionModel() != null) 
		{
			try
			{
				if (nonMembers.size() > 0) 
				{
					int index = lvNonMembersAddedToEvent.getSelectionModel().getSelectedIndex();

					nonMembers.remove(index);

					lvNonMembersAddedToEvent.getItems().remove(index);
					viaOms.addNonMembersToEvent(nonMembers, selectedEvent.getEventTitle());
					lblNonMembersAdded.setText("Non-members already added: " + nonMembers.size());
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				JOptionPane.showMessageDialog(null, "Please select a non-member to remove!");
			}			
		}
	}	
	
	@FXML
	void addCategoryToEvent(ActionEvent event) throws FileNotFoundException 
	{
		eventCategories = viaOms.getEventCategories(selectedEvent.getEventTitle());

		if (checkForDuplicates(eventCategories, lvCategoriesToAdd.getSelectionModel().getSelectedItem())) 
		{
			JOptionPane.showMessageDialog(null, "Category is already added to event!");
		}

		else if (lvCategoriesToAdd.getSelectionModel().getSelectedItem().equals(selectedEvent.getEventCategory())) 
		{
			JOptionPane.showMessageDialog(null, "Category is already added to event!");
		}

		else 
		{
			eventCategories.add(lvCategoriesToAdd.getSelectionModel().getSelectedItem());
			
			lvCategoriesAddedToEvent.getItems().add(lvCategoriesToAdd.getSelectionModel().getSelectedItem());
			viaOms.addCategoriesToEvent(eventCategories, selectedEvent.getEventTitle());
		}
	}

	@FXML
	void removeCategoryFromEvent(ActionEvent event) throws FileNotFoundException 
	{
		eventCategories = viaOms.getEventCategories(selectedEvent.getEventTitle());

		if (lvCategoriesAddedToEvent.getSelectionModel() != null) 
		{
			try
			{
				if (eventCategories.size() > 0) 
				{
					int index = lvCategoriesAddedToEvent.getSelectionModel().getSelectedIndex();
					eventCategories.remove(index);

					lvCategoriesAddedToEvent.getItems().remove(index);
					viaOms.addCategoriesToEvent(eventCategories, selectedEvent.getEventTitle());
				}				
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				JOptionPane.showMessageDialog(null, "Please select a category to remove!");
			}			
		}	
	}
	
	@FXML
	void addLecturerToEvent(ActionEvent event) throws ParseException, IOException
	{
		if (checkForDuplicates(lecturersAlreadyRegistered, lvLecturersToAdd.getSelectionModel().getSelectedItem())) 
		{
			JOptionPane.showMessageDialog(null, "Lecturer is already added to event!");
		}
		
		else if (lvLecturersToAdd.getSelectionModel().getSelectedItem().equals(selectedEvent.getEventLecturer())) 
		{
			JOptionPane.showMessageDialog(null, "Lecturer is already added to event!");
		}
		
		else 
		{
			lecturersAlreadyRegistered.add(lvLecturersToAdd.getSelectionModel().getSelectedItem());

			viaOms.addLecturerToEvent(lecturersAlreadyRegistered, selectedEvent.getEventTitle());
			lvLecturersAlreadyAdded.getItems().add(lvLecturersToAdd.getSelectionModel().getSelectedItem());
			lblLecturersAdded.setText("Lecturers already added: " + lecturersAlreadyRegistered.size());
		}
	}
	
	@FXML
	void removeLecturerFromEvent(ActionEvent event) throws FileNotFoundException
	{
		lecturersAlreadyRegistered = viaOms.getEventLecturers(selectedEvent.getEventTitle());
		
		if (lvLecturersAlreadyAdded.getSelectionModel() != null) 
		{
			try
			{
				if (lecturersAlreadyRegistered.size() > 0) 
				{
					int index = lvLecturersAlreadyAdded.getSelectionModel().getSelectedIndex();
					lecturersAlreadyRegistered.remove(index);
					lvLecturersAlreadyAdded.getItems().remove(index);
					
					viaOms.addLecturerToEvent(lecturersAlreadyRegistered, selectedEvent.getEventTitle());
					lblLecturersAdded.setText("Lecturers already added: " + lecturersAlreadyRegistered.size());
				}
			}			
			catch(ArrayIndexOutOfBoundsException e)
			{
				JOptionPane.showMessageDialog(null, "Please select a lecturer to remove!");
			}			
		}
		
	}
	
	public boolean checkForDuplicates(ArrayList<String> memberList, String member) 
	{
		boolean status = false;

		for (int i = 0; i < memberList.size(); i++) 
		{
			if (member.equals(memberList.get(i)))
				return true;
		}
		
		return status;
	}	
}
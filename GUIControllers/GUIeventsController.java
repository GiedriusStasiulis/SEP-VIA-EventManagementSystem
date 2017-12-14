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
 * Controller class for GUIevents.fxml. Handles all ActionEvent and
 * MouseEvents that occur on the events page in the application. *
 * 
 * @author Group#2 *
 */

public class GUIeventsController implements Initializable
{
	private File file;
	
	private Event eventObj;
	private Event selectedEvent;
	private EventList eventList = new EventList();
	private MemberList memberList = new MemberList();
	private LecturerList lecturerList= new LecturerList();
	private ArrayList<String> membersRegisteredList = new ArrayList<String>();
		
	private static final String FILENAME = "MemberList.txt";
	private static final String FILENAMEEVENT = "EventList.txt";
	private static final String LECTURERFILENAME = "LecturerList.txt";
	private String filenameMembersEvent = "MemberListForEvent.txt";

	private FileReaderWriter eventFile = new FileReaderWriter(FILENAMEEVENT);
	private FileReaderWriter lecturerFile = new FileReaderWriter(LECTURERFILENAME);
	private FileReaderWriter memberFile = new FileReaderWriter(FILENAME);
	private FileReaderWriter memberEventFile = new FileReaderWriter(filenameMembersEvent);
	
	private ObservableList<String> memberNames=FXCollections.observableArrayList();
	//private ObservableList<String> emptyList=FXCollections.observableArrayList();
	private ObservableList<String> typeChoices = FXCollections.observableArrayList("Lecture","Seminar","Workshop","Journey");
	private ObservableList<String> categoryChoices =FXCollections.observableArrayList("Astrology","Therapy","Fortune-telling");
	private ObservableList<Event>  events = FXCollections.observableArrayList();
	private ObservableList<String> lecturerNames = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Title","Type","Category","Lecturer","Status");
	
	private ObservableList<String> membersAlreadyAdded = FXCollections.observableArrayList();
	private ObservableList<String> membersAlreadyAddedtemp = FXCollections.observableArrayList();
	
	
	@FXML private BorderPane eventsPage = new BorderPane();
	
	@FXML private Button btnCreateEvent,btnDeleteEvent,btnCancelEditEvent,btnEditEvent,btnClearTextFields,
							btnSearchEvents,btnSaveEventEditChanges,btnClearEditEventTextFields,btnAddMemberToEvent,
							btnRemoveMemberFromEvent,btnAddCategoryToEvent,btnRemoveCategoryFromEvent;
	
	@FXML private TextField tfEnterSearchKeywords,tfEventEndTime,tfShowEventPrice,tfShowEventNumberOfTickets,tfShowEventEndTime,
							tfEventNumberOfTickets,tfEventPrice,tfShowEventDiscount,tfEnterEventTitle,tfEventStartTime,
							tfEventDiscount,tfShowEventTicketsRemaining,tfShowEventStartTime,tfSelectedEvent,tfShowEventTitle;

	@FXML private ComboBox<String> cbEventLecturer, cbEventCategory,cbEventSearchCriteria,cbShowEventCategory,cbEventType,cbShowEventLecturer,cbShowEventType;
	
	@FXML private TableView<Event> eventsTable;
	
	@FXML private TableColumn<Event, String> tcEventTitle,tcEventType,tcEventCategory,
									tcEventStartTime,tcEventEndTime,tcEventDuration,tcEventPrice,tcEventDiscount,tcEventNumberOfTickets,
									tcTicketsRemaining,tcEventLecturer,tcEventStatus;
	
	@FXML private TableColumn<Event, LocalDate> tcEventStartDate,tcEventEndDate;
	
	@FXML private Label lblEventCount;
	
	@FXML private ListView<Event> lvEventSearchResults;
	@FXML private ListView<String> lvMembersToAdd,lvMembersAddedToEvent,lvCategoriesToAdd,lvCategoriesAddedToEvent;
	
	@FXML private CheckBox cbSelectAllMembersToAdd,cbSelectAllMembersToRemove,cbFinalizeEvent,cbShowEventStatus;
	
	@FXML private DatePicker dpShowEventEndDate,dpShowEventStartDate,dpEventStartDate,dpEventEndDate;
	
	@FXML private HBox hboxEventEditOptions;	
	
	@FXML private TitledPane tpShowEventsPane,tpShowSearchEventsPane,tpShowCreateEventPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		System.out.println("Event list size: " + memberList.size());
		
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
    	
		//initialize combobox
		
		cbEventType.setItems(typeChoices);
		cbEventType.getSelectionModel().select(0);
		cbEventCategory.setItems(categoryChoices);
		cbEventCategory.getSelectionModel().select(0);
		
		
		
		cbShowEventCategory.setItems(categoryChoices);
		cbShowEventType.setItems(typeChoices);
		
		//cbShowEventCategory.getSelectionModel().select(0);
		//cbShowEventType.getSelectionModel().select(0);
		//initialize table
		tcEventTitle.setCellValueFactory(new PropertyValueFactory<Event,String>("eventTitle"));
		//////////////////
		
		
		tcEventType.setCellValueFactory(new PropertyValueFactory<Event,String>("eventType"));
		tcEventCategory.setCellValueFactory(new PropertyValueFactory<Event,String>("eventCategory"));
		tcEventLecturer.setCellValueFactory(new PropertyValueFactory<Event,String>("eventLecturer"));
		
		
		/////////////////
		tcEventStartDate.setCellValueFactory(new PropertyValueFactory<Event,LocalDate>("eventStartDate"));
		tcEventEndDate.setCellValueFactory(new PropertyValueFactory<Event,LocalDate>("eventEndDate"));
		tcEventStartTime.setCellValueFactory(new PropertyValueFactory<Event, String>("startTime"));
		tcEventEndTime.setCellValueFactory(new PropertyValueFactory<Event, String>("endTime"));
		tcEventPrice.setCellValueFactory(new PropertyValueFactory<Event, String>("price"));
		tcEventDiscount.setCellValueFactory(new PropertyValueFactory<Event, String>("discount"));
		tcEventNumberOfTickets.setCellValueFactory(new PropertyValueFactory<Event, String>("maxMembers"));
		tcEventDuration.setCellValueFactory(new PropertyValueFactory<Event,String>("eventDuration"));
		tcEventStatus.setCellValueFactory(new PropertyValueFactory<Event,String>("status"));
		
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
		
		//spEventsTableScrollPane.setStyle("-fx-font-size: 13px;");
		
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
		
		/*try {
			lvMembersAddedToEvent.setItems(memberEventFile.readEventMemberFile(eventsTable.getSelectionModel().getSelectedItem().getEventTitle()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		try
		{		   
		   eventsTable.setItems(getList()); 
		   //System.out.println(eventList);
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
		
		
		eventsTable.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
			   showEventDetailsFromTable();
				tpShowEventsPane.setExpanded(true);
				btnEditEvent.setDisable(false);
				btnDeleteEvent.setDisable(false);
				System.out.println(selectedEvent);
				try
            {
					//lvMembersAddedToEvent.setItems(getMembersAdded());
					
			//System.out.println(getMembersAdded());
				//lvMembersAddedToEvent.setItems(memberEventFile.readEventMemberFile();   
				
               generateAllMemberNameList();
            }
            catch (FileNotFoundException | ParseException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            };
			}
		});
		
		
		lvMembersToAdd.setOnMouseClicked((MouseEvent event) -> {
		   if (event.getClickCount()==1) {
		   //System.out.println(lvMembersToAdd.getSelectionModel().getSelectedItem());
		   //System.out.println(eventsTable.getSelectionModel().getSelectedItem());
		//eventsTable.getSelectionModel().getSelectedItem().registerLecturer(lvMembersToAdd.getSelectionModel().getSelectedItem());	
		   
		   }
		}); 
				
		/*tpShowCreateEventPane.setOnMouseClicked((MouseEvent event) -> {
			if(event.getClickCount() == 1) {
				tpShowEventsPane.setExpanded(false);
			}
		});
		
		tpShowSearchEventsPane.setOnMouseClicked((MouseEvent event) -> {
			if(event.getClickCount() == 1) {
				tpShowEventsPane.setExpanded(false);
			}
		}); */
		
		lvEventSearchResults.setOnMouseClicked((MouseEvent event) -> {
			   if (event.getClickCount()==1) {
				   showEventDetailsFromListView();
				   tpShowEventsPane.setExpanded(true);
				   btnEditEvent.setDisable(false);
				   btnDeleteEvent.setDisable(false);
				   System.out.println(selectedEvent);
			   }
			}); 
	}	
	
	

	public void showEventDetailsFromTable()
	{
		if (eventsTable.getSelectionModel().getSelectedItem() !=null)
		{
		   selectedEvent = eventsTable.getSelectionModel().getSelectedItem();
		   tfShowEventTitle.setText(selectedEvent.getEventTitle());
		   cbShowEventType.getSelectionModel().select(selectedEvent.getEventType());
		   cbShowEventCategory.getSelectionModel().select(selectedEvent.getEventCategory());
		   dpShowEventStartDate.setValue(selectedEvent.getEventStartDate());
		   dpShowEventEndDate.setValue(selectedEvent.getEventEndDate());
		   tfShowEventPrice.setText(Double.toString(selectedEvent.getPrice()));
		   tfShowEventStartTime.setText(selectedEvent.getStartTime());
		   tfShowEventEndTime.setText(selectedEvent.getEndTime());
		   tfShowEventDiscount.setText(Double.toString(selectedEvent.getDiscount()));
		   cbShowEventLecturer.getSelectionModel().select(selectedEvent.getEventLecturer());
		   tfShowEventNumberOfTickets.setText(Integer.toString(selectedEvent.getMaxMembers()));	
		   
		   if(selectedEvent.getStatus().equals("Finalized"))
		   {
			   cbShowEventStatus.setSelected(true);
		   }
		   
		}
	}
	
	
	public void showEventDetailsFromListView()
	{
		if (lvEventSearchResults.getSelectionModel().getSelectedItem() !=null)
		{
		   selectedEvent = lvEventSearchResults.getSelectionModel().getSelectedItem();
		   tfShowEventTitle.setText(selectedEvent.getEventTitle());
		   cbShowEventType.getSelectionModel().select(selectedEvent.getEventType());
		   cbShowEventCategory.getSelectionModel().select(selectedEvent.getEventCategory());
		   dpShowEventStartDate.setValue(selectedEvent.getEventStartDate());
		   dpShowEventEndDate.setValue(selectedEvent.getEventEndDate());
		   tfShowEventPrice.setText(Double.toString(selectedEvent.getPrice()));
		   tfShowEventStartTime.setText(selectedEvent.getStartTime());
		   tfShowEventEndTime.setText(selectedEvent.getEndTime());
		   tfShowEventDiscount.setText(Double.toString(selectedEvent.getDiscount()));
		   cbShowEventLecturer.getSelectionModel().select(selectedEvent.getEventLecturer());
		   tfShowEventNumberOfTickets.setText(Integer.toString(selectedEvent.getMaxMembers()));		   
		}
	}
	
	public ObservableList<String> getLecturerNames() throws FileNotFoundException, ParseException
	{
		lecturerList = lecturerFile.readLecturerTextFile();
		
		for(int i = 0; i < lecturerList.size(); i++)
		{
			lecturerNames.add(lecturerList.getLecturer(i).getName());
		}
		
		return lecturerNames;
	}
	
	
	
	
	
	
	public ObservableList<String> getMembersAdded() throws FileNotFoundException, ParseException
	{
		membersAlreadyAddedtemp.clear();
		
		memberEventFile.setFile(eventsTable.getSelectionModel().getSelectedItem().getEventTitle().toString() + "MemberListForEvent.txt");
		
		membersRegisteredList = memberEventFile.readEventMemberFile();
		
		
		for(int i = 0; i < membersRegisteredList.size(); i++)
		{
			membersAlreadyAddedtemp.add(membersRegisteredList.get(i));
		}
		
		//membersAlreadyAddedtemp.clear();
		return membersAlreadyAddedtemp;
	}

	

	
	@FXML
	public void clearMemberListView()
	{
	   
	}
	
	 @FXML
	 public void generateAllMemberNameList() throws FileNotFoundException, ParseException 
	 {
	    
	    memberNames.clear();
	   
	    
	    
	    memberList= memberFile.readMemberTextFile();
	    
	    for (int i=0; i<memberList.size(); i++)
	    {
	       memberNames.add(memberList.getMember(i).getName());
	       
	    }
	    
	    
	    lvMembersToAdd.setItems(memberNames);
	    
	    //System.out.println(memberList.size());
	    int size = memberList.size();
	    for ( int i = 0; i<size ; i++)
	    {
	       memberList.deleteMember(0);
	    }
	    
	    //System.out.println(memberList.size());
	    
	    
	 }
	    
	 //@FXML
	/* void showRegisteredMemberList()
	 {
	    lvMemberList.setItems(generateRegisteredMemberList());
	 }
	 */
    @FXML
    void createEvent(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException
    {
    	String eventTitle = tfEnterEventTitle.getText();
    	
    	String eventType = cbEventType.getValue();
    	String eventCategory = cbEventCategory.getValue();
    	String eventLecturer = cbEventLecturer.getValue();
    	
    	//DatePicker datePicker = new DatePicker();
    	LocalDate localStartDate = dpEventStartDate.getValue();
    	LocalDate localEndDate = dpEventEndDate.getValue();
    	
    	String startTime = tfEventStartTime.getText();
    	String endTime = tfEventEndTime.getText();
    	double price = Double.parseDouble(tfEventPrice.getText());
    	double discount = Double.parseDouble(tfEventDiscount.getText());
    	int maxMembers = Integer.parseInt(tfEventNumberOfTickets.getText());
    	
    	String finalized = "";
    	
    	if(cbFinalizeEvent.isSelected())
    	{
    		finalized = "Finalized";
    	}
    	
    	else
    	{
    		finalized = "Not finalized";
    	}
         	
    	eventObj = new Event(eventTitle,eventType,eventCategory,eventLecturer,localStartDate,startTime,localEndDate,endTime,maxMembers,price,discount,finalized);
    	eventList.addEventToList(eventObj);
    	eventFile.writeEventTextFile(eventList);
    	eventsTable.getItems().add(eventObj);   	
    	
    	//////////create a file to store lecturer names for this event///////
    	
    	String filename = eventTitle+"LecturerListForEvent.txt";
      File file = new File(filename);
      file.createNewFile();
      /////////////////////////////////////////////////////////////////////
      
      /////////// Create a file to store members for this event///////////
      String filename2= eventTitle+"MemberListForEvent.txt";
      File file2 = new File(filename2);
      file2.createNewFile();
      ////////////////////////////////////////////////////////////////////
      
      
    	///testing////
    	//System.out.println(eventNew.getEventCategory());
    	
    	
    	//////////////
    	clearCreateEventTextFields(event);
    }
    
    public ObservableList<Event> getList() throws ParseException, IOException
    {
       eventList = eventFile.readEventsTextFile();
       
       for ( int i=0; i<eventList.size(); i++)
       {
          events.add(new Event(eventList.getEvent(i).getEventTitle(),eventList.getEvent(i).getEventType(),eventList.getEvent(i).getEventCategory(),eventList.getEvent(i).getEventLecturer(),eventList.getEvent(i).getEventStartDate(),eventList.getEvent(i).getStartTime(),eventList.getEvent(i).getEventEndDate(),eventList.getEvent(i).getEndTime(),eventList.getEvent(i).getMaxMembers(),eventList.getEvent(i).getPrice(),eventList.getEvent(i).getDiscount(),eventList.getEvent(i).getStatus()));
       }
       //System.out.println("From Controller getList() method, get category -: "+eventList.getEvent(0).getCategory() );
       return events;
    }
    
    /*public ObservableList<String> getLecturerNames() throws FileNotFoundException,ParseException
    {
       lecturerList = lecturerFile.readLecturerTextFile();
       
       for (int i=0; i<lecturerList.size(); i ++)
       {
          lecturerNames.add(lecturerList.getLecturer(i).getName());
       }
       return lecturerNames;
    }*/
    

    @FXML
    void clearCreateEventTextFields(ActionEvent event) 
    {
    	tfEnterEventTitle.setText("");
    }

    @FXML
    void searchEvents(ActionEvent event) 
    {
    	System.out.println("Search event");
    	ObservableList<Event> searchResults = FXCollections.observableArrayList();
    	int searchCriteriaComboBoxSelection = cbEventSearchCriteria.getSelectionModel().getSelectedIndex();
    	String searchKeyword = tfEnterSearchKeywords.getText();	
    	tfEnterSearchKeywords.setText("");
    	
    	switch(searchCriteriaComboBoxSelection)
    	{
    	case 0:
    		
    		for(int i = 0; i < events.size(); i ++)
			{
				if (events.get(i).getEventTitle().toLowerCase().contains(searchKeyword))
				{
					searchResults.add(events.get(i));
				}
			}
			
			if(searchResults.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}
    		
    		break;
    		
    	case 1:
    		
    		for(int i = 0; i < events.size(); i ++)
			{
				if (events.get(i).getEventType().toLowerCase().contains(searchKeyword))
				{
					searchResults.add(events.get(i));
				}
			}
			
			if(searchResults.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}    		
    		
    		break;
    		
    	case 2:
    		
    		for(int i = 0; i < events.size(); i ++)
			{
				if (events.get(i).getEventCategory().toLowerCase().contains(searchKeyword))
				{
					searchResults.add(events.get(i));
				}
			}
			
			if(searchResults.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}    		    		
    		
    		break;
    		
    	case 3:
    		
    		for(int i = 0; i < events.size(); i ++)
			{
				if (events.get(i).getEventLecturer().toLowerCase().contains(searchKeyword))
				{
					searchResults.add(events.get(i));
				}
			}
			
			if(searchResults.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			}    
    		
    		break;
    		
    	case 4:
    		
    		for(int i = 0; i < events.size(); i ++)
			{
				if (events.get(i).getStatus().toLowerCase().contains(searchKeyword))
				{
					searchResults.add(events.get(i));
				}
			}
			
			if(searchResults.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No event found with the given search keyword: \n" + searchKeyword);
			} 
    		
    		break;
    	}
    	
    	lvEventSearchResults.setItems(searchResults);
    }

    @FXML
    void editEvent(ActionEvent event) 
    {
    	if(eventsTable.getSelectionModel() != null)
		{
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
	    	
	    	//tfShowMemberName.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
	    	//tfShowMemberEmail.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
		}	

    }
    
    @FXML
    void saveEditEventChanges(ActionEvent event) throws FileNotFoundException, ParseException
    {
 	int index = eventsTable.getSelectionModel().getSelectedIndex();
    	
    	String newEventTitle = tfShowEventTitle.getText();
    	
    	String newEventType = cbShowEventType.getValue();
    	String newEventCategory = cbShowEventCategory.getValue();
    	String newEventLecturer = cbShowEventLecturer.getValue();
    	
    	//DatePicker datePicker = new DatePicker();
    	LocalDate newLocalStartDate = dpShowEventStartDate.getValue();
    	LocalDate newLocalEndDate = dpShowEventEndDate.getValue();
    	
    	String newStartTime = tfShowEventStartTime.getText();
    	String newEndTime = tfShowEventEndTime.getText();
    	double newPrice = Double.parseDouble(tfShowEventPrice.getText());
    	double newDiscount = Double.parseDouble(tfShowEventDiscount.getText());
    	int newMaxMembers = Integer.parseInt(tfShowEventNumberOfTickets.getText());
    	
    	String newFinalized = "";
    	
    	if(cbShowEventStatus.isSelected())
    	{
    		newFinalized = "Finalized";
    	}
    	
    	else
    	{
    		newFinalized = "Not finalized";
    	}
    	
    	eventsTable.getSelectionModel().getSelectedItem().setEventTitle(newEventTitle);
    	eventsTable.getSelectionModel().getSelectedItem().setEventType(newEventType);
    	eventsTable.getSelectionModel().getSelectedItem().setEventCategory(newEventCategory);
    	eventsTable.getSelectionModel().getSelectedItem().setEventLecturer(newEventLecturer);
    	eventsTable.getSelectionModel().getSelectedItem().setEventStartDate(newLocalStartDate);
    	eventsTable.getSelectionModel().getSelectedItem().setEventEndDate(newLocalEndDate);
    	eventsTable.getSelectionModel().getSelectedItem().setStartTime(newStartTime);
    	eventsTable.getSelectionModel().getSelectedItem().setEndTime(newEndTime);
    	eventsTable.getSelectionModel().getSelectedItem().setPrice(newPrice);
    	eventsTable.getSelectionModel().getSelectedItem().setDiscount(newDiscount);
    	eventsTable.getSelectionModel().getSelectedItem().setMaxMembers(newMaxMembers);
    	eventsTable.getSelectionModel().getSelectedItem().setStatus(newFinalized);
    	

	    	eventList.replaceEvent(index,eventsTable.getSelectionModel().getSelectedItem());    	
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
    }

    @FXML
    void clearEditEventTextFields(ActionEvent event)
    {
   

		/*
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
		}*/
    }

    @FXML
    void cancelEditEvent(ActionEvent event) 
    {
    	hboxEventEditOptions.setVisible(false);
    	
		tfShowEventTitle.setEditable(false);		
		dpShowEventStartDate.setEditable(false);
		dpShowEventEndDate.setEditable(false);
		tfShowEventStartTime.setEditable(false);
		tfShowEventEndTime.setEditable(false);
		tfShowEventNumberOfTickets.setEditable(false);
		tfShowEventTicketsRemaining.setEditable(false);
		tfShowEventDiscount.setEditable(false);
    }

    @FXML
    void deleteEvent(ActionEvent event) throws FileNotFoundException 
    {
    	System.out.println(selectedEvent);
    	System.out.println(eventList);
    	
    	if(eventsTable.getSelectionModel() != null)
    	{
    		System.out.println(eventsTable.getSelectionModel().getSelectedIndex());
    		if(eventList.size() > 0)
    		{
	    		try 
	    		{	  
	    			String[] options = {"Delete","Cancel"}; 
	    	    	int n = JOptionPane.showOptionDialog(null,
	    	                "Are you sure you want to delete member:\n" + eventsTable.getSelectionModel().getSelectedItem() + " ?",
	    	                "Delete a member",
	    	                JOptionPane.YES_NO_OPTION,
	    	                JOptionPane.QUESTION_MESSAGE,
	    	                null,
	    	                options,
	    	                options[0]);	    			
	    			
	    			if (n == JOptionPane.YES_OPTION) 
	    			{
	    			
	    				int index = eventsTable.getSelectionModel().getSelectedIndex();
	    				
	    				System.out.println(index);
	    				
				    	eventList.deleteEvent(index);
				    	eventFile.writeEventTextFile(eventList);
				    	eventsTable.getItems().remove(index);
				    	
				    	//tfShowEventTitle.setText("");

				    	
				    	//lblMemberCount.setText(String.format("Member count: %d", memberList.size()));
	    			}
	    		}
	    		catch(ArrayIndexOutOfBoundsException e)
	    		{
	    			//e.printStackTrace();
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
    void addMemberToEvent(ActionEvent event) throws FileNotFoundException 
    {
    	//System.out.println("Add member to event");
    	
    	
    	if(checkForDuplicates(membersRegisteredList,lvMembersToAdd.getSelectionModel().getSelectedItem()))
    	{
    		JOptionPane.showMessageDialog(null, "Member is already added to event!");
    	}
    	
    	else
    	{
    		eventsTable.getSelectionModel().getSelectedItem().addMemberToEvent(lvMembersToAdd.getSelectionModel().getSelectedItem());
        	//membersRegisteredList.clear();
        	membersRegisteredList.add(lvMembersToAdd.getSelectionModel().getSelectedItem());
        	
        	memberEventFile.writeEventMemberFile(membersRegisteredList,eventsTable.getSelectionModel().getSelectedItem().getEventTitle());
        	
        	lvMembersAddedToEvent.getItems().add(lvMembersToAdd.getSelectionModel().getSelectedItem());
    	}
    }
    
    public boolean checkForDuplicates(ArrayList<String> memberList, String member)
	{
		boolean status = false;
		
		for(int i = 0; i < membersRegisteredList.size(); i++)
		{
			if(member.equals(membersRegisteredList.get(i)))
				return true;
		}
		
		return status;
	}

    @FXML
    void removeMemberFromEvent(ActionEvent event) throws FileNotFoundException
    {
    	System.out.println("Remove member from event");
    	
    	if(lvMembersAddedToEvent.getSelectionModel() != null)
    	{
    		if(membersRegisteredList.size() > 0)
    		{
    			int index = lvMembersAddedToEvent.getSelectionModel().getSelectedIndex();
    			membersRegisteredList.remove(index);
    			memberEventFile.writeEventMemberFile(membersRegisteredList,eventsTable.getSelectionModel().getSelectedItem().getEventTitle());
    			lvMembersAddedToEvent.getItems().remove(index);
    		}
    	}
    	
    }
    
    @FXML
    void addCategoryToEvent(ActionEvent event) 
    {
    	System.out.println("Add lecturer to event");
    }
    
    @FXML
    void removeCategoryFromEvent(ActionEvent event) 
    {
    	System.out.println("Remove lecturer from event");
    }
    
    
}
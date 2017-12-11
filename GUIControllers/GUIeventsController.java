import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Controller class for GUIevents.fxml. Handles all ActionEvent and MouseEvents
 * that occur on the events page in the application. * 
 * @author Group#2 *
 */

public class GUIeventsController implements Initializable
{
	@FXML private BorderPane eventsPage = new BorderPane();
	
	@FXML private Button btnCreateEvent,btnDeleteEvent,btnCancelEditEvent,btnEditEvent,btnClearTextFields,
							btnSearchEvents,btnSaveEventEditChanges,btnClearCreateEventTextFields,btnAddMemberToEvent,
							btnRemoveMemberFromEvent;
	
	@FXML private TextField tfEnterSearchKeywords,tfEventEndTime,tfShowEventPrice,tfShowEventNumberOfTickets,tfShowEventEndTime,
							tfEventNumberOfTickets,tfEventPrice,tfShowEventDiscount,tfEnterEventTitle,tfEventStartTime,
							tfEventDiscount,tfShowEventTicketsRemaining,tfShowEventStartTime,tfSelectedEvent,tfShowEventTitle;

	@FXML private ComboBox<?> cbEventSearchCriteria,cbShowEventCategory,cbEventType,cbShowEventLecturer,cbShowEventType,cbEventCategory,cbEventLecturer;
	
	@FXML private TableView<?> eventsTable;
	
	@FXML private TableColumn<?, ?> tcEventTitle,tcEventType,tcEventCategory,tcEventStartDate,tcEventEndDate,
									tcEventStartTime,tcEventEndTime,tcEventDuration,tcEventPrice,tcEventDiscount,tcEventNumberOfTickets,
									tcTicketsRemaining,tcEventLecturer;
	
	@FXML private Label lblEventCount;
	
	@FXML private ListView<?> lvEventSearchResults,lvMemberList,lvMembersAddedToEvent;
	
	@FXML private CheckBox cbSelectAllMembersToAdd,cbSelectAllMembersToRemove;
	
	@FXML private DatePicker dpShowEventEndDate,dpShowEventStartDate,dpEventStartDate,dpEventEndDate;
	
	@FXML private HBox hboxEventEditOptions;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		eventsPage.setMaxHeight(Double.MAX_VALUE);
		eventsPage.setMaxWidth(Double.MAX_VALUE);	
		
		hboxEventEditOptions.setVisible(false);
		
		tcEventTitle.setStyle("-fx-alignment: CENTER;");
		tcEventType.setStyle("-fx-alignment: CENTER;");
		tcEventCategory.setStyle("-fx-alignment: CENTER;");
		tcEventStartDate.setStyle("-fx-alignment: CENTER;");
		tcEventEndDate.setStyle("-fx-alignment: CENTER;");
		tcEventStartTime.setStyle("-fx-alignment: CENTER;");
		tcEventEndTime.setStyle("-fx-alignment: CENTER;");
		tcEventDuration.setStyle("-fx-alignment: CENTER;");
		tcEventPrice.setStyle("-fx-alignment: CENTER;");
		tcEventDiscount.setStyle("-fx-alignment: CENTER;");
		tcEventNumberOfTickets.setStyle("-fx-alignment: CENTER;");
		tcTicketsRemaining.setStyle("-fx-alignment: CENTER;");
		tcEventLecturer.setStyle("-fx-alignment: CENTER;");
				
		cbShowEventType.setDisable(true);
		cbShowEventCategory.setDisable(true);
		cbShowEventLecturer.setDisable(true);
		
		dpShowEventStartDate.setDisable(true);		
		dpShowEventEndDate.setDisable(true);
		
		tfShowEventTitle.setEditable(false);
		tfShowEventStartTime.setEditable(false);
		tfShowEventEndTime.setEditable(false);
		tfShowEventNumberOfTickets.setEditable(false);
		tfShowEventTicketsRemaining.setEditable(false);
		tfShowEventPrice.setEditable(false);
		tfShowEventDiscount.setEditable(false);
	}	
	
	public void showEventDetailsFromTable() 
	{

	}
	
	public void showEventDetailsFromListView() 
	{

	}	
	
    @FXML
    void createEvent(ActionEvent event) 
    {
    	System.out.println("Create event");
    }

    @FXML
    void clearCreateEventTextFields(ActionEvent event) 
    {
    	System.out.println("Clear text fields");
    	tfEnterEventTitle.setText("");
    	tfEventStartTime.setText("");
    	tfEventEndTime.setText("");
    	tfEventNumberOfTickets.setText("");
    	tfEventPrice.setText("");
    	tfEventDiscount.setText("");
    	
    	cbEventType.getSelectionModel().select(0);
    	cbEventCategory.getSelectionModel().select(0);
    	cbEventLecturer.getSelectionModel().select(0);
    	
    	dpEventStartDate.getEditor().clear();
    	dpEventStartDate.setValue(null);    	
    	dpEventEndDate.getEditor().clear();
    	dpEventEndDate.setValue(null);
    }

    @FXML
    void searchEvents(ActionEvent event) 
    {
    	System.out.println("Search event");
    }

    @FXML
    void editEvent(ActionEvent event) 
    {
    	hboxEventEditOptions.setVisible(true);
		
		cbShowEventType.setDisable(false);
		cbShowEventCategory.setDisable(false);
		cbShowEventLecturer.setDisable(false);
		
		dpShowEventStartDate.setDisable(false);		
		dpShowEventEndDate.setDisable(false);
		
		tfShowEventTitle.setEditable(true);
		tfShowEventStartTime.setEditable(true);
		tfShowEventEndTime.setEditable(true);
		tfShowEventNumberOfTickets.setEditable(true);
		tfShowEventTicketsRemaining.setEditable(true);
		tfShowEventPrice.setEditable(true);
		tfShowEventDiscount.setEditable(true);
    }
    
    @FXML
    void saveEditEventChanges(ActionEvent event) 
    {
    	hboxEventEditOptions.setVisible(false);
    	
    	cbShowEventType.setDisable(true);
		cbShowEventCategory.setDisable(true);
		cbShowEventLecturer.setDisable(true);
		
		dpShowEventStartDate.setDisable(true);		
		dpShowEventEndDate.setDisable(true);
		
		tfShowEventTitle.setEditable(false);
		tfShowEventStartTime.setEditable(false);
		tfShowEventEndTime.setEditable(false);
		tfShowEventNumberOfTickets.setEditable(false);
		tfShowEventTicketsRemaining.setEditable(false);
		tfShowEventPrice.setEditable(false);
		tfShowEventDiscount.setEditable(false);
    }

    @FXML
    void clearEditEventTextFields(ActionEvent event) 
    {
    	tfShowEventTitle.setText("");
    	tfShowEventStartTime.setText("");
		tfShowEventEndTime.setText("");
		tfShowEventNumberOfTickets.setText("");
		tfShowEventTicketsRemaining.setText("");
		tfShowEventPrice.setText("");
		tfShowEventDiscount.setText("");
		
		dpShowEventStartDate.getEditor().clear();
    	dpShowEventStartDate.setValue(null);    	
    	dpShowEventEndDate.getEditor().clear();
    	dpShowEventEndDate.setValue(null);
    	
    	cbShowEventType.getSelectionModel().select(0);
    	cbShowEventCategory.getSelectionModel().select(0);
    	cbShowEventLecturer.getSelectionModel().select(0);
    }
    
    @FXML
    void cancelEditEvent(ActionEvent event) 
    {
    	System.out.println("Cancel edit event");
    	hboxEventEditOptions.setVisible(false);    	
			
    	cbShowEventType.setDisable(true);
		cbShowEventCategory.setDisable(true);
		cbShowEventLecturer.setDisable(true);
    	
		dpShowEventStartDate.setDisable(true);
		dpShowEventEndDate.setDisable(true);
		
		tfShowEventTitle.setEditable(false);	
		tfShowEventStartTime.setEditable(false);
		tfShowEventEndTime.setEditable(false);
		tfShowEventNumberOfTickets.setEditable(false);
		tfShowEventTicketsRemaining.setEditable(false);
		tfShowEventDiscount.setEditable(false);
		tfShowEventPrice.setEditable(false);
    }

    @FXML
    void deleteEvent(ActionEvent event) 
    {
    	System.out.println("Delete event");
    	
    }

    @FXML
    void addMemberToEvent(ActionEvent event) 
    {
    	System.out.println("Add member to event");
    }

    @FXML
    void removeMemberFromEvent(ActionEvent event) 
    {
    	System.out.println("Remove member from event");
    }
}
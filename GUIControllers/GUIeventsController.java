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

public class GUIeventsController implements Initializable
{
	@FXML private BorderPane eventsPage = new BorderPane();
	
	@FXML private Button btnCreateEvent,btnDeleteEvent,btnCancelEditEvent,btnEditEvent,btnClearTextFields,
							btnSearchEvents,btnSaveEventEditChanges,btnClearEditEventTextFields,btnAddMemberToEvent,
							btnRemoveMemberFromEvent;
	
	@FXML private TextField tfEnterSearchKeywords,tfEventEndTime,tfShowEventPrice,tfShowEventNumberOfTickets,tfShowEventEndTime,
							tfEventNumberOfTickets,tfEventPrice,tfShowEventDiscount,tfEnterEventTitle,tfEventStartTime,
							tfEventDiscount,tfShowEventTicketsRemaining,tfShowEventStartTime,tfSelectedEvent,tfShowEventTitle;

	@FXML private ComboBox<?> cbEventSearchCriteria,cbShowEventCategory,cbEventType,cbShowEventLecturer,cbShowEventType;
	
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
    void createEvent(ActionEvent event) 
    {
    	System.out.println("Create event");
    }

    @FXML
    void clearTextFields(ActionEvent event) 
    {
    	System.out.println("Clear text fields");
    }

    @FXML
    void searchEvents(ActionEvent event) 
    {
    	System.out.println("Search event");
    }

    @FXML
    void saveEditEventChanges(ActionEvent event) 
    {
    	System.out.println("Save edit changes");
    }

    @FXML
    void clearEditEventTextFields(ActionEvent event) 
    {
    	System.out.println("Clear edit event text fields");
    }

    @FXML
    void editEvent(ActionEvent event) 
    {
    	System.out.println("Edit event");
    	hboxEventEditOptions.setVisible(true);

    }
    
    @FXML
    void cancelEditEvent(ActionEvent event) 
    {
    	System.out.println("Cancel edit event");
    	hboxEventEditOptions.setVisible(false);
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUICreateEventPanelController 
{

    @FXML private TextField tfEnterEventTitle,tfEnterEventStartTime,tfEnterEventEndTime,tfEnterEventTicketNumber,tfEnterEventPrice,tfEnterEventDiscount;
        
	@FXML private ComboBox<String> cbSelectType = new ComboBox<>();    
	@FXML private ComboBox<String> cbSelectCategory = new ComboBox<>();	
	@FXML private ComboBox<String> cbSelectLecturer = new ComboBox<>();
	@FXML private ComboBox<String> cbSelectEventSearchCriteria = new ComboBox<>();
    
    @FXML private DatePicker dpSelectEventStartDate = new DatePicker();    
    @FXML private DatePicker dpSelectEventEndDate = new DatePicker();
    
	@FXML private Button btnCreateNewEvent, btnClearCreateEventPanelTextFields, btnCancelCreateEventPanel;
	
	@FXML private RadioButton rbFinalizeEvent;

		
	public void initialize() 
    {    	
        ObservableList<String> eventType = FXCollections.observableArrayList("Lecture","Seminar","Workshop","Journey","None");        
        ObservableList<String> eventCategory = FXCollections.observableArrayList("Dream Interpretation","Healing","Astrology","Reincarnation","Karma","Alternative Health-Care","None");    
        ObservableList<String> eventLecturer = FXCollections.observableArrayList("None");     
        
            
        cbSelectType.setItems(eventType);        
        cbSelectCategory.setItems(eventCategory);    
        cbSelectLecturer.setItems(eventLecturer);
        

        
        cbSelectType.setPromptText("Click to select");        
        cbSelectCategory.setPromptText("Click to select");        
        cbSelectLecturer.setPromptText("Click to select");    
        
        tfEnterEventPrice.setPromptText("00.00");
        tfEnterEventDiscount.setPromptText("00.00%");
        
        
        rbFinalizeEvent.setDisable(true);
    }   	
	
	public void createNewEvent(ActionEvent event)
	{
		System.out.println("Event creation!");
	}
   
    public void clearCreateEventPanelTextFields(ActionEvent event)
    {
    	tfEnterEventTitle.setText(" ");
    	tfEnterEventStartTime.setText(" ");
    	tfEnterEventEndTime.setText(" ");
    	tfEnterEventTicketNumber.setText(" ");    
    	
    	tfEnterEventPrice.setText("00.00");
    	tfEnterEventDiscount.setText("00.00%");

    	cbSelectType.getSelectionModel().clearSelection();
    	cbSelectCategory.getSelectionModel().clearSelection();
    	cbSelectLecturer.getSelectionModel().clearSelection();
    	
    	dpSelectEventStartDate.setValue(null);
    	dpSelectEventEndDate.setValue(null);
    }    
    

    public void setEventStatus(ActionEvent event) 
    {
    	//
    }
	
	public void cancelCreateEventPanel(ActionEvent event)
	{
		Stage stage = (Stage) btnCancelCreateEventPanel.getScene().getWindow();
	    stage.close();
	}
}

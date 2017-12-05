import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUISearchEventsPanelController 
{
    @FXML private TextField tfEventSearchKeyword;
    
    @FXML private TextArea taSearchEventsResults;
	
	@FXML private Button btnSearchEvents,btnOpenSelectedEvent,btnCancelSearchEventsPanel;
	
    @FXML private ComboBox<String> cbSelectEventSearchCriteria;

    public void initialize() 
    {    	
    	ObservableList<String> eventSearchCriteria = FXCollections.observableArrayList("Title", "Type","Category","Lecturer","Start-date","End-date");
    	
        cbSelectEventSearchCriteria.setItems(eventSearchCriteria);
        
        cbSelectEventSearchCriteria.setPromptText("Click to select");
        
        btnOpenSelectedEvent.setDisable(true);
    }   	
    
    public void searchEvents(ActionEvent event) 
    {
    	System.out.println("Searching events");
    }

    public void openSelectedEvent(ActionEvent event) 
    {
    	//
    }

    public void cancelSearchEventsPanel(ActionEvent event) 
    {
    	Stage stage = (Stage) btnCancelSearchEventsPanel.getScene().getWindow();
	    stage.close();
    }
}

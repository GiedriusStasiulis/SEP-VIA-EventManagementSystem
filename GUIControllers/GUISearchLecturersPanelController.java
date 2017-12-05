import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUISearchLecturersPanelController 
{
	@FXML private TextField tfLecturerSearchKeyword;
	
    @FXML private TextArea taLecturerSearchResults;	
    
	@FXML private Button btnSearchLecturers,btnOpenSelectedLecturer,btnCancelSearchLecturerPanel;

	@FXML private ComboBox<String> cbSelectLecturerSearchCriteria;

	public void initialize() 
    {    	
    	ObservableList<String> lecturerSearchCriteria = FXCollections.observableArrayList("Name", "Category","Phone number","E-mail");
    	
    	cbSelectLecturerSearchCriteria.setItems(lecturerSearchCriteria);
        
    	cbSelectLecturerSearchCriteria.setPromptText("Click to select");
        
    	btnOpenSelectedLecturer.setDisable(true);
    }   
	

	public void searchLecturers(ActionEvent event) 
	{
		System.out.println("Searching lecturers");
	}

	public void openSelectedLecturer(ActionEvent event) 
	{
		//
	}

	public void cancelSearchLecturerPanel(ActionEvent event) 
	{
		Stage stage = (Stage) btnCancelSearchLecturerPanel.getScene().getWindow();
	    stage.close();
	}
}

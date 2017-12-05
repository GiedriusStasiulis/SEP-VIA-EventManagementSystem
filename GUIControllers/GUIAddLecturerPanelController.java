import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUIAddLecturerPanelController 
{
	@FXML private TextField tfEnterLecturerName, tfEnterLecturerPhoneNumber, tfEnterLecturerEmail;
	
	@FXML private Button btnAddLecturer, btnClearAddLecturerTextFields, btnCancelAddLecturerPanel;
	
	@FXML private ComboBox<String> cbSelectLecturerCategory = new ComboBox<>();

	public void initialize()
	{
		ObservableList<String> lecturerCategory = FXCollections.observableArrayList("Dream Interpretation","Healing","Astrology","Reincarnation","Karma","Alternative Health-Care","None");    
		
		cbSelectLecturerCategory.setItems(lecturerCategory);
		
		cbSelectLecturerCategory.setPromptText("Click to select");
	}	
	
	public void addLecturer(ActionEvent event) 
	{
		System.out.println("Lecturer added!");
	}

	public void clearAddLecturerTextFields(ActionEvent event) 
	{
		tfEnterLecturerName.setText(" ");
		tfEnterLecturerPhoneNumber.setText(" ");
		tfEnterLecturerEmail.setText(" ");
		
		cbSelectLecturerCategory.getSelectionModel().clearSelection();
	}

	public void cancelAddLecturerPanel(ActionEvent event) 
	{
		Stage stage = (Stage) btnCancelAddLecturerPanel.getScene().getWindow();
	    stage.close();
	}
}

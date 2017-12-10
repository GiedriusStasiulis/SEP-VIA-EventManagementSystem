import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class GUIlecturersController implements Initializable 
{
	@FXML private BorderPane lecturerPage = new BorderPane();
	
	@FXML private Button btnEditLecturer,btnSearchLecturers,btnDeleteLecturer,btnSaveLecturerEditChanges,btnClearEditLecturerTextFields,
    				btnAddLecturer,btnClearAddLecturerTextFields;

    @FXML private TextField tfShowLecturerName,tfEnterLecturerPhoneNumber,tfEnterLecturerSearchKeywords,tfEnterLecturerName,
    						tfEnterLecturerEmail,tfShowLecturerEmail,tfShowLecturerPhoneNumber;
	
    @FXML private TableView<?> sponsorTable;
        
    @FXML private TableColumn<?, ?> tcLecturerCategory,tcLecturerEmail,tcLecturerPhoneNumber,tcLecturerName;

    @FXML private ListView<?> lvLecturerSearchResults;

    @FXML private ComboBox<?> cbLecturerSearchCriteria,cbSelectLecturerCategory,cbShowLecturerCategory;

    @FXML private Label lblLecturerCount;

    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
    	lecturerPage.setMaxHeight(Double.MAX_VALUE);
    	lecturerPage.setMaxWidth(Double.MAX_VALUE);
	}    
    
    @FXML
    void addLecturer(ActionEvent event) 
    {

    }

    @FXML
    void clearAddLecturerTextFields(ActionEvent event) 
    {

    }

    @FXML
    void searchLecturers(ActionEvent event) 
    {

    }

    @FXML
    void saveEditLecturerChanges(ActionEvent event) 
    {

    }

    @FXML
    void clearEditLecturerTextFields(ActionEvent event) 
    {

    }

    @FXML
    void cancelEditLecturer(ActionEvent event) 
    {

    }

    @FXML
    void editLecturer(ActionEvent event) 
    {

    }

    @FXML
    void deleteLecturer(ActionEvent event) 
    {

    }
}

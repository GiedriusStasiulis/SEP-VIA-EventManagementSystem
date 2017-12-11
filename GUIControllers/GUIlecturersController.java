import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Controller class for GUIlecturers.fxml. Handles all ActionEvent and MouseEvents
 * that occur on the lecturers page in the application. * 
 * @author Group#2 *
 */

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
    
    @FXML private HBox hboxLecturerEditOptions;

    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
    	lecturerPage.setMaxHeight(Double.MAX_VALUE);
    	lecturerPage.setMaxWidth(Double.MAX_VALUE);
    	
    	hboxLecturerEditOptions.setVisible(false);
    	
    	tcLecturerCategory.setStyle("-fx-alignment: CENTER;");
    	tcLecturerEmail.setStyle("-fx-alignment: CENTER;");
    	tcLecturerPhoneNumber.setStyle("-fx-alignment: CENTER;");
    	tcLecturerName.setStyle("-fx-alignment: CENTER;");
    	
    	cbShowLecturerCategory.setDisable(true);
    	
    	tfShowLecturerName.setEditable(false);
    	tfShowLecturerEmail.setEditable(false);
    	tfShowLecturerPhoneNumber.setEditable(false);
	}    
    
    public void showLecturerDetailsFromTable() 
	{

	}
	
	public void showLecturerDetailsFromListView() 
	{

	}	    
    
    @FXML
    void addLecturer(ActionEvent event) 
    {

    }

    @FXML
    void clearAddLecturerTextFields(ActionEvent event) 
    {
    	tfEnterLecturerName.setText("");
    	tfEnterLecturerEmail.setText("");
    	tfEnterLecturerPhoneNumber.setText("");
    	
    	cbSelectLecturerCategory.getSelectionModel().select(0);
    }

    @FXML
    void searchLecturers(ActionEvent event) 
    {

    }
    
    @FXML
    void editLecturer(ActionEvent event) 
    {
    	hboxLecturerEditOptions.setVisible(true);
    	
    	cbShowLecturerCategory.setDisable(false);
    	
    	tfShowLecturerName.setEditable(true);
    	tfShowLecturerEmail.setEditable(true);
    	tfShowLecturerPhoneNumber.setEditable(true);
    	
    	tfShowLecturerName.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
    	tfShowLecturerEmail.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
    	tfShowLecturerPhoneNumber.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
    	
    	cbShowLecturerCategory.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
    }

    @FXML
    void saveEditLecturerChanges(ActionEvent event) 
    {
    	hboxLecturerEditOptions.setVisible(false);
    	
    	cbShowLecturerCategory.setDisable(true);
    	
    	tfShowLecturerName.setEditable(false);
    	tfShowLecturerEmail.setEditable(false);
    	tfShowLecturerPhoneNumber.setEditable(false);
    	
    	tfShowLecturerName.setStyle("-fx-border-width: 0px ;");
    	tfShowLecturerEmail.setStyle("-fx-border-width: 0px ;");
    	tfShowLecturerPhoneNumber.setStyle("-fx-border-width: 0px ;");
    	
    	cbShowLecturerCategory.setStyle("-fx-border-width: 0px ;");
    }

    @FXML
    void clearEditLecturerTextFields(ActionEvent event) 
    {
    	tfShowLecturerName.setText("");
    	tfShowLecturerEmail.setText("");
    	tfShowLecturerPhoneNumber.setText("");
    	
    	cbShowLecturerCategory.getSelectionModel().select(0);
    }

    @FXML
    void cancelEditLecturer(ActionEvent event) 
    {
    	hboxLecturerEditOptions.setVisible(false);   	
    	
    	tfShowLecturerName.setEditable(false);
    	tfShowLecturerEmail.setEditable(false);
    	tfShowLecturerPhoneNumber.setEditable(false);
    	
    	cbShowLecturerCategory.setDisable(true);
    	
    	tfShowLecturerName.setStyle("-fx-border-width: 0px ;");
    	tfShowLecturerEmail.setStyle("-fx-border-width: 0px ;");
    	tfShowLecturerPhoneNumber.setStyle("-fx-border-width: 0px ;");
    	
    	cbShowLecturerCategory.setStyle("-fx-border-width: 0px ;");
    }    

    @FXML
    void deleteLecturer(ActionEvent event) 
    {

    }
}
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Controller class for GUIsponsors.fxml. Handles all ActionEvent and MouseEvents
 * that occur on the sponsors page in the application. * 
 * @author Group#2 *
 */

public class GUIsponsorsController implements Initializable 
{	
	@FXML private BorderPane sponsorPage = new BorderPane();
	
    @FXML private Button btnAddSponsor,btnDeleteSponsor,btnEditSponsor,btnClearAddSponsorTextFields,btnSearchSponsors,
    				btnCancelEditSponsor,btnClearEditSponsorTextFields,btnSaveSponsorEditChanges;

    @FXML private TextField tfShowSponsorEmail,tfEnterSponsorSearchKeywords,tfEnterSponsorEmail,tfShowSponsorName,
    						tfShowSponsorPhoneNumber,tfEnterSponsorName,tfEnterSponsorPhoneNumber;
    
    @FXML private TableView<?> sponsorTable;
    
    @FXML private TableColumn<?, ?> tcSponsorName,tcSponsorEmail,tcSponsorPhoneNumber;
        
    @FXML private ListView<?> lvSponsorSearchResults;

    @FXML private ComboBox<?> cbSponsorSearchCriteria;
    
    @FXML private Label lblSponsorCount;
    
    @FXML private HBox hboxSponsorEditOptions;

    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
    	sponsorPage.setMaxHeight(Double.MAX_VALUE);
    	sponsorPage.setMaxWidth(Double.MAX_VALUE);
    	
    	hboxSponsorEditOptions.setVisible(false);
    	
    	tcSponsorName.setStyle("-fx-alignment: CENTER;");
    	tcSponsorEmail.setStyle("-fx-alignment: CENTER;");
    	tcSponsorPhoneNumber.setStyle("-fx-alignment: CENTER;");
    	
    	tfShowSponsorName.setEditable(false);
    	tfShowSponsorEmail.setEditable(false);
    	tfShowSponsorPhoneNumber.setEditable(false);
	}
    
    public void showSponsorDetailsFromTable() 
	{

	}
	
	public void showSponsorDetailsFromListView() 
	{

	}	
    
    @FXML
    void addSponsor(ActionEvent event) 
    {

    }

    @FXML
    void clearAddSponsorTextFields(ActionEvent event) 
    {
    	tfEnterSponsorName.setText("");
    	tfEnterSponsorEmail.setText("");
    	tfEnterSponsorPhoneNumber.setText("");
    }

    @FXML
    void searchSponsors(ActionEvent event) 
    {

    }

    @FXML
    void editSponsor(ActionEvent event) 
    {
    	hboxSponsorEditOptions.setVisible(true);
    	
    	tfShowSponsorName.setEditable(true);
    	tfShowSponsorEmail.setEditable(true);
    	tfShowSponsorPhoneNumber.setEditable(true);
    }
    
    @FXML
    void saveEditSponsorChanges(ActionEvent event) 
    {
    	hboxSponsorEditOptions.setVisible(false);
    	
    	tfShowSponsorName.setEditable(false);
    	tfShowSponsorEmail.setEditable(false);
    	tfShowSponsorPhoneNumber.setEditable(false);
    }

    @FXML
    void clearEditSponsorTextFields(ActionEvent event) 
    {
    	tfShowSponsorName.setText("");
    	tfShowSponsorEmail.setText("");
    	tfShowSponsorPhoneNumber.setText("");
    }

    @FXML
    void cancelEditSponsor(ActionEvent event) 
    {
    	hboxSponsorEditOptions.setVisible(false);
    	
    	tfShowSponsorName.setEditable(false);
    	tfShowSponsorEmail.setEditable(false);
    	tfShowSponsorPhoneNumber.setEditable(false);
    }    

    @FXML
    void deleteSponsor(ActionEvent event) 
    {

    }
}
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

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

    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
    	sponsorPage.setMaxHeight(Double.MAX_VALUE);
    	sponsorPage.setMaxWidth(Double.MAX_VALUE);
	}
    
    @FXML
    void addSponsor(ActionEvent event) 
    {

    }

    @FXML
    void clearAddSponsorTextFields(ActionEvent event) 
    {

    }

    @FXML
    void searchSponsors(ActionEvent event) 
    {

    }

    @FXML
    void saveEditSponsorChanges(ActionEvent event) 
    {

    }

    @FXML
    void clearEditSponsorTextFields(ActionEvent event) 
    {

    }

    @FXML
    void cancelEditSponsor(ActionEvent event) 
    {

    }

    @FXML
    void editSponsor(ActionEvent event) 
    {

    }

    @FXML
    void deleteSponsor(ActionEvent event) 
    {

    }
}

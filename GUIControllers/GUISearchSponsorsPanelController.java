import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUISearchSponsorsPanelController 
{
    @FXML private TextField tfSponsorSearchKeyword;
    
    @FXML private TextArea taSearchSponsorsResults;
	
	@FXML private Button btnSearchSponsors,btnOpenSelectedSponsor,btnCancelSearchSponsorPanel;

    @FXML private ComboBox<String> cbSelectSponsorSearchCriteria;
    
    public void initialize() 
    {    	
    	ObservableList<String> sponsorSearchCriteria = FXCollections.observableArrayList("Name", "Phone number","E-mail");
    	
    	cbSelectSponsorSearchCriteria.setItems(sponsorSearchCriteria);
        
    	cbSelectSponsorSearchCriteria.setPromptText("Click to select");
        
    	btnOpenSelectedSponsor.setDisable(true);
    }   

    public void searchSponsors(ActionEvent event) 
    {
    	System.out.println("Searching sponsors");
    }

    public void openSelectedSponsor(ActionEvent event) 
    {
    	//
    }

    public void cancelSearchSponsorPanel(ActionEvent event) 
    {
    	Stage stage = (Stage) btnCancelSearchSponsorPanel.getScene().getWindow();
	    stage.close();
    }
}

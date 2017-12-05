import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUIAddSponsorPanelController 
{

    @FXML private TextField tfEnterSponsorName,tfEnterSponsorPhoneNumber,tfEnterSponsorEmail;

    @FXML private Button btnAddSponsor,btnClearAddSponsorTextFields,btnCancelAddSponsorPanel;
    
    
    public void addSponsor(ActionEvent event) 
    {
    	System.out.println("Sponsor added!");
    }

    public void clearAddSponsorTextFields(ActionEvent event) 
    {
    	tfEnterSponsorName.setText(" ");
    	tfEnterSponsorPhoneNumber.setText(" ");
    	tfEnterSponsorEmail.setText(" ");
    }

    public void cancelAddSponsorPanel(ActionEvent event) 
    {
		Stage stage = (Stage) btnCancelAddSponsorPanel.getScene().getWindow();
	    stage.close();
    }

}

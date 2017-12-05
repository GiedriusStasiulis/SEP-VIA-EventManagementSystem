import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUISearchMembersPanelController 
{
    @FXML private TextField tfMemberSearchKeyword;

    @FXML private TextArea taMemberSearchResults;
        
    @FXML private Button btnSearchMembers,btnOpenSelectedMember,btnCancelSearchMemberPanel;

    @FXML private ComboBox<String> cbSelectMemberSearchCriteria;

	public void initialize() 
    {    	
    	ObservableList<String> memberSearchCriteria = FXCollections.observableArrayList("Name", "E-mail");
    	
    	cbSelectMemberSearchCriteria.setItems(memberSearchCriteria);
        
    	cbSelectMemberSearchCriteria.setPromptText("Click to select");
        
    	btnOpenSelectedMember.setDisable(true);
    }   
    
    
    public void searchMembers(ActionEvent event) 
    {
    	System.out.println("Searching members");
    }

    public void openSelectedMember(ActionEvent event) 
    {
    	//
    }

    public void cancelSearchMemberPanel(ActionEvent event) 
    {
		Stage stage = (Stage) btnCancelSearchMemberPanel.getScene().getWindow();
	    stage.close();
    }
}

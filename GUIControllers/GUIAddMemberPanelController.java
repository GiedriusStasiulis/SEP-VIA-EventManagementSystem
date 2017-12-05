import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUIAddMemberPanelController 
{
	
	@FXML private TextField tfEnterMemberName,tfEnterMemberEmail;
	
	@FXML private Button btnAddMember,btnClearAddMemberTextFields,btnCancelAddMemberPanel;

    public void addMember(ActionEvent event) 
    {
    	System.out.println("Member added!");
	}

	public void clearAddMemberTextFields(ActionEvent event) 
	{
		tfEnterMemberName.setText(" ");
		tfEnterMemberEmail.setText(" ");
	}

	public void cancelAddMemberPanel(ActionEvent event) 
	{
		Stage stage = (Stage) btnCancelAddMemberPanel.getScene().getWindow();
	    stage.close();
	}

}

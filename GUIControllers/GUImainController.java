import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.acl.Group;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controller class for GUImain.fxml. Each Tabbed Pane tab in GUImain.fxml is encapsulated with a 
 * separate FXML file with it's own associated controller class. fx:include directive tells the FXMLLoader to 
 * load the FXML file that is being referenced. 
 * @author Group#2
 */

public class GUImainController implements Initializable 
{
	
	@FXML private Button btnOpenUserGuide;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		// TODO Auto-generated method stub		
	}
	
	@FXML 
	public void openUserGuide()
	{
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File("VIAuserGuide.pdf");
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
	}	
	
}





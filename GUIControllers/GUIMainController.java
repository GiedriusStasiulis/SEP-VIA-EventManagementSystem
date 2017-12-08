import java.io.IOException;
import java.net.URL;
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

public class GUImainController
{
	@FXML GUImembersController childController;
	
    @FXML TabPane TabPane;

    @FXML Tab eventsTab;

    @FXML Tab sponsorsTab;

    @FXML Tab membersTab;

    @FXML Tab homeTab;

    @FXML Tab lecturersTab;

    @FXML
    private BorderPane membersBorderPane = new BorderPane();

    public void initialize()
    {
    	
    }


}





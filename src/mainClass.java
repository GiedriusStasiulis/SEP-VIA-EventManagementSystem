import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class mainClass extends Application
{
	
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setController(new GUIMainController());
		
		loader.setLocation(getClass().getResource("FXML/viaGUI.fxml"));
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		
		stage.setTitle("VIA - Event Management System");	
		
		stage.setScene(scene);
		stage.show();
	}

}

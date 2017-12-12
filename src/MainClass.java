import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application 
{	
	public FXMLLoader loader;
	
	public static void main(String[] args)
	{
		launch(args);
	}	
	
	public void start(Stage stage) throws Exception 
	{
		loader = new FXMLLoader();		
		loader.setLocation(getClass().getResource("FXML/GUImain.fxml"));
		loader.setController(new GUImainController());
		
		Parent root = loader.load();					
		Scene scene = new Scene(root);		

		stage.setTitle("VIA - Organization Management System");
		stage.setScene(scene);
		stage.show();		
		
		//TEST
	}
}
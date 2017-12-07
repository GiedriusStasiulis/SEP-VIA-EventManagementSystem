import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GUIAddMemberPanelController 
{
	private Member member;
	private String filename = "MemberList.txt";
	private MemberList memberList = new MemberList();
	private MemberFile memberFile = new MemberFile(filename);	
	
	private GUIMainController tempGUI;	
	private GUIMainController realGUI;	

	@FXML private TextField tfEnterMemberName,tfEnterMemberEmail;
	
	@FXML private Button btnAddMember,btnClearAddMemberTextFields,btnCancelAddMemberPanel;
	
	
    public void addMember(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException 
    {
    	String memberName = tfEnterMemberName.getText();
    	String memberEmail = tfEnterMemberEmail.getText();
		member = new Member(memberName,memberEmail);
		memberList.addMemberToList(member);
		memberFile.writeMemberTextFile(memberList);
				
		 FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("FXML/viaGUI.fxml"));
	     GUIMainController guiMainController = new GUIMainController();
	     loader.setController(guiMainController);        
	     Parent root = loader.load();
	     	     
	     guiMainController.setText("Clicked"); 
	     
	     cancelAddMemberPanel(event);  
    }      


	public void clearAddMemberTextFields(ActionEvent event) 
	{
		tfEnterMemberName.setText("");
		tfEnterMemberEmail.setText("");		
	}

	public void cancelAddMemberPanel(ActionEvent event) throws FileNotFoundException, ParseException
	{
		Stage stage = (Stage) btnCancelAddMemberPanel.getScene().getWindow();
	    stage.close();	    
	}

}

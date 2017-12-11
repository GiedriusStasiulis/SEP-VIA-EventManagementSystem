import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Controller class for GUIlecturers.fxml. Handles all ActionEvent and MouseEvents
 * that occur on the lecturers page in the application. * 
 * @author Group#2 *
 */

public class GUIlecturersController implements Initializable 
{
   private Lecturer lecturer;
   private Lecturer selectedLecturer;
   private LecturerList lecturerList=new LecturerList();
   private String filename="LecturerList.txt";
   private LecturerFile lecturerFile= new LecturerFile(filename);
   
   private ObservableList<Lecturer> lecturers = FXCollections.observableArrayList();
   private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name","E-mail");
   private ObservableList<String> lecturerCategory = FXCollections.observableArrayList("Dream Interpretation","Healing","Astrology","Reincarnation","Karma","Alternative Health-Care","None");
   
   @FXML private BorderPane lecturerPage = new BorderPane();
   
   @FXML private Button btnEditLecturer,btnSearchLecturers,btnDeleteLecturer,btnSaveLecturerEditChanges,btnClearEditLecturerTextFields,
               btnAddLecturer,btnClearAddLecturerTextFields;

    @FXML private TextField tfShowLecturerName,tfEnterLecturerPhoneNumber,tfEnterLecturerSearchKeywords,tfEnterLecturerName,
                     tfEnterLecturerEmail,tfShowLecturerEmail,tfShowLecturerPhoneNumber;
   
    @FXML private TableView<Lecturer> lecturerTable;
        
    @FXML private TableColumn<Lecturer, String> tcLecturerCategory,tcLecturerEmail,tcLecturerPhoneNumber,tcLecturerName;

    @FXML private ListView<Lecturer> lvLecturerSearchResults;

    @FXML private ComboBox<String> cbLecturerSearchCriteria,cbSelectLecturerCategory,cbShowLecturerCategory;

    @FXML private Label lblLecturerCount;

    @Override
   public void initialize(URL location, ResourceBundle resources) 
   {
      lecturerPage.setMaxHeight(Double.MAX_VALUE);
      lecturerPage.setMaxWidth(Double.MAX_VALUE);
      
      cbLecturerSearchCriteria.setItems(searchCriteria);
      cbLecturerSearchCriteria.getSelectionModel().select(0);
      
      cbSelectLecturerCategory.setItems(lecturerCategory);
      cbSelectLecturerCategory.getSelectionModel().select(0);
      
      tcLecturerName.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("name"));
      tcLecturerCategory.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("category"));
      tcLecturerPhoneNumber.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("phone number"));
      tcLecturerEmail.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("email"));

      tcLecturerName.setStyle( "-fx-alignment: CENTER;");
      tcLecturerCategory.setStyle( "-fx-alignment: CENTER;");
      tcLecturerPhoneNumber.setStyle( "-fx-alignment: CENTER;");
      tcLecturerEmail.setStyle( "-fx-alignment: CENTER;");
      
      /*try
      {
         lecturerTable.setItems(getList());
      }
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      } 
      catch (ParseException e) 
      {
         e.printStackTrace();
      }*/
      
      lecturerTable.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() == 1) {
           showLecturerDetailsFromTable();
         }
     });
      lvLecturerSearchResults.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() == 1) {
           showLecturerDetailsFromListView();
         }
         
     });
      lblLecturerCount.setText(String.format("Lecturer count: %d", lecturerList.size()));
      
   }  
    public void showLecturerDetailsFromTable() {

       if (lecturerTable.getSelectionModel().getSelectedItem() != null) {
           selectedLecturer = lecturerTable.getSelectionModel().getSelectedItem();
           tfShowLecturerName.setText(selectedLecturer.getName());
           tfShowLecturerEmail.setText(selectedLecturer.getEmail());
       }
   }
   
   public void showLecturerDetailsFromListView() {

       if (lvLecturerSearchResults.getSelectionModel().getSelectedItem() != null) {
           selectedLecturer = lvLecturerSearchResults.getSelectionModel().getSelectedItem();
           System.out.println(selectedLecturer);
           tfShowLecturerName.setText(selectedLecturer.getName());
           tfShowLecturerEmail.setText(selectedLecturer.getEmail());
       }
   }
    
    @FXML
    void addLecturer(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException
    {
       String lecturerName = tfEnterLecturerName.getText();
       
       String lecturerPhoneNumber = tfEnterLecturerPhoneNumber.getText();
       int tempLecturerPhoneNumber=Integer.parseInt(lecturerPhoneNumber);
       String lecturerCategory=cbSelectLecturerCategory.getSelectionModel().getSelectedItem();
       String lecturerEmail = tfEnterLecturerEmail.getText();
       if(tfEnterLecturerName.getText().isEmpty())
       {
          lecturerName = "empty";
       }
       
       if(tfEnterLecturerEmail.getText().isEmpty())
       {
          lecturerEmail = "empty";
       } 
       if(tfEnterLecturerPhoneNumber.getText().isEmpty())
       {
          lecturerPhoneNumber ="empty";
       }

          lecturer = new Lecturer(lecturerName, lecturerCategory,tempLecturerPhoneNumber,lecturerEmail);      
          lecturerList.addLecturer(lecturer);   
          lecturerFile.writeTextFile(lecturerList);     
          lecturerTable.getItems().add(lecturer);
          clearAddLecturerTextFields(event);
          
          lblLecturerCount.setText(String.format("Lecturer count: %d", lecturerList.size()));
    }

    @FXML
    void clearAddLecturerTextFields(ActionEvent event) 
    {
       tfEnterLecturerName.setText("");
       tfEnterLecturerPhoneNumber.setText("");
       tfEnterLecturerEmail.setText("");

    }

    @FXML
    void searchLecturers(ActionEvent event) 
    {

    }

    @FXML
    void saveEditLecturerChanges(ActionEvent event) 
    {

    }

    @FXML
    void clearEditLecturerTextFields(ActionEvent event) 
    {

    }

    @FXML
    void cancelEditLecturer(ActionEvent event) 
    {

    }

    @FXML
    void editLecturer(ActionEvent event) 
    {

    }

    @FXML
    void deleteLecturer(ActionEvent event) 
    {

    }
    public ObservableList<Lecturer> getList() throws FileNotFoundException, ParseException 
    {
       lecturerList = lecturerFile.readLecturerTextFile();

       for (int i = 0; i < lecturerList.size(); i++) 
       {
          
          lecturers.add(new Lecturer(lecturerList.getLecturer(i).getName(), lecturerList.getLecturer(i).getCategory(),lecturerList.getLecturer(i).getPhoneNumber(), lecturerList.getLecturer(i).getEmail()));
       }

       return lecturers;
    }
}

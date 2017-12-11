import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
 * Controller class for GUIlecturers.fxml. Handles all ActionEvent and
 * MouseEvents that occur on the lecturers page in the application. *
 * 
 * @author Group#2 *
 */

public class GUIlecturersController implements Initializable {
	private Lecturer lecturer;
	private Lecturer selectedLecturer;
	private LecturerList lecturerList = new LecturerList();
	private String filename = "LecturerList.txt";
	private LecturerFile lecturerFile = new LecturerFile(filename);

	private ObservableList<Lecturer> lecturers = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name","Category","Phone number", "E-mail");
	private ObservableList<String> lecturerCategory = FXCollections.observableArrayList("Dream Interpretation",
			"Healing", "Astrology", "Reincarnation", "Karma", "Alternative Health-Care", "None");
	

	@FXML
	private BorderPane lecturerPage = new BorderPane();

	@FXML
	private Button btnEditLecturer, btnSearchLecturers, btnDeleteLecturer, btnSaveLecturerEditChanges,
			btnClearEditLecturerTextFields, btnAddLecturer, btnClearAddLecturerTextFields;

	@FXML
	private TextField tfShowLecturerName, tfEnterLecturerPhoneNumber, tfEnterLecturerSearchKeywords,
			tfEnterLecturerName, tfEnterLecturerEmail, tfShowLecturerEmail, tfShowLecturerPhoneNumber;

	@FXML
	private TableView<Lecturer> lecturerTable;

	@FXML
	private TableColumn<Lecturer, String> tcLecturerEmail, tcLecturerCategory, tcLecturerPhoneNumber, tcLecturerName;

	@FXML
	private ListView<Lecturer> lvLecturerSearchResults;

	@FXML
	private ComboBox<String> cbLecturerSearchCriteria, cbSelectLecturerCategory, cbShowLecturerCategory;

	@FXML
	private Label lblLecturerCount;
	
	@FXML
   private HBox hboxLecturerEditOptions;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lecturerPage.setMaxHeight(Double.MAX_VALUE);
		lecturerPage.setMaxWidth(Double.MAX_VALUE);

		cbLecturerSearchCriteria.setItems(searchCriteria);
		cbLecturerSearchCriteria.getSelectionModel().select(0);

		cbSelectLecturerCategory.setItems(lecturerCategory);
		cbSelectLecturerCategory.getSelectionModel().select(0);

		tcLecturerName.setCellValueFactory(new PropertyValueFactory<Lecturer,String>("name"));
		tcLecturerCategory.setCellValueFactory(new PropertyValueFactory<Lecturer,String>("category"));
		tcLecturerPhoneNumber.setCellValueFactory(new PropertyValueFactory<Lecturer,String>("phoneNumber"));
		tcLecturerEmail.setCellValueFactory(new PropertyValueFactory<Lecturer,String>("email"));

		tcLecturerName.setStyle("-fx-alignment: CENTER;");
		tcLecturerCategory.setStyle( "-fx-alignment: CENTER;");
		tcLecturerPhoneNumber.setStyle("-fx-alignment: CENTER;");
		tcLecturerEmail.setStyle("-fx-alignment: CENTER;");

		try {
			lecturerTable.setItems(getList());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lecturerTable.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
				showLecturerDetailsFromTable();
				System.out.println("Table clicked");
			}
		});

		lvLecturerSearchResults.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
				showLecturerDetailsFromListView();
			}

		});

		lblLecturerCount.setText(String.format("Lecturer count: %d", lecturerList.size()));

	}

	public ObservableList<Lecturer> getList() throws FileNotFoundException, ParseException {
		
		lecturerList = lecturerFile.readLecturerTextFile();
		
		System.out.println(lecturerList.toString());

		for (int i = 0; i < lecturerList.size(); i++) {

			lecturers.add(new Lecturer(lecturerList.getLecturer(i).getName(), lecturerList.getLecturer(i).getCategory(),
					lecturerList.getLecturer(i).getPhoneNumber(), lecturerList.getLecturer(i).getEmail()));
		}

		return lecturers;
	}

	public void showLecturerDetailsFromTable() {
		if (lecturerTable.getSelectionModel().getSelectedItem() != null) {
			selectedLecturer = lecturerTable.getSelectionModel().getSelectedItem();
			tfShowLecturerName.setText(selectedLecturer.getName());
			tfShowLecturerEmail.setText(selectedLecturer.getEmail());
			tfShowLecturerPhoneNumber.setText(selectedLecturer.getPhoneNumber());
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
		String lecturerCategory = cbSelectLecturerCategory.getSelectionModel().getSelectedItem();
		String lecturerPhoneNumber = tfEnterLecturerPhoneNumber.getText();
		String lecturerEmail = tfEnterLecturerEmail.getText();

		if(tfEnterLecturerName.getText().isEmpty() && tfEnterLecturerPhoneNumber.getText().isEmpty() && tfEnterLecturerEmail.getText().isEmpty())
		{
			lecturerName = String.format("empty%d", lecturerList.size() + 1);
			lecturerPhoneNumber = String.format("empty%d", lecturerList.size() + 1);
			lecturerEmail = String.format("empty@empty%d", lecturerList.size() + 1);
		}		
		
		else if(tfEnterLecturerName.getText().isEmpty())
		{
			lecturerName = String.format("empty%d", lecturerList.size() + 1);
		}
		
		else if(tfEnterLecturerPhoneNumber.getText().isEmpty())
		{
			lecturerPhoneNumber = String.format("empty@%d", lecturerList.size() + 1);
		}
		
		else if(tfEnterLecturerEmail.getText().isEmpty())
		{
			lecturerEmail = String.format("empty@empty%d", lecturerList.size() + 1);
		}
		
		if(lecturerEmail.contains("@"))
		{		
			lecturer = new Lecturer(lecturerName,lecturerCategory,lecturerPhoneNumber,lecturerEmail);	
			
			System.out.println(lecturer);
			
			if(lecturerList.checkForDuplicates(lecturerList, lecturer))
			{
				JOptionPane.showMessageDialog(null, "Lecturer already exists in the system!");
				clearAddLecturerTextFields(event);
			}
			
			else
			{
				lecturerList.addLecturerToList(lecturer);		
				lecturerFile.writeLecturerTextFile(lecturerList);		
				lecturerTable.getItems().add(lecturer);
				clearAddLecturerTextFields(event);
					
				lblLecturerCount.setText(String.format("Lecturer count: %d", lecturerList.size()));
			}
		}
		
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
			clearAddLecturerTextFields(event);
		}		
	}

	@FXML
	void clearAddLecturerTextFields(ActionEvent event) {
      tfEnterLecturerName.setText("");
      tfEnterLecturerPhoneNumber.setText("");
      tfEnterLecturerEmail.setText("");
	}

	@FXML
	void searchLecturers(ActionEvent event) {
      ObservableList<Lecturer> searchResults= FXCollections.observableArrayList();
      int searchCriteriaComboBoxSelection = cbLecturerSearchCriteria.getSelectionModel().getSelectedIndex();     
      String searchKeyword = tfEnterLecturerSearchKeywords.getText();     
      tfEnterLecturerSearchKeywords.setText("");
      
      searchResults.clear();
      
      switch(searchCriteriaComboBoxSelection)
      {
         case 0:
            
            for(int i = 0; i < lecturers.size(); i ++)
            {
               if (lecturers.get(i).getName().toLowerCase().contains(searchKeyword))
               {
                  searchResults.add(lecturers.get(i));
               }
            }
            
            if(searchResults.isEmpty())
            {
               JOptionPane.showMessageDialog(null, "No lecturers found with the given search keyword: \n" + searchKeyword);
            }
            
            break;
            
         case 1:
            
            for(int i = 0; i < lecturers.size(); i ++)
            {
               if (lecturers.get(i).getEmail().toLowerCase().contains(searchKeyword))
               {
                  searchResults.add(lecturers.get(i));
               }
            }
            
            if(searchResults.isEmpty())
            {
               JOptionPane.showMessageDialog(null, "No lecturers found with the given search keyword: " + searchKeyword);
            }
            
            break;
         case 2:
            for (int i = 0; i < lecturers.size(); i++)
            {
               if (lecturers.get(i).getPhoneNumber().toLowerCase()
                     .contains(searchKeyword))
               {
                  searchResults.add(lecturers.get(i));
               }
            }

            if (searchResults.isEmpty())
            {
               JOptionPane.showMessageDialog(null,
                     "No lecturers found with the given search keyword: \n"
                           + searchKeyword);
            }
            break;
         case 3:
            for (int i = 0; i < lecturers.size(); i++)
            {
               if (lecturers.get(i).getCategory().toLowerCase().contains(searchKeyword))
               {
                  searchResults.add(lecturers.get(i));
               }
            }

            if (searchResults.isEmpty())
            {
               JOptionPane.showMessageDialog(null,
                     "No lecturers found with the given search keyword: \n"
                           + searchKeyword);
            }

            default:             
               break;         
      }  
      
      lvLecturerSearchResults.setItems(searchResults);  
    
	}

	@FXML
	void saveEditLecturerChanges(ActionEvent event) throws FileNotFoundException, ParseException {
	   int index = lecturerList.getLecturerIndex(selectedLecturer);   
	     
	     String newLecturerName = tfShowLecturerName.getText();
	     String newLecturerEmail = tfShowLecturerEmail.getText();
	     String newLecturerPhone = tfShowLecturerPhoneNumber.getText();
	     
	     if(tfShowLecturerName.getText().isEmpty())
	     {
	        newLecturerName = "empty";
	     }
	     
	     if(tfShowLecturerEmail.getText().isEmpty())
	     {
	        newLecturerEmail = "empty";
	     }  
	     
	     if(tfShowLecturerPhoneNumber.getText().isEmpty())
	     {
	        newLecturerPhone = "empty";
	     }  
	     
	     selectedLecturer.setName(newLecturerName);
	     selectedLecturer.setEmail(newLecturerEmail);
	     selectedLecturer.setPhoneNumber(newLecturerPhone);

	     lecturerList.replaceLecturer(index,selectedLecturer);       
	     lecturerFile.writeLecturerTextFile(lecturerList);

	     lecturerTable.getItems().set(index, selectedLecturer);

	     hboxLecturerEditOptions.setVisible(false);
	     tfShowLecturerName.setEditable(false);
	     tfShowLecturerEmail.setEditable(false);
	     tfShowLecturerPhoneNumber.setEditable(false);
	     
	     tfShowLecturerName.setStyle("-fx-border-width: 0px ;");
	     tfShowLecturerEmail.setStyle("-fx-border-width: 0px ;");
	     tfShowLecturerPhoneNumber.setStyle("-fx-border-width: 0px ;");
	}

	@FXML
	void clearEditLecturerTextFields(ActionEvent event) {
	   tfShowLecturerName.setText("");
      tfShowLecturerEmail.setText("");
      tfShowLecturerPhoneNumber.setText("");
	}

	@FXML
	void cancelEditLecturer(ActionEvent event) {
	   hboxLecturerEditOptions.setVisible(false);

      tfShowLecturerName.setEditable(false);
      tfShowLecturerEmail.setEditable(false);
      tfShowLecturerPhoneNumber.setEditable(false);

      tfShowLecturerName.setStyle("-fx-border-width: 0px ;");
      tfShowLecturerEmail.setStyle("-fx-border-width: 0px ;");
      tfShowLecturerPhoneNumber.setStyle("-fx-border-width: 0px ;");

	}

	@FXML
	void editLecturer(ActionEvent event) {
	   if (lecturerTable.getSelectionModel() != null)
      {
         System.out.println(lecturerTable.getSelectionModel());
         hboxLecturerEditOptions.setVisible(true);

         tfShowLecturerName.setEditable(true);
         tfShowLecturerEmail.setEditable(true);
         tfShowLecturerPhoneNumber.setEditable(true);

         tfShowLecturerName
               .setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
         tfShowLecturerEmail
               .setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
         tfShowLecturerPhoneNumber
               .setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
      }

	}

	@FXML
	void deleteLecturer(ActionEvent event) throws FileNotFoundException {
	   if(lecturerTable.getSelectionModel() != null)
	     {
	        if(lecturerList.size() > 0)
	        {
	           try 
	           {
	              String[] options = {"Delete","Cancel"}; 
	              int n = JOptionPane.showOptionDialog(null,
	                        "Are you sure you want to delete lecturer:\n" + selectedLecturer + " ?",
	                        "Delete a lecturer",
	                        JOptionPane.YES_NO_OPTION,
	                        JOptionPane.QUESTION_MESSAGE,
	                        null,
	                        options,
	                        options[0]);              
	              
	              if (n == JOptionPane.YES_OPTION) 
	              {
	                 int index = lecturerList.getLecturerIndex(selectedLecturer);
	                 
	                 lecturerList.deleteLecturer(index);
	                 lecturerFile.writeLecturerTextFile(lecturerList);
	                 lecturerTable.getItems().remove(index);
	                 
	                 tfShowLecturerName.setText("");
	                 tfShowLecturerEmail.setText("");   
	                 tfShowLecturerPhoneNumber.setText("");   
	                 
	                 lblLecturerCount.setText(String.format("lecturer count: %d", lecturerList.size()));
	              }        
	           }
	           catch(ArrayIndexOutOfBoundsException e)
	           {
	              JOptionPane.showMessageDialog(null, "Please select a lecturer from the table to delete");
	           }
	        }        
	        else
	        {
	           JOptionPane.showMessageDialog(null, "No more lecturers left to delete");
	        }
	     }     
	     else
	     {
	        JOptionPane.showMessageDialog(null, "Please select a lecturer from the table to delete");
	     }     
	   }    

	}


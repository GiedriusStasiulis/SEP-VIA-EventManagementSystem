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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Controller class for GUIlecturers.fxml. Handles all ActionEvent and
 * MouseEvents that occur on the lecturers page in the application. *
 * 
 * @author Group#2 *
 */

public class GUIlecturersController implements Initializable 
{
	private VIAoms viaOms = new VIAoms();

	private ObservableList<Lecturer> lecturers = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name", "Category",
			"Phone number", "E-mail");
	private ObservableList<String> lecturerCategory = FXCollections.observableArrayList("Dream Interpretation",
			"Healing", "Astrology", "Reincarnation", "Karma", "Alternative Health-Care", "None");

	@FXML private BorderPane lecturerPage = new BorderPane();

	@FXML private Button btnEditLecturer, btnSearchLecturers, btnDeleteLecturer, btnSaveLecturerEditChanges,
			btnClearEditLecturerTextFields, btnAddLecturer, btnClearAddLecturerTextFields;

	@FXML private TextField tfShowLecturerName, tfEnterLecturerPhoneNumber, tfEnterLecturerSearchKeywords,
			tfEnterLecturerName, tfEnterLecturerEmail, tfShowLecturerEmail, tfShowLecturerPhoneNumber;

	@FXML private TableView<Lecturer> lecturerTable;

	@FXML private TableColumn<Lecturer, String> tcLecturerEmail, tcLecturerCategory, tcLecturerPhoneNumber, tcLecturerName;

	@FXML private ListView<Lecturer> lvLecturerSearchResults;

	@FXML private ComboBox<String> cbLecturerSearchCriteria, cbSelectLecturerCategory, cbShowLecturerCategory;

	@FXML private Label lblLecturerCount;

	@FXML private HBox hboxLecturerEditOptions;
	
	@FXML private ScrollPane spLecturersTableScrollPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		lecturerPage.setMaxHeight(Double.MAX_VALUE);
		lecturerPage.setMaxWidth(Double.MAX_VALUE);
		
		hboxLecturerEditOptions.setVisible(false);

		cbLecturerSearchCriteria.setItems(searchCriteria);
		cbLecturerSearchCriteria.getSelectionModel().select(0);

		cbSelectLecturerCategory.setItems(lecturerCategory);
		cbShowLecturerCategory.setItems(lecturerCategory);
		cbSelectLecturerCategory.getSelectionModel().select(0);
		
		cbShowLecturerCategory.setDisable(true);
		
		tfShowLecturerName.setEditable(false);
		tfShowLecturerEmail.setEditable(false);
		tfShowLecturerPhoneNumber.setEditable(false);

		tcLecturerName.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("lecturerName"));
		tcLecturerCategory.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("lecturerCategory"));
		tcLecturerEmail.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("lecturerEmail"));
		tcLecturerPhoneNumber.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("lecturerPhoneNumber"));

		tcLecturerName.setStyle("-fx-alignment: CENTER;");
		tcLecturerCategory.setStyle("-fx-alignment: CENTER;");
		tcLecturerEmail.setStyle("-fx-alignment: CENTER;");
		tcLecturerPhoneNumber.setStyle("-fx-alignment: CENTER;");

		btnEditLecturer.setDisable(true);
		btnDeleteLecturer.setDisable(true);

		try 
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
		}

		lecturerTable.setOnMouseClicked((MouseEvent event) -> 
		{
			if (event.getClickCount() == 1) 
			{
				if(lecturerTable.getSelectionModel().getSelectedItem() != null)
				{
					showLecturerDetailsFromTable();
					btnEditLecturer.setDisable(false);
					btnDeleteLecturer.setDisable(false);
				}
				else if(lecturerTable.getSelectionModel().getSelectedItem() == null)
				{
					btnEditLecturer.setDisable(true);
					btnDeleteLecturer.setDisable(true);
				}
			}
		});

		lvLecturerSearchResults.setOnMouseClicked((MouseEvent event) -> 
		{
			if (event.getClickCount() == 1) 
			{
				if(lvLecturerSearchResults.getSelectionModel().getSelectedItem() != null)
				{					
					try 
					{
						lecturerTable.requestFocus();
						lecturerTable.getSelectionModel().select(viaOms.getLecturerList().getLecturerIndex(lvLecturerSearchResults.getSelectionModel().getSelectedItem()));
						MouseEvent.fireEvent(lecturerTable, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
				                0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
				                true, true, true, true, true, true, null));
					} 
					catch (FileNotFoundException | ParseException e) 
					{
						e.printStackTrace();
					}
					btnEditLecturer.setDisable(false);
					btnDeleteLecturer.setDisable(false);				
				}		
				
				else if(lvLecturerSearchResults.getSelectionModel().getSelectedItem() == null)
				{
					btnEditLecturer.setDisable(true);
					btnDeleteLecturer.setDisable(true);
				}
			}
		});

		try 
		{
			lblLecturerCount.setText(String.format("Lecturer count: %d", viaOms.getLecturerList().size()));
		} 
		catch (FileNotFoundException | ParseException e) 
		{
			e.printStackTrace();
		}
	}

	public void showLecturerDetailsFromTable() 
	{
		if (lecturerTable.getSelectionModel().getSelectedItem() != null) 
		{
			tfShowLecturerName.setText(viaOms.getLecturer(lecturerTable.getSelectionModel().getSelectedItem()).getLecturerName());
			tfShowLecturerEmail.setText(viaOms.getLecturer(lecturerTable.getSelectionModel().getSelectedItem()).getLecturerEmail());
			tfShowLecturerPhoneNumber.setText(viaOms.getLecturer(lecturerTable.getSelectionModel().getSelectedItem()).getLecturerPhoneNumber());
			cbShowLecturerCategory.setValue(viaOms.getLecturer(lecturerTable.getSelectionModel().getSelectedItem()).getLecturerCategory());
		}
	}

	public ObservableList<Lecturer> getList() throws FileNotFoundException, ParseException 
	{
		for (int i = 0; i < viaOms.getLecturerList().size(); i++) {

			lecturers.add(new Lecturer(viaOms.getLecturerList().getLecturer(i).getLecturerName(), viaOms.getLecturerList().getLecturer(i).getLecturerCategory(),
					viaOms.getLecturerList().getLecturer(i).getLecturerEmail(),viaOms.getLecturerList().getLecturer(i).getLecturerPhoneNumber()));
		}
		return lecturers;
	}

	@FXML
	void addLecturer(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException 
	{
		String lecturerName = tfEnterLecturerName.getText();
		String lecturerCategory = cbSelectLecturerCategory.getSelectionModel().getSelectedItem();
		String lecturerEmail = tfEnterLecturerEmail.getText();
		String lecturerPhoneNumber = tfEnterLecturerPhoneNumber.getText();

		if (tfEnterLecturerName.getText().isEmpty() && tfEnterLecturerPhoneNumber.getText().isEmpty()
				&& tfEnterLecturerEmail.getText().isEmpty()) {
			lecturerName = String.format("Not specified%d", viaOms.getLecturerList().size() + 1);
			
			lecturerEmail = "empty@empty";
			lecturerPhoneNumber = "empty";
		}

		else if (tfEnterLecturerName.getText().isEmpty()) 
		{
			lecturerName = "empty";
		}

		else if (tfEnterLecturerPhoneNumber.getText().isEmpty()) 
		{
			lecturerPhoneNumber = "empty";
		}

		else if (tfEnterLecturerEmail.getText().isEmpty()) 
		{
			lecturerEmail = "empty@empty";
		}

		if (lecturerEmail.contains("@")) 
		{
			Lecturer lecturer = new Lecturer(lecturerName, lecturerCategory, lecturerEmail, lecturerPhoneNumber);

			if (viaOms.checkForLecturerDuplicates(lecturer)) 
			{
				JOptionPane.showMessageDialog(null, "Lecturer already exists in the system!");
				clearAddLecturerTextFields(event);
			}

			else 
			{
				viaOms.addLecturer(lecturerName, lecturerCategory, lecturerEmail, lecturerPhoneNumber);
				lecturerTable.getItems().add(lecturer);
				clearAddLecturerTextFields(event);
				
				lblLecturerCount.setText(String.format("Lecturer count: %d", viaOms.getLecturerList().size()));
			}
		}

		else 
		{
			JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
		}
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
		ObservableList<Lecturer> searchResults = FXCollections.observableArrayList();
		int searchCriteriaComboBoxSelection = cbLecturerSearchCriteria.getSelectionModel().getSelectedIndex();
		String searchKeyword = tfEnterLecturerSearchKeywords.getText();
		
		if(tfEnterLecturerSearchKeywords.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please enter a search key-word!");
			tfEnterLecturerSearchKeywords.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");			
		}
		
		else
		{
			tfEnterLecturerSearchKeywords.setText("");
			tfEnterLecturerSearchKeywords.setStyle("-fx-border-width: 0px ;");

			searchResults.clear();

			switch (searchCriteriaComboBoxSelection) 
			{
			case 0:

				for (int i = 0; i < lecturers.size(); i++) 
				{
					if (lecturers.get(i).getLecturerName().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(lecturers.get(i));
					}
				}

				if (searchResults.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,
							"No lecturers found with the given search keyword: \n" + searchKeyword);
				}
				
				break;

			case 1:

				for (int i = 0; i < lecturers.size(); i++) 
				{
					if (lecturers.get(i).getLecturerCategory().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(lecturers.get(i));
					}
				}

				if (searchResults.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,
							"No lecturers found with the given search keyword: " + searchKeyword);
				}

				break;
				
			case 2:
				
				for (int i = 0; i < lecturers.size(); i++) 
				{
					if (lecturers.get(i).getLecturerPhoneNumber().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(lecturers.get(i));
					}
				}

				if (searchResults.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,
							"No lecturers found with the given search keyword: \n" + searchKeyword);
				}
				
				break;
				
			case 3:
				
				for (int i = 0; i < lecturers.size(); i++) 
				{
					if (lecturers.get(i).getLecturerEmail().toLowerCase().contains(searchKeyword.toLowerCase())) 
					{
						searchResults.add(lecturers.get(i));
					}
				}

				if (searchResults.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,
							"No lecturers found with the given search keyword: \n" + searchKeyword);
				}

			default:
				break;
			}

			lvLecturerSearchResults.setItems(searchResults);
		}		
	}
	
	@FXML
	void editLecturer(ActionEvent event) 
	{
		if (lecturerTable.getSelectionModel() != null) 
		{
			hboxLecturerEditOptions.setVisible(true);
			
			btnDeleteLecturer.setDisable(true);
			cbShowLecturerCategory.setDisable(false);
			
			tfShowLecturerName.setEditable(true);
			tfShowLecturerEmail.setEditable(true);
			tfShowLecturerPhoneNumber.setEditable(true);

			tfShowLecturerName.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowLecturerEmail.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowLecturerPhoneNumber.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			cbShowLecturerCategory.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
		}
	}

	@FXML
	void saveEditLecturerChanges(ActionEvent event) throws FileNotFoundException, ParseException 
	{
		int index = viaOms.getLecturerList().getLecturerIndex(lecturerTable.getSelectionModel().getSelectedItem()); 

		String newLecturerName = tfShowLecturerName.getText();
		String newLecturerCategory = cbShowLecturerCategory.getSelectionModel().getSelectedItem();
		String newLecturerEmail = tfShowLecturerEmail.getText();
		String newLecturerPhone = tfShowLecturerPhoneNumber.getText();		

		if (tfShowLecturerName.getText().isEmpty()) 
		{
			newLecturerName = "empty";
		}

		if (tfShowLecturerEmail.getText().isEmpty()) 
		{
			newLecturerEmail = "empty";
		}

		if (tfShowLecturerPhoneNumber.getText().isEmpty()) 
		{
			newLecturerPhone = "empty";
		}		
		
		if(newLecturerEmail.contains("@"))
		{	
			Lecturer tempLecturer = new Lecturer(newLecturerName,newLecturerCategory,newLecturerEmail,newLecturerPhone);
			
			if (viaOms.checkForLecturerDuplicates(tempLecturer)) 
			{
				JOptionPane.showMessageDialog(null, "Lecturer already exists in the system!");
				
				hboxLecturerEditOptions.setVisible(false);
				
				tfShowLecturerName.setText("");
				tfShowLecturerEmail.setText("");
				tfShowLecturerPhoneNumber.setText("");
				
				cbShowLecturerCategory.setDisable(true);
				
				tfShowLecturerName.setEditable(false);
				tfShowLecturerEmail.setEditable(false);
				tfShowLecturerPhoneNumber.setEditable(false);
		
				tfShowLecturerName.setStyle("-fx-border-width: 0px ;");
				tfShowLecturerEmail.setStyle("-fx-border-width: 0px ;");
				tfShowLecturerPhoneNumber.setStyle("-fx-border-width: 0px ;");
				cbShowLecturerCategory.setStyle("-fx-border-width: 0px ;");
				
				btnEditLecturer.setDisable(true);
				btnDeleteLecturer.setDisable(true);
			}

			else 
			{
				lecturerTable.getSelectionModel().getSelectedItem().setLecturerName(newLecturerName);
				lecturerTable.getSelectionModel().getSelectedItem().setLecturerCategory(newLecturerCategory);
				lecturerTable.getSelectionModel().getSelectedItem().setLecturerEmail(newLecturerEmail);
				lecturerTable.getSelectionModel().getSelectedItem().setLecturerPhoneNumber(newLecturerPhone);
		
				viaOms.editLecturer(index, lecturerTable.getSelectionModel().getSelectedItem());						
				lecturerTable.getItems().set(index, lecturerTable.getSelectionModel().getSelectedItem());
				lvLecturerSearchResults.getItems().clear();
				
				hboxLecturerEditOptions.setVisible(false);
				
				cbShowLecturerCategory.setDisable(true);
				
				tfShowLecturerName.setEditable(false);
				tfShowLecturerEmail.setEditable(false);
				tfShowLecturerPhoneNumber.setEditable(false);
				
				btnEditLecturer.setDisable(true);
				btnDeleteLecturer.setDisable(true);
		
				tfShowLecturerName.setStyle("-fx-border-width: 0px ;");
				tfShowLecturerEmail.setStyle("-fx-border-width: 0px ;");
				tfShowLecturerPhoneNumber.setStyle("-fx-border-width: 0px ;");
				cbShowLecturerCategory.setStyle("-fx-border-width: 0px ;");
				
				lecturerTable.requestFocus();
				lecturerTable.getSelectionModel().select(index);
				lecturerTable.getFocusModel().focus(index);
				
				MouseEvent.fireEvent(lecturerTable, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
		                0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
		                true, true, true, true, true, true, null));
			}		
		}
		
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
		}
	}

	@FXML
	void clearEditLecturerTextFields(ActionEvent event) 
	{
		tfShowLecturerName.setText("");
		tfShowLecturerEmail.setText("");
		tfShowLecturerPhoneNumber.setText("");
	}

	@FXML
	void cancelEditLecturer(ActionEvent event) 
	{
		hboxLecturerEditOptions.setVisible(false);

		cbShowLecturerCategory.setDisable(true);
		
		tfShowLecturerName.setEditable(false);
		tfShowLecturerEmail.setEditable(false);
		tfShowLecturerPhoneNumber.setEditable(false);

		tfShowLecturerName.setStyle("-fx-border-width: 0px ;");
		tfShowLecturerEmail.setStyle("-fx-border-width: 0px ;");
		tfShowLecturerPhoneNumber.setStyle("-fx-border-width: 0px ;");
		cbShowLecturerCategory.setStyle("-fx-border-width: 0px ;");
		
		btnEditLecturer.setDisable(true);
		btnDeleteLecturer.setDisable(true);
	}	

	@FXML
	void deleteLecturer(ActionEvent event) throws FileNotFoundException, ParseException 
	{
		if (lecturerTable.getSelectionModel() != null) 
		{
			if (viaOms.getLecturerList().size() > 0) 
			{
				try 
				{
					if(lecturerTable.getSelectionModel().getSelectedItem() != null)
					{
						String[] options = { "Delete", "Cancel" };
						int n = JOptionPane.showOptionDialog(null,
								"Are you sure you want to delete lecturer:\n" + lecturerTable.getSelectionModel().getSelectedItem() + " ?",
								"Delete lecturer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
	
						if (n == JOptionPane.YES_OPTION) 
						{
							int index = viaOms.getLecturerList().getLecturerIndex(lecturerTable.getSelectionModel().getSelectedItem());
	
							viaOms.deleteLecturer(index);							
							lecturerTable.getItems().remove(index);
							lvLecturerSearchResults.getItems().clear();
							tfShowLecturerName.setText("");
							tfShowLecturerEmail.setText("");
							tfShowLecturerPhoneNumber.setText(""); 
							
							btnEditLecturer.setDisable(true);
							btnDeleteLecturer.setDisable(true);
	
							lblLecturerCount.setText(String.format("lecturer count: %d", viaOms.getLecturerList().size()));
						}
					}
				} 
				
				catch (ArrayIndexOutOfBoundsException e) 
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
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

public class GUIlecturersController implements Initializable 
{
	private VIAoms viaOms = new VIAoms();
	
	private Lecturer selectedLecturer;
	private LecturerList lecturerList = new LecturerList();
	private static final String FILENAME = "LecturerList.txt";
	private FileReaderWriter lecturerFile = new FileReaderWriter(FILENAME);

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
		
		//spLecturersTableScrollPane.setStyle("-fx-font-size: 13px;");
		
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

		lecturerTable.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
				showLecturerDetailsFromTable();
				btnEditLecturer.setDisable(false);
				btnDeleteLecturer.setDisable(false);
			}
		});

		lvLecturerSearchResults.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
				showLecturerDetailsFromListView();
				btnEditLecturer.setDisable(false);
				btnDeleteLecturer.setDisable(false);
			}
		});

		lblLecturerCount.setText(String.format("Lecturer count: %d", lecturerList.size()));
	}

	public void showLecturerDetailsFromTable() 
	{
		if (lecturerTable.getSelectionModel().getSelectedItem() != null) 
		{
			selectedLecturer = lecturerTable.getSelectionModel().getSelectedItem();
			tfShowLecturerName.setText(selectedLecturer.getLecturerName());
			tfShowLecturerEmail.setText(selectedLecturer.getLecturerEmail());
			tfShowLecturerPhoneNumber.setText(selectedLecturer.getLecturerPhoneNumber());

			int tempIndex = 0;

			if (selectedLecturer.getLecturerCategory().equalsIgnoreCase("Dream Interpretation")) {
				tempIndex = 0;
			}
			if (selectedLecturer.getLecturerCategory().equalsIgnoreCase("Healing")) {
				tempIndex = 1;
			}
			if (selectedLecturer.getLecturerCategory().equalsIgnoreCase("Astrology")) {
				tempIndex = 2;
			}
			if (selectedLecturer.getLecturerCategory().equalsIgnoreCase("Reincarnation")) {
				tempIndex = 3;
			}
			if (selectedLecturer.getLecturerCategory().equalsIgnoreCase("Karma")) {
				tempIndex = 4;
			}
			if (selectedLecturer.getLecturerCategory().equalsIgnoreCase("Alternative Health-Care")) {
				tempIndex = 5;
			}

			cbShowLecturerCategory.getSelectionModel().clearAndSelect(tempIndex);
		}
	}

	public void showLecturerDetailsFromListView() 
	{
		if (lvLecturerSearchResults.getSelectionModel().getSelectedItem() != null) 
		{
			selectedLecturer = lvLecturerSearchResults.getSelectionModel().getSelectedItem();
			System.out.println(selectedLecturer);
			tfShowLecturerName.setText(selectedLecturer.getLecturerName());
			tfShowLecturerEmail.setText(selectedLecturer.getLecturerEmail());
			tfShowLecturerPhoneNumber.setText(selectedLecturer.getLecturerPhoneNumber());
		}
	}
	
	public ObservableList<Lecturer> getList() throws FileNotFoundException, ParseException 
	{
		lecturerList = lecturerFile.readLecturerTextFile();

		for (int i = 0; i < lecturerList.size(); i++) {

			lecturers.add(new Lecturer(lecturerList.getLecturer(i).getLecturerName(), lecturerList.getLecturer(i).getLecturerCategory(),
					lecturerList.getLecturer(i).getLecturerEmail(),lecturerList.getLecturer(i).getLecturerPhoneNumber()));
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
			lecturerName = String.format("empty%d", lecturerList.size() + 1);
			
			lecturerEmail = String.format("empty@empty%d", lecturerList.size() + 1);
			lecturerPhoneNumber = String.format("empty%d", lecturerList.size() + 1);
		}

		else if (tfEnterLecturerName.getText().isEmpty()) 
		{
			lecturerName = String.format("empty%d", lecturerList.size() + 1);
		}

		else if (tfEnterLecturerPhoneNumber.getText().isEmpty()) 
		{
			lecturerPhoneNumber = String.format("empty@%d", lecturerList.size() + 1);
		}

		else if (tfEnterLecturerEmail.getText().isEmpty()) 
		{
			lecturerEmail = String.format("empty@empty%d", lecturerList.size() + 1);
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
		int index = viaOms.getLecturerList().getLecturerIndex(selectedLecturer); 

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
				selectedLecturer.setLecturerName(newLecturerName);
				selectedLecturer.setLecturerCategory(newLecturerCategory);
				selectedLecturer.setLecturerEmail(newLecturerEmail);
				selectedLecturer.setLecturerPhoneNumber(newLecturerPhone);
		
				viaOms.editLecturer(index, selectedLecturer);
						
				lecturerTable.getItems().set(index, selectedLecturer);
		
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
			if (lecturerList.size() > 0) 
			{
				try 
				{
					String[] options = { "Delete", "Cancel" };
					int n = JOptionPane.showOptionDialog(null,
							"Are you sure you want to delete lecturer:\n" + selectedLecturer + " ?",
							"Delete lecturer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);

					if (n == JOptionPane.YES_OPTION) 
					{
						int index = viaOms.getLecturerList().getLecturerIndex(selectedLecturer);

						viaOms.deleteLecturer(index);
						
						lecturerTable.getItems().remove(index);
	
						tfShowLecturerName.setText("");
						tfShowLecturerEmail.setText("");
						tfShowLecturerPhoneNumber.setText(""); 
						
						btnEditLecturer.setDisable(true);
						btnDeleteLecturer.setDisable(true);

						lblLecturerCount.setText(String.format("lecturer count: %d", viaOms.getLecturerList().size()));
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
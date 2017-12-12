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
	private Lecturer lecturer;
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

		tcLecturerName.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("name"));
		tcLecturerCategory.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("category"));
		tcLecturerPhoneNumber.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("phoneNumber"));
		tcLecturerEmail.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("email"));

		tcLecturerName.setStyle("-fx-alignment: CENTER;");
		tcLecturerCategory.setStyle("-fx-alignment: CENTER;");
		tcLecturerPhoneNumber.setStyle("-fx-alignment: CENTER;");
		tcLecturerEmail.setStyle("-fx-alignment: CENTER;");

		btnEditLecturer.setDisable(true);
		btnDeleteLecturer.setDisable(true);
		
		spLecturersTableScrollPane.setStyle("-fx-font-size: 13px;");
		
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
			tfShowLecturerName.setText(selectedLecturer.getName());
			tfShowLecturerEmail.setText(selectedLecturer.getEmail());
			tfShowLecturerPhoneNumber.setText(selectedLecturer.getPhoneNumber());

			int tempIndex = 0;

			if (selectedLecturer.getCategory().equalsIgnoreCase("Dream Interpretation")) {
				tempIndex = 0;
			}
			if (selectedLecturer.getCategory().equalsIgnoreCase("Healing")) {
				tempIndex = 1;
			}
			if (selectedLecturer.getCategory().equalsIgnoreCase("Astrology")) {
				tempIndex = 2;
			}
			if (selectedLecturer.getCategory().equalsIgnoreCase("Reincarnation")) {
				tempIndex = 3;
			}
			if (selectedLecturer.getCategory().equalsIgnoreCase("Karma")) {
				tempIndex = 4;
			}
			if (selectedLecturer.getCategory().equalsIgnoreCase("Alternative Health-Care")) {
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
			tfShowLecturerName.setText(selectedLecturer.getName());
			tfShowLecturerEmail.setText(selectedLecturer.getEmail());
		}
	}
	
	public ObservableList<Lecturer> getList() throws FileNotFoundException, ParseException 
	{
		lecturerList = lecturerFile.readLecturerTextFile();

		for (int i = 0; i < lecturerList.size(); i++) {

			lecturers.add(new Lecturer(lecturerList.getLecturer(i).getName(), lecturerList.getLecturer(i).getCategory(),
					lecturerList.getLecturer(i).getPhoneNumber(), lecturerList.getLecturer(i).getEmail()));
		}
		return lecturers;
	}

	@FXML
	void addLecturer(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException 
	{
		String lecturerName = tfEnterLecturerName.getText();
		String lecturerCategory = cbSelectLecturerCategory.getSelectionModel().getSelectedItem();
		String lecturerPhoneNumber = tfEnterLecturerPhoneNumber.getText();
		String lecturerEmail = tfEnterLecturerEmail.getText();

		if (tfEnterLecturerName.getText().isEmpty() && tfEnterLecturerPhoneNumber.getText().isEmpty()
				&& tfEnterLecturerEmail.getText().isEmpty()) {
			lecturerName = String.format("empty%d", lecturerList.size() + 1);
			lecturerPhoneNumber = String.format("empty%d", lecturerList.size() + 1);
			lecturerEmail = String.format("empty@empty%d", lecturerList.size() + 1);
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
			lecturer = new Lecturer(lecturerName, lecturerCategory, lecturerPhoneNumber, lecturerEmail);

			System.out.println(lecturer);

			if (lecturerList.checkForDuplicates(lecturerList, lecturer)) 
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
		tfEnterLecturerSearchKeywords.setText("");

		searchResults.clear();

		switch (searchCriteriaComboBoxSelection) 
		{
		case 0:

			for (int i = 0; i < lecturers.size(); i++) 
			{
				if (lecturers.get(i).getName().toLowerCase().contains(searchKeyword)) 
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
				if (lecturers.get(i).getCategory().toLowerCase().contains(searchKeyword)) 
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
				if (lecturers.get(i).getPhoneNumber().toLowerCase().contains(searchKeyword)) 
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
				if (lecturers.get(i).getEmail().toLowerCase().contains(searchKeyword)) 
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
	
	@FXML
	void editLecturer(ActionEvent event) 
	{
		if (lecturerTable.getSelectionModel() != null) 
		{
			System.out.println(lecturerTable.getSelectionModel());
			hboxLecturerEditOptions.setVisible(true);

			cbShowLecturerCategory.setDisable(false);
			
			tfShowLecturerName.setEditable(true);
			tfShowLecturerEmail.setEditable(true);
			tfShowLecturerPhoneNumber.setEditable(true);

			tfShowLecturerName.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
			tfShowLecturerEmail.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
			tfShowLecturerPhoneNumber.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
		}
	}

	@FXML
	void saveEditLecturerChanges(ActionEvent event) throws FileNotFoundException, ParseException 
	{
		int index = lecturerList.getLecturerIndex(selectedLecturer);

		String newLecturerName = tfShowLecturerName.getText();
		String newLecturerEmail = tfShowLecturerEmail.getText();
		String newLecturerPhone = tfShowLecturerPhoneNumber.getText();
		String newLecturerCategory = cbShowLecturerCategory.getSelectionModel().getSelectedItem();

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
			selectedLecturer.setName(newLecturerName);
			selectedLecturer.setEmail(newLecturerEmail);
			selectedLecturer.setPhoneNumber(newLecturerPhone);
			selectedLecturer.setCategory(newLecturerCategory);
	
			lecturerList.replaceLecturer(index, selectedLecturer);
			lecturerFile.writeLecturerTextFile(lecturerList);
	
			lecturerTable.getItems().set(index, selectedLecturer);
	
			hboxLecturerEditOptions.setVisible(false);
			
			cbShowLecturerCategory.setDisable(true);
			
			tfShowLecturerName.setEditable(false);
			tfShowLecturerEmail.setEditable(false);
			tfShowLecturerPhoneNumber.setEditable(false);
	
			tfShowLecturerName.setStyle("-fx-border-width: 0px ;");
			tfShowLecturerEmail.setStyle("-fx-border-width: 0px ;");
			tfShowLecturerPhoneNumber.setStyle("-fx-border-width: 0px ;");
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
	}	

	@FXML
	void deleteLecturer(ActionEvent event) throws FileNotFoundException 
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
							"Delete a lecturer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
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
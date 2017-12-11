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
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name", "E-mail");
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

	}

	@FXML
	void searchLecturers(ActionEvent event) {

	}

	@FXML
	void saveEditLecturerChanges(ActionEvent event) {

	}

	@FXML
	void clearEditLecturerTextFields(ActionEvent event) {

	}

	@FXML
	void cancelEditLecturer(ActionEvent event) {

	}

	@FXML
	void editLecturer(ActionEvent event) {

	}

	@FXML
	void deleteLecturer(ActionEvent event) {

	}
}

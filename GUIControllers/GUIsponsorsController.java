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
 * Controller class for GUIsponsors.fxml. Handles all ActionEvent and
 * MouseEvents that occur on the sponsors page in the application. *
 * 
 * @author Group#2 *
 */

public class GUIsponsorsController implements Initializable 
{
	private VIAoms viaOms = new VIAoms();

	private Sponsor selectedSponsor;

	private ObservableList<Sponsor> sponsors = FXCollections.observableArrayList();
	private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name", "E-mail", "Phone");

	@FXML
	private BorderPane sponsorPage = new BorderPane();

	@FXML
	private Button btnAddSponsor, btnDeleteSponsor, btnEditSponsor, btnClearAddSponsorTextFields, btnSearchSponsors,
			btnCancelEditSponsor, btnClearEditSponsorTextFields, btnSaveSponsorEditChanges;

	@FXML
	private TextField tfSponsorID, tfShowSponsorEmail, tfEnterSponsorSearchKeywords, tfEnterSponsorEmail,
			tfShowSponsorName, tfShowSponsorPhoneNumber, tfEnterSponsorName, tfEnterSponsorPhoneNumber;

	@FXML
	private TableView<Sponsor> sponsorTable;

	@FXML
	private TableColumn<Sponsor, String> tcSponsorID, tcSponsorName, tcSponsorEmail, tcSponsorPhoneNumber;

	@FXML
	private ListView<Sponsor> lvSponsorSearchResults;

	@FXML
	private ComboBox<String> cbSponsorSearchCriteria;

	@FXML
	private Label lblSponsorCount;

	@FXML
	private HBox hboxSponsorEditOptions;

	@FXML
	private ScrollPane spSponsorsTableScrollPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sponsorPage.setMaxHeight(Double.MAX_VALUE);
		sponsorPage.setMaxWidth(Double.MAX_VALUE);

		hboxSponsorEditOptions.setVisible(false);

		cbSponsorSearchCriteria.setItems(searchCriteria);
		cbSponsorSearchCriteria.getSelectionModel().select(0);

		tcSponsorName.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("name"));
		tcSponsorEmail.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("email"));
		tcSponsorPhoneNumber.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("phoneNumber"));

		tcSponsorName.setStyle("-fx-alignment: CENTER;");
		tcSponsorEmail.setStyle("-fx-alignment: CENTER;");
		tcSponsorPhoneNumber.setStyle("-fx-alignment: CENTER;");

		btnEditSponsor.setDisable(true);
		btnDeleteSponsor.setDisable(true);

		tfShowSponsorName.setEditable(false);
		tfShowSponsorEmail.setEditable(false);
		tfShowSponsorPhoneNumber.setEditable(false);

		try {
			sponsorTable.setItems(getList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		sponsorTable.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
				showSponsorDetailsFromTable();
				btnEditSponsor.setDisable(false);
				btnDeleteSponsor.setDisable(false);
			}
		});

		lvSponsorSearchResults.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() == 1) {
				showSponsorDetailsFromListView();
				btnEditSponsor.setDisable(false);
				btnDeleteSponsor.setDisable(false);
			}
		});

		try {
			lblSponsorCount.setText(String.format("Sponsor count: %d", viaOms.getSponsorList().size()));
		} catch (FileNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showSponsorDetailsFromTable() {
		if (sponsorTable.getSelectionModel().getSelectedItem() != null) {
			selectedSponsor = sponsorTable.getSelectionModel().getSelectedItem();
			tfShowSponsorName.setText(selectedSponsor.getName());
			tfShowSponsorEmail.setText(selectedSponsor.getEmail());
			tfShowSponsorPhoneNumber.setText(selectedSponsor.getPhoneNumber());
		}
	}

	public void showSponsorDetailsFromListView() {
		if (lvSponsorSearchResults.getSelectionModel().getSelectedItem() != null) {
			selectedSponsor = lvSponsorSearchResults.getSelectionModel().getSelectedItem();
			tfShowSponsorName.setText(selectedSponsor.getName());
			tfShowSponsorEmail.setText(selectedSponsor.getEmail());
			tfShowSponsorPhoneNumber.setText(selectedSponsor.getPhoneNumber());
		}
	}

	public ObservableList<Sponsor> getList() throws FileNotFoundException, ParseException {
		
		for (int i = 0; i < viaOms.getSponsorList().size(); i++) {
			sponsors.add(new Sponsor(viaOms.getSponsorList().getSponsor(i).getName(), viaOms.getSponsorList().getSponsor(i).getEmail(),
					viaOms.getSponsorList().getSponsor(i).getPhoneNumber()));
		}
		return sponsors;
	}

	@FXML
	void addSponsor(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException {
		String sponsorName = tfEnterSponsorName.getText();
		String sponsorEmail = tfEnterSponsorEmail.getText();
		String sponsorPhone = tfEnterSponsorPhoneNumber.getText();

		if (tfEnterSponsorName.getText().isEmpty() && tfEnterSponsorEmail.getText().isEmpty()
				&& tfEnterSponsorPhoneNumber.getText().isEmpty()) {
			sponsorName = String.format("empty%d", viaOms.getSponsorList().size() + 1);
			sponsorEmail = "empty@";
			sponsorPhone = "empty";
		}

		else if (tfEnterSponsorName.getText().isEmpty()) {
			sponsorName = String.format("empty%d", viaOms.getSponsorList().size() + 1);
		}

		else if (tfEnterSponsorEmail.getText().isEmpty()) {
			sponsorEmail = "empty@";
		}

		else if (tfEnterSponsorPhoneNumber.getText().isEmpty()) {
			sponsorPhone = "empty";
		}

		if (sponsorEmail.contains("@")) {
			Sponsor sponsor = new Sponsor(sponsorName, sponsorEmail, sponsorPhone);

			if (viaOms.checkForSponsorDuplicates(sponsor)) {
				JOptionPane.showMessageDialog(null, "Sponsor already exists in the system!");
				clearAddSponsorTextFields(event);
			}

			else {
				viaOms.addSponsor(sponsorName, sponsorEmail, sponsorPhone);

				sponsorTable.getItems().add(sponsor);
				clearAddSponsorTextFields(event);

				lblSponsorCount.setText(String.format("Sponsor count: %d", viaOms.getSponsorList().size()));
			}
		}

		else {
			JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
		}
	}

	@FXML
	void clearAddSponsorTextFields(ActionEvent event) {
		tfEnterSponsorName.setText("");
		tfEnterSponsorEmail.setText("");
		tfEnterSponsorPhoneNumber.setText("");
	}

	@FXML
	void searchSponsors(ActionEvent event) {
		ObservableList<Sponsor> searchResults = FXCollections.observableArrayList();
		int searchCriteriaComboBoxSelection = cbSponsorSearchCriteria.getSelectionModel().getSelectedIndex();
		String searchKeyword = tfEnterSponsorSearchKeywords.getText();
		
		if(tfEnterSponsorSearchKeywords.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Please enter a search key-word!");
			tfEnterSponsorSearchKeywords.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");	
		}
		
		else
		{
			tfEnterSponsorSearchKeywords.setText("");
			tfEnterSponsorSearchKeywords.setStyle("-fx-border-width: 0px ;");	

			searchResults.clear();

			switch (searchCriteriaComboBoxSelection) {
			case 0:

				for (int i = 0; i < sponsors.size(); i++) {
					if (sponsors.get(i).getName().toLowerCase().contains(searchKeyword.toLowerCase())) {
						searchResults.add(sponsors.get(i));
					}
				}

				if (searchResults.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"No sponsors found with the given search keyword: \n" + searchKeyword);
				}

				break;

			case 1:

				for (int i = 0; i < sponsors.size(); i++) {
					if (sponsors.get(i).getEmail().toLowerCase().contains(searchKeyword.toLowerCase())) {
						searchResults.add(sponsors.get(i));
					}
				}

				if (searchResults.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"No sponsors found with the given search keyword: " + searchKeyword);
				}

				break;

			case 2:

				for (int i = 0; i < sponsors.size(); i++) {
					if (sponsors.get(i).getPhoneNumber().toLowerCase().contains(searchKeyword.toLowerCase())) {
						searchResults.add(sponsors.get(i));
					}
				}

				if (searchResults.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"No sponsors found with the given search keyword: \n" + searchKeyword);
				}

				break;

			default:
				break;
			}

			lvSponsorSearchResults.setItems(searchResults);
		}		
	}

	@FXML
	void editSponsor(ActionEvent event) {
		if (sponsorTable.getSelectionModel() != null) 
		{
			hboxSponsorEditOptions.setVisible(true);

			tfShowSponsorName.setEditable(true);
			tfShowSponsorEmail.setEditable(true);
			tfShowSponsorPhoneNumber.setEditable(true);
			
			btnDeleteSponsor.setDisable(true);

			tfShowSponsorName.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowSponsorEmail.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
			tfShowSponsorPhoneNumber.setStyle("-fx-border-color: red ; -fx-border-width: 0.3px ;");
		}
	}

	@FXML
	void saveEditSponsorChanges(ActionEvent event) throws FileNotFoundException, ParseException {
		int index = viaOms.getSponsorList().getSponsorIndex(selectedSponsor);

		String newSponsorName = tfShowSponsorName.getText();
		String newSponsorEmail = tfShowSponsorEmail.getText();
		String newSponsorPhone = tfShowSponsorPhoneNumber.getText();

		if (tfShowSponsorName.getText().isEmpty()) {
			newSponsorName = String.format("empty%d", viaOms.getSponsorList().size());
		}

		if (tfShowSponsorEmail.getText().isEmpty()) {
			newSponsorEmail = "empty";
		}

		if (tfShowSponsorPhoneNumber.getText().isEmpty()) {
			newSponsorPhone = "empty";
		}

		if (newSponsorEmail.contains("@")) {
			Sponsor tempSponsor = new Sponsor(newSponsorName, newSponsorEmail, newSponsorPhone);

			if (viaOms.checkForSponsorDuplicates(tempSponsor)) {
				JOptionPane.showMessageDialog(null, "Sponsor already exists in the system!");

				hboxSponsorEditOptions.setVisible(false);

				tfShowSponsorName.setText("");
				tfShowSponsorEmail.setText("");
				tfShowSponsorPhoneNumber.setText("");

				tfShowSponsorName.setEditable(false);
				tfShowSponsorEmail.setEditable(false);
				tfShowSponsorPhoneNumber.setEditable(false);

				tfShowSponsorName.setStyle("-fx-border-width: 0px ;");
				tfShowSponsorEmail.setStyle("-fx-border-width: 0px ;");
				tfShowSponsorPhoneNumber.setStyle("-fx-border-width: 0px ;");

				btnEditSponsor.setDisable(true);
				btnDeleteSponsor.setDisable(true);
			}

			else {
				selectedSponsor.setName(newSponsorName);
				selectedSponsor.setEmail(newSponsorEmail);
				selectedSponsor.setPhoneNumber(newSponsorPhone);

				viaOms.editSponsor(index, selectedSponsor);
				sponsorTable.getItems().set(index, selectedSponsor);

				hboxSponsorEditOptions.setVisible(false);
				tfShowSponsorName.setEditable(false);
				tfShowSponsorEmail.setEditable(false);
				tfShowSponsorPhoneNumber.setEditable(false);

				btnEditSponsor.setDisable(true);
				btnDeleteSponsor.setDisable(true);

				tfShowSponsorName.setStyle("-fx-border-width: 0px ;");
				tfShowSponsorEmail.setStyle("-fx-border-width: 0px ;");
				tfShowSponsorPhoneNumber.setStyle("-fx-border-width: 0px ;");
				
				sponsorTable.requestFocus();
				sponsorTable.getSelectionModel().select(index);
				sponsorTable.getFocusModel().focus(index);
				
				MouseEvent.fireEvent(sponsorTable, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
		                0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
		                true, true, true, true, true, true, null));
			}
		}

		else {
			JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
		}
	}

	@FXML
	void clearEditSponsorTextFields(ActionEvent event) {
		tfShowSponsorName.setText("");
		tfShowSponsorEmail.setText("");
		tfShowSponsorPhoneNumber.setText("");
	}

	@FXML
	void cancelEditSponsor(ActionEvent event) {
		hboxSponsorEditOptions.setVisible(false);

		tfShowSponsorName.setEditable(false);
		tfShowSponsorEmail.setEditable(false);
		tfShowSponsorPhoneNumber.setEditable(false);

		tfShowSponsorName.setStyle("-fx-border-width: 0px ;");
		tfShowSponsorEmail.setStyle("-fx-border-width: 0px ;");
		tfShowSponsorPhoneNumber.setStyle("-fx-border-width: 0px ;");

		btnEditSponsor.setDisable(true);
		btnDeleteSponsor.setDisable(true);
	}

	@FXML
	void deleteSponsor(ActionEvent event) throws FileNotFoundException, ParseException, ArrayIndexOutOfBoundsException {
		if (sponsorTable.getSelectionModel() != null) {
			if (viaOms.getSponsorList().size() > 0) {
				try {
					String[] options = { "Delete", "Cancel" };
					int n = JOptionPane.showOptionDialog(null,
							"Are you sure you want to delete sponsor:\n" + selectedSponsor + " ?", "Delete sponsor",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					if (n == JOptionPane.YES_OPTION) {
						
						int index = viaOms.getSponsorList().getSponsorIndex(selectedSponsor);

						viaOms.deleteSponsor(index);
						// sponsorFile.writeSponsorTextFile(sponsorList);
						sponsorTable.getItems().remove(index);

						tfShowSponsorName.setText("");
						tfShowSponsorEmail.setText("");
						tfShowSponsorPhoneNumber.setText("");

						btnEditSponsor.setDisable(true);
						btnDeleteSponsor.setDisable(true);

						lblSponsorCount.setText(String.format("Sponsor count: %d", viaOms.getSponsorList().size()));
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Please select a sponsor from the table to delete");
				}
			} else {
				JOptionPane.showMessageDialog(null, "No more sponsors left to delete");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please select a sponsor from the table to delete");
		}
	}
}
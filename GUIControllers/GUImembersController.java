import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUImembersController implements Initializable {
	private Member member;
	private MemberList memberList = new MemberList();
	private String filename = "MemberList.txt";
	private MemberFile memberFile = new MemberFile(filename);

	ObservableList<Member> members = FXCollections.observableArrayList();

	@FXML
	private BorderPane memberPage = new BorderPane();

	@FXML
	private Button btnAddMember, btnClearTextFields;

	@FXML
	private TextField tfEnterMemberName, tfEnterMemberEmail;

	@FXML
	private TableView<Member> memberTable;

	@FXML
	private TableColumn<Member, String> tcMemberName, tcMemberEmail;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberPage.setMaxHeight(Double.MAX_VALUE);
		memberPage.setMaxWidth(Double.MAX_VALUE);

		tcMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
		tcMemberEmail.setCellValueFactory(new PropertyValueFactory<Member, String>("email"));

		try {
			memberTable.setItems(getList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public ObservableList<Member> getList() throws FileNotFoundException, ParseException {
		MemberList memberLi = memberFile.readMemberTextFile();

		for (int i = 0; i < memberLi.size(); i++) {
			members.add(new Member(memberLi.getMember(i).getName(), memberLi.getMember(i).getEmail()));
		}

		return members;
	}

	@FXML
	void addMember(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException {
		String memberName = tfEnterMemberName.getText();
		String memberEmail = tfEnterMemberEmail.getText();
		member = new Member(memberName, memberEmail);
		memberList.addMemberToList(member);
		memberFile.writeMemberTextFile(memberList);
		
		memberTable.getItems().add(member);

		clearTextFields(event);
	}

	@FXML
	void clearTextFields(ActionEvent event) {
		tfEnterMemberName.setText("");
		tfEnterMemberEmail.setText("");
	}
}
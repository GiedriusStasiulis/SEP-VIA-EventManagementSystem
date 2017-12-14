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
 * Controller class for GUIsponsors.fxml. Handles all ActionEvent and
 * MouseEvents that occur on the sponsors page in the application. *
 * 
 * @author Group#2 *
 */

public class GUIsponsorsController implements Initializable
{
   private Sponsor sponsor;
   private Sponsor selectedSponsor;
   private SponsorList sponsorList = new SponsorList();
   private static final String FILENAME = "SponsorList.txt";
   private FileReaderWriter sponsorFile = new FileReaderWriter(FILENAME);
   private VIAoms viaOms= new VIAoms();
   private ObservableList<Sponsor> sponsors = FXCollections.observableArrayList();
   private ObservableList<String> searchCriteria = FXCollections.observableArrayList("Name", "E-mail", "Phone");
   
   @FXML private BorderPane sponsorPage = new BorderPane();

   @FXML private Button btnAddSponsor, btnDeleteSponsor, btnEditSponsor,
         btnClearAddSponsorTextFields, btnSearchSponsors, btnCancelEditSponsor,
         btnClearEditSponsorTextFields, btnSaveSponsorEditChanges;

   @FXML private TextField tfSponsorID, tfShowSponsorEmail, tfEnterSponsorSearchKeywords,
         tfEnterSponsorEmail, tfShowSponsorName, tfShowSponsorPhoneNumber,
         tfEnterSponsorName, tfEnterSponsorPhoneNumber;

   @FXML private TableView<Sponsor> sponsorTable;

   @FXML private TableColumn<Sponsor, String> tcSponsorID, tcSponsorName, tcSponsorEmail,
         tcSponsorPhoneNumber;

   @FXML private ListView<Sponsor> lvSponsorSearchResults;

   @FXML private ComboBox<String> cbSponsorSearchCriteria;

   @FXML private Label lblSponsorCount;

   @FXML private HBox hboxSponsorEditOptions;
   
   @FXML private ScrollPane spSponsorsTableScrollPane;

   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      sponsorPage.setMaxHeight(Double.MAX_VALUE);
      sponsorPage.setMaxWidth(Double.MAX_VALUE);

      hboxSponsorEditOptions.setVisible(false);
      
      cbSponsorSearchCriteria.setItems(searchCriteria);
      cbSponsorSearchCriteria.getSelectionModel().select(0);

      tcSponsorName.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("name"));
      tcSponsorEmail.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("email"));
      tcSponsorPhoneNumber.setCellValueFactory(new PropertyValueFactory<Sponsor, String>("phone"));

      tcSponsorName.setStyle("-fx-alignment: CENTER;");
      tcSponsorEmail.setStyle("-fx-alignment: CENTER;");
      tcSponsorPhoneNumber.setStyle("-fx-alignment: CENTER;");
      
      btnEditSponsor.setDisable(true);
      btnDeleteSponsor.setDisable(true);
      
      tfShowSponsorName.setEditable(false);
      tfShowSponsorEmail.setEditable(false);
      tfShowSponsorPhoneNumber.setEditable(false);

      try
      {
         sponsorTable.setItems(getList());
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (ParseException e)
      {
         e.printStackTrace();
      }

      sponsorTable.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() == 1)
         {
            showSponsorDetailsFromTable();
            btnEditSponsor.setDisable(false);
            btnDeleteSponsor.setDisable(false);
         }
      });

      lvSponsorSearchResults.setOnMouseClicked((MouseEvent event) -> {
         if (event.getClickCount() == 1)
         {
            showSponsorDetailsFromListView();
            btnEditSponsor.setDisable(false);
            btnDeleteSponsor.setDisable(false);
         }
      });

      lblSponsorCount
            .setText(String.format("Sponsor count: %d", sponsorList.size()));
   }

   public void showSponsorDetailsFromTable()
   {
      if (sponsorTable.getSelectionModel().getSelectedItem() != null)
      {
         selectedSponsor = sponsorTable.getSelectionModel().getSelectedItem();
         tfShowSponsorName.setText(selectedSponsor.getName());
         tfShowSponsorEmail.setText(selectedSponsor.getEmail());
         tfShowSponsorPhoneNumber.setText(selectedSponsor.getPhone());
      }
   }

   public void showSponsorDetailsFromListView()
   {
      if (lvSponsorSearchResults.getSelectionModel().getSelectedItem() != null)
      {
         selectedSponsor = lvSponsorSearchResults.getSelectionModel().getSelectedItem();
         tfShowSponsorName.setText(selectedSponsor.getName());
         tfShowSponsorEmail.setText(selectedSponsor.getEmail());
         tfShowSponsorPhoneNumber.setText(selectedSponsor.getPhone());
      }
   }

   public ObservableList<Sponsor> getList() throws FileNotFoundException, ParseException
   {
      sponsorList = sponsorFile.readSponsorTextFile();

      for (int i = 0; i < sponsorList.size(); i++)
      {
         sponsors.add(new Sponsor(sponsorList.getSponsor(i).getName(),
               sponsorList.getSponsor(i).getEmail(),
               sponsorList.getSponsor(i).getPhone()));
      }
      return sponsors;
   }

   @FXML
   void addSponsor(ActionEvent event) throws ParseException, CloneNotSupportedException, IOException
   {
      String sponsorName = tfEnterSponsorName.getText();
      String sponsorEmail = tfEnterSponsorEmail.getText();
      String sponsorPhone = tfEnterSponsorPhoneNumber.getText();

      if (tfEnterSponsorName.getText().isEmpty()
            && tfEnterSponsorEmail.getText().isEmpty()
            && tfEnterSponsorPhoneNumber.getText().isEmpty())
      {
         sponsorName = String.format("empty%d", sponsorList.size() + 1);
         sponsorEmail = String.format("empty@empty%d", sponsorList.size() + 1); 
         sponsorPhone = String.format("empty%d", sponsorList.size() + 1);
      }

      else if (tfEnterSponsorName.getText().isEmpty())
      {
         sponsorName = String.format("empty%d", sponsorList.size() + 1);
      }

      else if (tfEnterSponsorEmail.getText().isEmpty())
      {
         sponsorEmail = String.format("empty@empty%d", sponsorList.size() + 1);
      }

      else if (tfEnterSponsorPhoneNumber.getText().isEmpty())
      {
         sponsorPhone = String.format("empty%d", sponsorList.size() + 1);
      }

      if (sponsorEmail.contains("@"))
      {
         sponsor = new Sponsor(sponsorName, sponsorEmail, sponsorPhone);

         if (viaOms.checkForSponsorDuplicates(sponsor))
         {
            JOptionPane.showMessageDialog(null,"Sponsor already exists in the system!");
            clearAddSponsorTextFields(event);
         }

         else
         {
            viaOms.addSponsor(sponsorName, sponsorEmail, sponsorPhone);
            
            sponsorTable.getItems().add(sponsor);
            clearAddSponsorTextFields(event);

            lblSponsorCount.setText(String.format("Sponsor count: %d", sponsorList.size()));
         }
      }

      else
      {
         JOptionPane.showMessageDialog(null,"Invalid e-mail format entered!\nFormat: example@gmail.com");
      }
   }

   @FXML
   void clearAddSponsorTextFields(ActionEvent event)
   {
      tfEnterSponsorName.setText("");
      tfEnterSponsorEmail.setText("");
      tfEnterSponsorPhoneNumber.setText("");
   }

   @FXML
   void searchSponsors(ActionEvent event)
   {
      ObservableList<Sponsor> searchResults = FXCollections
            .observableArrayList();
      int searchCriteriaComboBoxSelection = cbSponsorSearchCriteria
            .getSelectionModel().getSelectedIndex();
      String searchKeyword = tfEnterSponsorSearchKeywords.getText();
      tfEnterSponsorSearchKeywords.setText("");

      searchResults.clear();

      switch (searchCriteriaComboBoxSelection)
      {
         case 0:

            for (int i = 0; i < sponsors.size(); i++)
            {
               if (sponsors.get(i).getName().toLowerCase()
                     .contains(searchKeyword))
               {
                  searchResults.add(sponsors.get(i));
               }
            }

            if (searchResults.isEmpty())
            {
               JOptionPane.showMessageDialog(null,
                     "No sponsors found with the given search keyword: \n"
                           + searchKeyword);
            }

            break;

         case 1:

            for (int i = 0; i < sponsors.size(); i++)
            {
               if (sponsors.get(i).getEmail().toLowerCase()
                     .contains(searchKeyword))
               {
                  searchResults.add(sponsors.get(i));
               }
            }

            if (searchResults.isEmpty())
            {
               JOptionPane.showMessageDialog(null,
                     "No sponsors found with the given search keyword: "
                           + searchKeyword);
            }

            break;
            
         case 2:
        	 
            for (int i = 0; i < sponsors.size(); i++)
            {
               if (sponsors.get(i).getPhone().toLowerCase()
                     .contains(searchKeyword))
               {
                  searchResults.add(sponsors.get(i));
               }
            }

            if (searchResults.isEmpty())
            {
               JOptionPane.showMessageDialog(null,
                     "No sponsors found with the given search keyword: \n"
                           + searchKeyword);
            }

            break;

         default:
            break;
      }

      lvSponsorSearchResults.setItems(searchResults);
   }

   @FXML
   void editSponsor(ActionEvent event)
   {
      if (sponsorTable.getSelectionModel() != null)
      {
         System.out.println(sponsorTable.getSelectionModel());
         hboxSponsorEditOptions.setVisible(true);

         tfShowSponsorName.setEditable(true);
         tfShowSponsorEmail.setEditable(true);
         tfShowSponsorPhoneNumber.setEditable(true);

         tfShowSponsorName.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
         tfShowSponsorEmail.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
         tfShowSponsorPhoneNumber.setStyle("-fx-border-color: orange ; -fx-border-width: 1px ;");
      }
   }

   @FXML
   void saveEditSponsorChanges(ActionEvent event) throws FileNotFoundException, ParseException 
   {
     int index = viaOms.getSponsorList().getSponsorIndex(selectedSponsor);   
     
     String newSponsorName = tfShowSponsorName.getText();
     String newSponsorEmail = tfShowSponsorEmail.getText();
     String newSponsorPhone = tfShowSponsorPhoneNumber.getText();
     
     if(tfShowSponsorName.getText().isEmpty())
     {
        newSponsorName = "empty";
     }
     
     if(tfShowSponsorEmail.getText().isEmpty())
     {
        newSponsorEmail = "empty";
     }  
     
     if(tfShowSponsorPhoneNumber.getText().isEmpty())
     {
        newSponsorPhone = "empty";
     }  
     
     if(newSponsorEmail.contains("@"))
     {
    	 Sponsor tempSponsor = new Sponsor(newSponsorName,newSponsorEmail,newSponsorPhone);	 
     
    	 if (viaOms.checkForSponsorDuplicates(tempSponsor))
         {
            JOptionPane.showMessageDialog(null,"Sponsor already exists in the system!");
                        
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

         else
         {
        	 selectedSponsor.setName(newSponsorName);
             selectedSponsor.setEmail(newSponsorEmail);
             selectedSponsor.setPhone(newSponsorPhone);

             sponsorList.replaceSponsor(index,selectedSponsor);       
             sponsorFile.writeSponsorTextFile(sponsorList);

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
         }    
     }
     
     else
     {
    	 JOptionPane.showMessageDialog(null, "Invalid e-mail format entered!\nFormat: example@gmail.com");
     }
   }

   @FXML
   void clearEditSponsorTextFields(ActionEvent event)
   {
      tfShowSponsorName.setText("");
      tfShowSponsorEmail.setText("");
      tfShowSponsorPhoneNumber.setText("");
   }

   @FXML
   void cancelEditSponsor(ActionEvent event)
   {
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
   void deleteSponsor(ActionEvent event) throws FileNotFoundException, ParseException, ArrayIndexOutOfBoundsException
   {        
     if(sponsorTable.getSelectionModel() != null)
     {
        if(sponsorList.size() > 0)
        {
           try 
           {
              String[] options = {"Delete","Cancel"}; 
              int n = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to delete sponsor:\n" + selectedSponsor + " ?",
                        "Delete a sponsor",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);              
              
              if (n == JOptionPane.YES_OPTION) 
              {
                 int index = viaOms.getSponsorList().getSponsorIndex(selectedSponsor);
                 
                 viaOms.deleteSponsor(index);
                 //sponsorFile.writeSponsorTextFile(sponsorList);
                 sponsorTable.getItems().remove(index);
                 
                 tfShowSponsorName.setText("");
                 tfShowSponsorEmail.setText("");   
                 tfShowSponsorPhoneNumber.setText("");   
                 
                 btnEditSponsor.setDisable(true);
                 btnDeleteSponsor.setDisable(true);
                 
                 lblSponsorCount.setText(String.format("Sponsor count: %d", sponsorList.size()));
              }        
           }
           catch(ArrayIndexOutOfBoundsException e)
           {
              JOptionPane.showMessageDialog(null, "Please select a sponsor from the table to delete");
           }
        }        
        else
        {
           JOptionPane.showMessageDialog(null, "No more sponsors left to delete");
        }
     }     
     else
     {
        JOptionPane.showMessageDialog(null, "Please select a sponsor from the table to delete");
     }     
   }    
}
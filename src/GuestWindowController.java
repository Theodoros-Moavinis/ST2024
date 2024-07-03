import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class GuestWindowController extends Controller implements Initializable {

    @FXML
    private Button DIYButton;

    @FXML
    private Button LogInButton;

    @FXML
    private Label InformationLabel;

    @FXML
    private ListView<String> PackageListView;

     @FXML
    private PasswordField PartnerPasswordField;

    @FXML
    private TextField PartnerUsernameField;

    @FXML
    private Button ReservationButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private DatePicker StartDateField;

    @FXML
    private DatePicker EndDateField;

    @FXML
    private ChoiceBox<String> DestinationChoiceBox;

    @FXML
    private Button SearchButton;

    @FXML
    void SetLabelText(String text) {
        InformationLabel.setText(text);
    }

    @FXML
    void DIYButtonClicked(ActionEvent event) {

    }

    @FXML
    void LogInButtonClicked(ActionEvent event) {
        popup(event, "LogInWindow.fxml", "LogIn");
    }

    @FXML
    void ReservationButtonClicked(ActionEvent event) {
        System.out.println(PackageListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void SignUpButtonClicked(ActionEvent event) {
        popup(event, "RegisterWindow.fxml", "Register");
    }

    String currentString;

    // populates the ListView upon pressing the search button
    @FXML
    void SearchButtonClicked(ActionEvent event) {
        // υπάρχουν για δοκιμστικούς σκοππούς, πρέπει να αντικατασταθούν από sql κώδικα,
        // comment out αργότερα
        TouristPackage pack1 = new TouristPackage(LocalDate.parse("2024-06-09"), LocalDate.parse("2024-06-16"),
                "Thessaloniki", "Kapsis", "Bus");
        TouristPackage pack2 = new TouristPackage(LocalDate.parse("2024-06-09"), LocalDate.parse("2024-06-16"),
                "Thessaloniki", "Hlektron", "car");
        TouristPackage pack3 = new TouristPackage(LocalDate.parse("2024-06-09"), LocalDate.parse("2024-06-16"),
                "Kilkis", "Government facility", "on foot");
        TouristPackage pack4 = new TouristPackage(LocalDate.parse("2024-06-09"), LocalDate.parse("2024-06-16"),
                "Rodos", "Airbnb", "ship");

        ArrayList<TouristPackage> TouristPackagekList = new ArrayList<TouristPackage>();
        TouristPackagekList.add(pack1);
        TouristPackagekList.add(pack2);
        TouristPackagekList.add(pack3);
        TouristPackagekList.add(pack4);

        ArrayList<String> ListViewContents = new ArrayList<String>();

        for (int i = 0; i <= 3; i++) {
            TouristPackage currentPackage = TouristPackagekList.get(i);
            if (DestinationChoiceBox.getValue() == currentPackage.getLocation()) {
                if (StartDateField.getValue().equals(currentPackage.StartDate)
                        && EndDateField.getValue().equals(currentPackage.EndDate)) {
                    ListViewContents.add(TouristPackagekList.get(i).toString());
                }

            }
            PackageListView.getItems().clear();
            PackageListView.getItems().addAll(ListViewContents);

        }
        System.out.println(StartDateField.getValue());
        System.out.println(EndDateField.getValue());
    }

    // υπάρχουν για δοκιμστικούς σκοππούς, πρέπει να αντικατασταθούν από sql κώδικα,
    // comment out αργότερα
    String[] Destinations = { "Kilkis", "Thessaloniki", "Rodos" };

    @Override
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        DestinationChoiceBox.getItems().addAll(Destinations);
    }

}

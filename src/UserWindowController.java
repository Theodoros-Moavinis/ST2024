import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class UserWindowController extends Controller{

    @FXML
    private Button DIYButton;

    @FXML
    private ChoiceBox<?> DestinationChoiceBox;

    @FXML
    private DatePicker EndDateField;

    @FXML
    private Button ExitButton;

    @FXML
    private ListView<?> PackageListView;

    @FXML
    private Button ReservationButton;

    @FXML
    private Button SearchButton;

    @FXML
    private Button SettingsButton;

    @FXML
    private DatePicker StartDateField;

    @FXML
    private Label InformationLabel;

    @FXML
    void SetLabelText(String text) {
        InformationLabel.setText(text);
    }


    @FXML
    void DIYButtonClicked(ActionEvent event) {

    }

    @FXML
    void ExitButtonClicked(ActionEvent event) {
        switch_scene(event, "GuestWindow.fxml");
    }

    @FXML
    void ReservationButtonClicked(ActionEvent event) {

    }

    @FXML
    void SearchButtonClicked(ActionEvent event) {

    }

    @FXML
    void SettingsButtonClicked(ActionEvent event) {

    }

}
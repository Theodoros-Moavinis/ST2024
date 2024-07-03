import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeWindowController extends Controller {

    @FXML
    private Button GuestButton;

    @FXML
    private Button LogInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    void GuestButtonClicked(ActionEvent event) {
        switch_scene(event, "GuestWindow.fxml");
    }

    @FXML
    void LogInButtonClicked(ActionEvent event) {
        switch_scene(event, "LogInWindow.fxml");
    }

    @FXML
    void SignUpButtonClicked(ActionEvent event) {
        switch_scene(event, "RegisterWindow.fxml");
    }

}

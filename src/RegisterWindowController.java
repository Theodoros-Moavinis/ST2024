import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterWindowController extends Controller {

    private User user;
    private String username;
    private String password;
    private String emailV;
    private String phoneV;
    private String nameV;
    private String surnameV;
    private boolean isAdmin;

    private boolean fieldsAreValid = false;
    @FXML
    private Button BackButton;

    @FXML
    private Button ContinueButton;

    @FXML
    private TextField FirstNameField;

    @FXML
    private TextField LastNameField;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField PhoneNumberField;

    @FXML
    private TextField UsernameField;

    @FXML
    private TextArea ErrorTextArea;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private PasswordField RepeatPasswordField;

    @FXML
    void BackButtonClicked(ActionEvent event) {
        switch_scene(event, "WelcomeWindow.fxml");
    }

    @FXML
    void ContinueButtonClicked(ActionEvent event) throws IOException {
        if (inputIsValid()) {
            // ο current user είναι αυτός που θα μπει στην βάση

            user = new User(username, password, nameV, surnameV, emailV, phoneV, isAdmin);
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://pg-16c734367-zalasbes-c698.d.aivencloud.com:24952/defaultdb?ssl=require&user=avnadmin&password=AVNS_dEGOkX4LoUt1GeQ8w0a");
                String sql = "INSERT INTO tourist_office.users (username,\"password\",isadmin,phone,email,\"name\",surname) VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setBoolean(3, user.isAdmin());
                preparedStatement.setString(4, user.getPhone());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getName());
                preparedStatement.setString(7, user.getSurname());

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Important Information");
                    alert.setContentText("Successfully users's  entrance into base \nPress OK to close.");

                    alert.getButtonTypes().setAll(ButtonType.OK);

                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {

                            alert.close();
                        }
                    });
                    preparedStatement.close();
                    connection.close();
                }

                else {
                    System.out.println("Failed to make connection!");
                }
            } catch (SQLException e) {

                e.printStackTrace();

            }

            // δεν χρησιμοποιούμε switch_scene γιατί εχεί να μεταφέρει
            // δεδομένα μεταξύ controllers
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserWindow.fxml"));
            Parent root = loader.load();

            UserWindowController control = loader.getController();
            control.SetLabelText(UsernameField.getText());
            // Parent root = FXMLLoader.load(getClass().getResource(filename));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

    }

    void testName(TextField nameField) {
        boolean nameIsValid = false;
        fieldsAreValid = false;
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            ErrorTextArea.appendText("First/last name is required\n");
        }
        
        if ((name.matches("[a-zA-Z]+")
                && name.matches("[\\u0370-\\u03FF\\u1F00-\\u1FFF]+"))) {
            nameIsValid = true;
            fieldsAreValid = true;

        } else {
            ErrorTextArea.appendText("Names must use greek or latin alphabet\n");
            nameField.clear();
        }
    }

    void testEmail() {
        boolean emailIsValid = false;
        fieldsAreValid = false;

        String email = EmailField.getText().trim();
        if (email.isEmpty()) {
            ErrorTextArea.appendText("Email required\n");

        }
        
        if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            emailIsValid = true;
            fieldsAreValid = true;
        } else {
            ErrorTextArea.appendText("Email must only contain latin characters\n");
            EmailField.clear();
        }

    }

    void testPhone() {
        boolean phoneIsValid = false;
        if (PhoneNumberField.getText().isEmpty() || !PhoneNumberField.getText().matches("^69\\d{8}$")) {
            ErrorTextArea.appendText("Greek mobile phone number is required");
            phoneIsValid = false;
            fieldsAreValid = false;
        }
    }

    void testPassword() {
        String password = PasswordField.getText().trim();
        boolean passwordIsValid = false;

        if (password.length() >= 8) {
            if (password.matches(".*[A-Z].*")) {
                if (password.matches(".*[a-z].*")) {
                    if (password.matches(".*\\d.*")) {
                        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                            passwordIsValid = true;
                        }
                    }
                }
            }
        }

        if (passwordIsValid == false) {
            PasswordField.clear();
            ErrorTextArea.appendText("Password must have:\n");
            ErrorTextArea.appendText(">More than 8 characters\n");
            ErrorTextArea.appendText(">Both uppercase and lowercase charachters\n");
            ErrorTextArea.appendText("> Special characters\n");
            ErrorTextArea.appendText(">At least one number\n");
        }
    }

    void passwordsMatch () {
        fieldsAreValid = false;
        if(PasswordField.getText().equals(RepeatPasswordField.getText())) {
            fieldsAreValid = true;
        } else {
            ErrorTextArea.appendText("Passwords do not match\n");
        }
    }

    boolean inputIsValid() {
        ErrorTextArea.setText("");
        testName(FirstNameField);
        testName(LastNameField);
        testEmail();
        testPhone();
        testPassword();

        emailV = EmailField.getText();
        phoneV = PhoneNumberField.getText();
        username = UsernameField.getText();
        password = PasswordField.getText();
        nameV = FirstNameField.getText();
        surnameV = LastNameField.getText();
        isAdmin = false;

        return fieldsAreValid;
    }

}

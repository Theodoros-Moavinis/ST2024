import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class LogInWindowController extends Controller {
    private User user;
    private boolean find;

    @FXML
    private Button BackButton;

    @FXML
    private Button ContinueButton;

    @FXML
    private TextField UsernameField;

    @FXML
    private TextArea ErrorArea;

    @FXML
    private PasswordField PasswordField;

    @FXML
    void BackButtonClicked(ActionEvent event) {
        switch_scene(event, "WelcomeWindow.fxml");
    }

    @FXML
    void ContinueButtonClicked(ActionEvent event) throws IOException {

        find = false;
        validate();

        if (find == true) {
            if (user.isAdmin()) {
                switch_scene(event, "GuestWindow.fxml");
                // δοκιμαστικό, αλλαξε σε κατάλληλο fxml μετα
            } else
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserWindow.fxml"));
                    Parent root = loader.load();

                    UserWindowController control = loader.getController();
                    control.SetLabelText(UsernameField.getText());
                    // Parent root = FXMLLoader.load(getClass().getResource(filename));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {

                    e.printStackTrace();
                }

        }
    }

    // για να μπορεί να μεπει ενας test user, για δοκιμαστικόυς σκοπούς, comment out
    /*
     * if (UsernameField.getText().equals(testUser.getName()) &&
     * PasswordField.getText().equals(testUser.getPassword())) {
     * 
     * //δεν χρησιμοποιούμε switch_scene γιατί εχεί να μεταφέρει
     * //δεδομένα μεταξύ controllers
     * FXMLLoader loader = new
     * FXMLLoader(getClass().getResource("UserWindow.fxml"));
     * Parent root = loader.load();
     * 
     * UserWindowController control = loader.getController();
     * control.SetLabelText(UsernameField.getText());
     * // Parent root = FXMLLoader.load(getClass().getResource(filename));
     * Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     * 
     * Scene scene = new Scene(root);
     * stage.setScene(scene);
     * }
     */

    public void validate() {
        int i = 2;
        if (UsernameField.getText().isEmpty()
                || !UsernameField.getText().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{10,}$")) {
            i--;
            ErrorArea.setText(
                    "Fill username \n At least one lower,one upper and one digit (0-9)\n Not shorter than 10 chars\n Latin alphabet");
            UsernameField.clear();
        }

        if (PasswordField.getText().isEmpty() || !PasswordField.getText()
                .matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=|{}[\\\\]:;\\\"'<>,.?/]).{8,}$")) {
            i--;
            ErrorArea.setText(
                    "Fill password\n At least one lower, one upper, one digit(0-9),one symbol\n Not shorter than 8 chars\nLatin alphabet");
            PasswordField.clear();
        }

        if (i == 2) {
            try {
                Connection conn = DriverManager.getConnection(
                        "jdbc:postgresql://pg-16c734367-zalasbes-c698.d.aivencloud.com:24952/defaultdb?ssl=require&user=avnadmin&password=AVNS_dEGOkX4LoUt1GeQ8w0a");
                String sql = "SELECT * FROM tourist_office.users u WHERE u.username=? AND u.\"password\"=? ";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, UsernameField.getText());
                stmt.setString(2, PasswordField.getText());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {

                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    boolean isAdmin = rs.getBoolean("isadmin");
                    user = new User(username, password, name, surname, email, phone, isAdmin);
                    find = true;

                } else {

                    /*
                     * empty_username_Validate.setText("Invalid username or password");
                     * empty_username_Validate.setOpacity(1.0);
                     */
                    ErrorArea.setText("User not found");
                    UsernameField.clear();
                    PasswordField.clear();
                }

                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
                // handleSQLException(e);
            }
        }
    }
}

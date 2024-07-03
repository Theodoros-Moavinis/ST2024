import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class Controller {
    public void switch_scene(ActionEvent event, String filename){
            try {
            Parent root  = FXMLLoader.load(getClass().getResource(filename));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene =  new Scene(root);
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popup(ActionEvent event, String filename, String title){
        try {
            Parent popup_root =FXMLLoader.load(getClass().getResource(filename));
            Scene popup_scene = new Scene(popup_root);
            Stage popup_Stage = new Stage();
            popup_Stage.setTitle(title);
            popup_Stage.setScene(popup_scene);
            popup_Stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    public void populateChoiceBox(ChoiceBox CB, String[] items){
        //maybe add if it is needed

    }
    */
}

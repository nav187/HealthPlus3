/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthPlus;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anju
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Label loginmsg;

    private Connection conn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //login backend:
    @FXML
    private void btnLoginAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

        try {

            conn = DbConn.connect();

            String sql = "select username, password, login_id from PR_LOGIN where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username_field.getText());
            ps.setString(2, password_field.getText());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                loginmsg.setText("");

                //hide login scene, and only load patientsearch:
                ((Node) (event.getSource())).getScene().getWindow().hide();

                //opens up the patient screen for now as no other screens are done. But once the dashboard is done, need to link that FXML.
                Parent parent = FXMLLoader.load(getClass().getResource("HealthPlusMain.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("HealthPlus");
                stage.show();

            } else {
                loginmsg.setText("Invalid username and/or password. Please try again.");
                username_field.setText("");
                password_field.setText("");
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    @FXML
    private void forgotpassword(ActionEvent event) {

    }

}

package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    public MenuItem mnuItmChangeCategories;
    public TextField txtFieldUserName;
    public PasswordField passwordFieldPwd;
    public Button btnLogin;

    public void loginAction(ActionEvent event) {
        if(btnLogin.getText().equals("Login")) {
            btnLogin.setText("Logout");
        }

        else {
            btnLogin.setText("Login");
        }
    }
}

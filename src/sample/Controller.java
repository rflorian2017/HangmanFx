package sample;

import constants.ApplicationConstants;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class Controller {
    public MenuItem mnuItmChangeCategories;
    public TextField txtFieldUserName;
    public PasswordField passwordFieldPwd;
    public Button btnLogin;
    public TabPane tabPane;
    public Tab tabPlay;
    public Tab tabLogin;

    public void loginAction(ActionEvent event) {
        if(btnLogin.getText().equals(ApplicationConstants.BTN_LOGIN_TEXT)) {
            btnLogin.setText(ApplicationConstants.BTN_LOGOUT_TEXT);
        }

        else {
            btnLogin.setText(ApplicationConstants.BTN_LOGIN_TEXT);
        }
    }

    public void activateLoginTab(ActionEvent event) {
        tabPane.getTabs().get(1).setDisable(false);
    }
}

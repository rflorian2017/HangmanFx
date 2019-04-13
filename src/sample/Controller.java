package sample;

import constants.ApplicationConstants;
import helper.Utility;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Controller {
    public MenuItem mnuItmChangeCategories;
    public TextField txtFieldUserName;
    public PasswordField passwordFieldPwd;
    public Button btnLogin;
    public TabPane tabPane;
    public Tab tabPlay;
    public Tab tabLogin;
    public Label lblUsername;
    public Label lblPassword;
    public Tab tabCategories;
    public Button btnAddCategory;
    public TextField txtFieldCategoryName;

    public void loginAction(ActionEvent event) {
        if (btnLogin.getText().equals(ApplicationConstants.BTN_LOGIN_TEXT)) {

            if (txtFieldUserName.getText().equals(ApplicationConstants.APP_USERNAME) &&
                    passwordFieldPwd.getText().equals(ApplicationConstants.APP_PASSWORD)) {

                btnLogin.setText(ApplicationConstants.BTN_LOGOUT_TEXT);
                lblPassword.setTextFill(Color.BLACK);
                lblUsername.setTextFill(Color.BLACK);
                tabPane.getTabs().get(2).setDisable(false);
                tabPane.getSelectionModel().select(tabCategories);

            }
            else {
                lblPassword.setTextFill(Color.RED);
                lblUsername.setTextFill(Color.RED);
            }
        } else {
            btnLogin.setText(ApplicationConstants.BTN_LOGIN_TEXT);
            tabPane.getSelectionModel().select(tabPlay);
            tabPane.getTabs().get(1).setDisable(true);
            tabPane.getTabs().get(2).setDisable(true);
        }
    }

    public void activateLoginTab(ActionEvent event) {
        tabPane.getTabs().get(1).setDisable(false);
        tabPane.getSelectionModel().select(tabLogin);
    }

    public void loginEnterKey(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            loginAction(null);
        }
    }

    public void addCategory(ActionEvent event) {
        if(!txtFieldCategoryName.getText().isEmpty()) {
            Utility.createCategoryFile(txtFieldCategoryName.getText());
        }
    }
}

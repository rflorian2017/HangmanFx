package sample;

import constants.ApplicationConstants;
import helper.CategoryParser;
import helper.Utility;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Category;

import java.io.IOException;


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
    public ComboBox comboboxCategories;
    public TextField txtFieldNewWord;
    public TextField txtFieldNewHint;
    public Button btnAddWord;
    public Label lblWordTabCategory;
    public Label lblCategoryNameCombobox;
    public CheckBox chkBoxCleanWords;


    public void initialize() {
        tabPane.getTabs().remove(tabLogin);
        //tabPane.getTabs().remove(tabCategories);
    }

    public void loginAction(ActionEvent event) {
        //text of button login is Login
        if (btnLogin.getText().equals(ApplicationConstants.BTN_LOGIN_TEXT)) {

            //correct user name and password
            if (txtFieldUserName.getText().equals(ApplicationConstants.APP_USERNAME) &&
                    passwordFieldPwd.getText().equals(ApplicationConstants.APP_PASSWORD)) {

                btnLogin.setText(ApplicationConstants.BTN_LOGOUT_TEXT);
                lblPassword.setTextFill(Color.BLACK);
                lblUsername.setTextFill(Color.BLACK);

                //add tab categories
                tabPane.getTabs().add(tabCategories);
                //activate tab categories
                tabPane.getSelectionModel().select(tabCategories);

                //do not allow anymore to edit text field user name and password field
                txtFieldUserName.setEditable(false);
                passwordFieldPwd.setEditable(false);
                //uncomment this line to decorate text field with other font
                //txtFieldUserName.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 12));

            }
            //incorrect user name or password
            else {
                lblPassword.setTextFill(Color.RED);
                lblUsername.setTextFill(Color.RED);
            }
        }
        //button has text Logout
        else {
            //allow to edit text field user name and password field and clear the text
            txtFieldUserName.setEditable(true);
            passwordFieldPwd.setEditable(true);
            txtFieldUserName.clear();
            passwordFieldPwd.clear();

            btnLogin.setText(ApplicationConstants.BTN_LOGIN_TEXT);
            tabPane.getTabs().remove(tabLogin);
            tabPane.getTabs().remove(tabCategories);
            tabPane.getSelectionModel().select(tabPlay);
        }
    }

    public void activateLoginTab(ActionEvent event) {
        tabPane.getTabs().add(tabLogin);
        tabPane.getSelectionModel().select(tabLogin);
    }

    public void loginEnterKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            loginAction(null);
        }
    }

    public void addCategory(ActionEvent event) {
        if (!txtFieldCategoryName.getText().isEmpty()) {
            Utility.createCategoryFile(txtFieldCategoryName.getText());
            fillCategoryCombobox(null);
            comboboxCategories.getSelectionModel().select(txtFieldCategoryName.getText());
            txtFieldCategoryName.clear();
        }


    }

    public void fillCategoryCombobox(Event event) {
        if (tabCategories.isSelected()) {
            comboboxCategories.getItems().clear();

            try {
                comboboxCategories.getItems().addAll(
                        Utility.listFilesWithoutExtensionFromPath(
                                ApplicationConstants.APP_FOLDER_DATA_PATH +
                                        "\\" +
                                        ApplicationConstants.CATEGORIES_FOLDER_NAME));
            } catch (Exception e) {
                //do nothing
            } finally {
                System.out.println("here I am");
            }
        }

        //comboboxCategories.getSelectionModel().select(0);
    }

    public void handleAddWord(ActionEvent event) {
        boolean chkBoxCleanActive = chkBoxCleanWords.isSelected();

        // region combobox category
        if (comboboxCategories.getSelectionModel().getSelectedIndex() == -1) {
            lblCategoryNameCombobox.setTextFill(Color.RED);
        } else {
            lblCategoryNameCombobox.setTextFill(Color.BLACK);

            // region Text field new word
            if (!txtFieldNewWord.getText().isEmpty()) {
                lblWordTabCategory.setTextFill(Color.BLACK);
                try {
                    Category category = CategoryParser.parseCategoryFile(chkBoxCleanActive,
                            ApplicationConstants.APP_FOLDER_DATA_PATH +
                                    "\\" +
                                    ApplicationConstants.CATEGORIES_FOLDER_NAME + "\\" +
                                    comboboxCategories.getSelectionModel().getSelectedItem().toString()
                                    + ApplicationConstants.CATEGORY_FILE_EXTENSION);

                    //word already exists if
                    if(category.wordExists(txtFieldNewWord.getText())) {
                        lblWordTabCategory.setTextFill(Color.RED);
                    }
                    //else word does not exist
                    else {
                        if(chkBoxCleanActive) {
                            Utility.cleanWordsInCategory(category.getWordList(),
                                    comboboxCategories.getSelectionModel().getSelectedItem().toString());
                        }
                        lblWordTabCategory.setTextFill(Color.BLACK);
                        Utility.addWordInCategory(category.getLastIdOfWord() + 1,
                                txtFieldNewWord.getText(),
                                txtFieldNewHint.getText(),
                                comboboxCategories.getSelectionModel().getSelectedItem().toString());
                        comboboxCategories.getSelectionModel().select(-1);
                        txtFieldNewWord.clear();
                        txtFieldNewHint.clear();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            } else {
                lblWordTabCategory.setTextFill(Color.RED);
            }
            // endregion
        }
        // endregion


    }
}

package com.casino.ui;

import com.casino.Casino;
import com.casino.DatabaseManager;
import com.casino.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class LoginPanel {

    @FXML private Button loginButton;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Text invalidUsername;
    @FXML private Text invalidPassword;

    private Consumer<User> onSuccess;

    @FXML
    public void onKeyPress(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            login(null);
        }
    }

    @FXML
    public void login(ActionEvent evt) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(!validateCredentials(username, password)) {
            return;
        }
        User user = new User(username, password, 1000.0);
        if(onSuccess != null) {
            onSuccess.accept(user);
        }
        Casino casino = Casino.getInstance();
        Stage stage = casino.getPrimaryStage();

        // Switch to Select menu
        stage.hide();
        stage.getScene().setRoot(casino.getScenes().get("select"));
        stage.centerOnScreen();
        stage.show();
    }

    private boolean validateCredentials(String username, String password) {
        Casino casino = Casino.getInstance();
        if(username.isEmpty()) {
            casino.displayError(invalidUsername);
            return false;
        }
        if(password.isEmpty()) {
            casino.displayError(invalidPassword);
            return false;
        }
        DatabaseManager database = casino.getDatabase();
        // todo: other checks (database)
        return true;
    }

    public void onSuccess(Consumer<User> onSuccess) {
        this.onSuccess = onSuccess;
    }
}

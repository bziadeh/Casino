package com.casino.ui;

import com.casino.Casino;
import com.casino.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.function.Consumer;

public class LoginPanel {

    @FXML private Button loginButton;
    private Consumer<User> onSuccess;

    @FXML
    public void login(ActionEvent evt) {
        User user = new User();
        if(onSuccess != null) {
            onSuccess.accept(user);
        }
        Casino casino = Casino.getInstance();
        Stage stage = casino.getPrimaryStage();

        // temp
        stage.hide();
        stage.getScene().setRoot(casino.getScenes().get("select"));
        stage.centerOnScreen();
        stage.show();
    }

    public void onSuccess(Consumer<User> onSuccess) {
        this.onSuccess = onSuccess;
    }
}

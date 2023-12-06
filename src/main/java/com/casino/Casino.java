package com.casino;

import com.casino.games.Blackjack;
import com.casino.ui.AdminPanel;
import com.casino.ui.LoginPanel;
import com.casino.ui.SelectPanel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

public class Casino extends Application {

    @Getter
    private static Casino instance;

    // Stores all loaded scenes for future access.
    @Getter
    private final Map<String, Parent> scenes = new HashMap<>();

    // Allows us to read and write to the database.
    @Getter
    private final DatabaseManager database = DatabaseManager.get();

    // Allows us to change game state from other modules.
    @Getter
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        primaryStage = stage;

        ClassLoader loader = getClass().getClassLoader();
        FXMLLoader loginLoader = new FXMLLoader(loader.getResource("Login.fxml"));
        FXMLLoader selectLoader = new FXMLLoader(loader.getResource("Select.fxml"));
        FXMLLoader adminLoader = new FXMLLoader(loader.getResource("Admin.fxml"));
        FXMLLoader blackjackLoader = new FXMLLoader(loader.getResource("Blackjack.fxml"));

        Parent loginParent = loginLoader.load();
        Parent selectParent = selectLoader.load();
        Parent adminParent = adminLoader.load();
        Parent blackjackParent = blackjackLoader.load();

        LoginPanel loginPanel = loginLoader.getController();
        loginPanel.onSuccess((user) -> {
            ((SelectPanel)selectLoader.getController()).setUser(user);
            ((AdminPanel)adminLoader.getController()).setUser(user);
            ((Blackjack)blackjackLoader.getController()).setUser(user);
        });

        scenes.put("login", loginParent);
        scenes.put("select", selectParent);
        scenes.put("admin", adminParent);
        scenes.put("blackjack", blackjackParent);

        stage.setTitle(Config.PROGRAM_TITLE);
        stage.getIcons().add(new Image("icons/icon.png"));
        stage.setScene(new Scene(loginParent));
        stage.setResizable(false);
        stage.show();
    }

    public void displayError(Text text) {
        if(text.isVisible()) {
            return;
        }
        text.setVisible(true);
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            text.setVisible(false);
        }).start();
    }
}

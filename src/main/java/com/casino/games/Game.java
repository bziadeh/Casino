package com.casino.games;

import com.casino.Casino;
import com.casino.Config;
import com.casino.ui.AbstractPanel;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;

public abstract class Game extends AbstractPanel {

    @Getter private boolean inGame;

    public Game() {
        listen();
    }

    public abstract void startGame();

    public abstract void stopGame();

    public abstract String getGameId();

    public void menu() {
        stopGame();
        Casino casino = Casino.getInstance();
        casino.getPrimaryStage().getScene().setRoot(casino.getScenes().get("select"));
    }

    public void setBalance(double amount) {
        getUser().setBalance(amount);
    }

    private void listen() {
        Thread listener = new Thread() {
            @SneakyThrows
            public void run() {
                // Listen and check if a player has started or exited this game
                while(true) {
                    Casino casino = Casino.getInstance();
                    Parent gameParent = casino.getScenes().get(getGameId());

                    Thread.sleep(Config.GAME_STATE_UPDATE_INTERVAL);
                    if(casino.getPrimaryStage().getScene() == null)
                        continue;

                    if(casino.getPrimaryStage().getScene().getRoot().equals(gameParent)) {
                        if(!inGame) {
                            inGame = true;
                            startGame();
                        }
                    } else {
                        if(inGame) {
                            inGame = false;
                            stopGame();
                        }
                    }
                }
            }
        };
        listener.setDaemon(true);
        listener.start();
    }
}

package com.casino.games;

import com.casino.Casino;
import com.casino.ui.AbstractPanel;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;

public abstract class Game extends AbstractPanel {

    @Getter private boolean inGame;

    public Game() {
        listen();
    }

    private void listen() {
        Thread listener = new Thread() {
            @SneakyThrows
            public void run() {
                // Listen and check if a player has started or exited this game
                while(true) {
                    final Casino casino = Casino.getInstance();
                    final Parent gameParent = casino.getScenes().get(getGameId());

                    Thread.sleep(100);
                    if(casino.getPrimaryStage().getScene() == null)
                        continue;

                    if(casino.getPrimaryStage().getScene().getRoot().equals(gameParent)) {
                        // This game is set as the root pane
                        if(!inGame) {
                            inGame = true;
                            startGame();
                        }
                    } else {
                        // This game is not set as the root pane
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

    public abstract void startGame();

    public abstract void stopGame();

    public abstract String getGameId();
}

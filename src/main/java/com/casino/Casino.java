package com.casino;

import com.casino.games.Game;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;

public class Casino extends JFrame {

    private Game game;
    private DatabaseManager database;

    public void onEnable() {
        database = DatabaseManager.get();
        // todo: implement onEnable()
    }

    public void onDisable() {
        // todo: implement onDisable()
    }

    public static void main(String[] args) {
        FlatLightLaf.setup(); // setup fancy Swing UI framework before creating swing components
        new Casino().onEnable();
    }
}

package com.casino;

import com.casino.games.Blackjack;
import com.casino.games.Roulette;
import com.casino.ui.AdminPanel;
import com.casino.ui.LoginPanel;
import com.casino.ui.SelectPanel;
import com.formdev.flatlaf.FlatLightLaf;
import lombok.Getter;
import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Casino extends JFrame {

    @Getter private DatabaseManager database;
    @Getter private CardLayout layout;

    public void onEnable() {
        database = DatabaseManager.get();
        layout = new CardLayout();

        // Create Static Panels
        LoginPanel login = new LoginPanel(this);
        SelectPanel select = new SelectPanel(this);
        AdminPanel admin = new AdminPanel(this);

        Container container = getContentPane();
        container.setLayout(layout);

        // Add Static Panels to ContentPane
        container.add(login, "login");
        container.add(select, "select");
        container.add(admin, "admin");

        // Handle Login Success
        login.onSuccess((user) -> {
            Blackjack blackjack = new Blackjack(this, user);
            Roulette roulette = new Roulette(this, user);
            container.add(blackjack, "blackjack");
            container.add(roulette, "roulette");
        });
        open();
    }

    private void open() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        // Calculate New Width and Height
        int newWidth = (int) (dimension.getWidth() / 1.5);
        int newHeight = (int) (dimension.getHeight() / 1.25);

        // Set JFrame Settings
        setSize(newWidth, newHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup(); // setup fancy Swing UI framework before creating swing components
        new Casino().onEnable();
    }
}

package com.casino.ui;

import com.casino.Casino;
import lombok.Getter;
import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel {

    @Getter private final Casino casino;

    public AbstractPanel(Casino casino) {
        this.casino = casino;
        onEnable();
    }

    public abstract void onEnable();
}

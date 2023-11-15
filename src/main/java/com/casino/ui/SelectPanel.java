package com.casino.ui;

import com.casino.Casino;
import javax.swing.JLabel;

public class SelectPanel extends AbstractPanel {

    public SelectPanel(Casino casino) {
        super(casino);
    }

    @Override
    public void onEnable() {
        add(new JLabel("Admin Page"));
        setVisible(true);
    }
}

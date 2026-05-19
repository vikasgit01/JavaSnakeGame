package engine.main;

import engine.components.Settings;

import javax.swing.*;

public class EngineWindow extends JFrame {

    private Settings settings;

    public EngineWindow(EnginePanel panel, Settings settings) {
        this.settings = settings;

        this.setTitle(settings.appName);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(settings.width, settings.height);

        this.add(panel);
        this.pack();

        this.setVisible(true);
    }
}

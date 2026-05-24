package engine.platform;

import engine.runtime.EngineContext;

import javax.swing.*;

public class EngineWindow extends JFrame {

    public EngineWindow(EnginePanel panel, EngineContext settings) {
        setTitle(settings.appName);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(settings.width, settings.height);

        add(panel);
        pack();
        setVisible(true);
    }
}

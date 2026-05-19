package engine.main;

import engine.components.Settings;

public class Loop extends Thread {

    EnginePanel panel;
    Settings settings;

    boolean running = true;

    Loop(EnginePanel panel, Settings settings) {

        this.panel = panel;
        this.settings = settings;
    }

    @Override
    public void run() {

        long drawInterval = 1000000000 / settings.fps;

        long lastTime = System.nanoTime();

        while (running) {

            long currentTime = System.nanoTime();

            if (currentTime - lastTime >= drawInterval) {

                // Update game
                panel.update();

                // Redraw screen
                panel.repaint();

                lastTime = currentTime;
            }
        }
    }
}

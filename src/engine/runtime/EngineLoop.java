package engine.runtime;

import engine.platform.EnginePanel;

public class EngineLoop extends Thread {

    private final EnginePanel panel;
    private final EngineContext settings;
    private boolean running = true;

    public EngineLoop(EnginePanel panel, EngineContext settings) {
        this.panel = panel;
        this.settings = settings;
    }

    @Override
    public void run() {
        long drawInterval = 1_000_000_000L / settings.fps;
        long lastTime = System.nanoTime();

        while (running) {
            long currentTime = System.nanoTime();

            if (currentTime - lastTime >= drawInterval) {
                Time.update();
                panel.update();
                panel.repaint();
                lastTime = currentTime;
            }
        }
    }

    public void stopLoop() {
        running = false;
    }
}

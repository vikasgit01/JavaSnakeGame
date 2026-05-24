package engine.runtime;

import engine.ecs.world.Scene;
import engine.ecs.world.SceneManager;
import engine.input.InputManager;
import engine.platform.EnginePanel;
import engine.platform.EngineWindow;

import java.awt.*;

public class Engine {

    private final EngineContext context;
    private final SceneManager sceneManager;
    private final EnginePanel panel;
    private final EngineWindow window;

    public Engine(EngineContext context) {
        this.context = context;
        sceneManager = new SceneManager();

        InputManager inputManager = new InputManager();
        panel = new EnginePanel(this, context, inputManager);
        window = new EngineWindow(panel, context);
    }

    public void addScene(Scene scene) {
        sceneManager.addScene(scene);
    }

    public EngineContext getContext() {
        return context;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void init() {
        for (Scene scene : sceneManager.getScenes()) {
            scene.init();
        }
    }

    public void play() {
        sceneManager.loadFirstScene();
        panel.startLoop();
    }

    public void loadScene(Scene scene) {
        sceneManager.loadScene(scene);
    }

    public void update() {
        for (Scene scene : sceneManager.getScenes()) {
            scene.update();
        }
    }

    public void draw(Graphics g) {
        for (Scene scene : sceneManager.getScenes()) {
            scene.draw(g);
        }
    }
}

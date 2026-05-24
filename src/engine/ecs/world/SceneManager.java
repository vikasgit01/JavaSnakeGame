package engine.ecs.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SceneManager {

    private final List<Scene> scenes = new ArrayList<>();
    private Scene activeScene;

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void loadScene(Scene scene) {
        activeScene = scene;
        scene.init();
    }

    public void loadFirstScene() {
        if (!scenes.isEmpty()) {
            loadScene(scenes.get(0));
        }
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    public List<Scene> getScenes() {
        return Collections.unmodifiableList(scenes);
    }
}

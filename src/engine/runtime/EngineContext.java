package engine.runtime;

import engine.ecs.components.Transform;
import engine.math.Vector2;

public class EngineContext {

    public String appName;
    public int tileSize;
    public int width;
    public int height;
    public int fps;

    public EngineContext(String appName, int tileSize, int width, int height, int fps) {
        this.appName = appName;
        this.tileSize = tileSize;
        this.width = width;
        this.height = height;
        this.fps = fps;
    }

    public boolean isOutOfBounds(Transform transform) {
        Vector2 position = transform.getPosition();
        Vector2 scale = transform.getScale();

        return position.getX() + scale.getX() >= width
                || position.getX() <= 0
                || position.getY() + scale.getY() >= height
                || position.getY() <= 0;
    }
}

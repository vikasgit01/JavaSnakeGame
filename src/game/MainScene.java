package game;

import engine.ecs.components.Transform;
import engine.math.Vector2;
import engine.ecs.world.Scene;

public class MainScene extends Scene {

    public MainScene(String name) {
        super(name);

        float snakeScale = 15f;

        Transform snakeTransform = new Transform(
                new Vector2((Game.settings.width - snakeScale) / 2, (Game.settings.height - snakeScale) / 2),
                new Vector2(snakeScale, snakeScale)
        );
        Snake snake = new Snake("snake");
        snake.addComponent(snakeTransform);

        addEntity(snake);
    }
}

package game;

import engine.ecs.components.Transform;
import engine.math.Vector2;
import engine.ecs.world.Scene;

public class MainScene extends Scene {

    public MainScene(String name) {
        super(name);

        float snakeScale = 16f;

        Transform snakeTransform = new Transform(
                new Vector2((Game.settings.width - snakeScale) / 2, (Game.settings.height - snakeScale) / 2),
                new Vector2(snakeScale, snakeScale)
        );

        Snake snake = new Snake("snake");
        snake.addComponent(snakeTransform);

        Transform appleTransform = new Transform(
                new Vector2(100, 100),
                new Vector2(8,8)
        );

        Apple apple = new Apple("apple");
        apple.addComponent(appleTransform);

        addEntity(apple);
        addEntity(snake);
    }
}

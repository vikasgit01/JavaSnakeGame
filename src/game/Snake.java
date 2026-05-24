package game;

import engine.ecs.components.Transform;
import engine.math.Vector2;
import engine.input.Keyboard;
import engine.runtime.Time;
import engine.ecs.entities.Entity;

import java.awt.*;

public class Snake extends Entity {

    private float velocityX = 1;
    private float velocityY = 0;
    private Direction currentDir = Direction.Right;
    private Vector2[] partsPosition;

    private final int bodyParts = 4;
    private final float spacing = 1;
    private final float speed = 5f;
    private float scale = 1f;
    private float moveAccumulator = 0f;
    private Transform transform;

    public Snake(String name) {
        super(name);
    }

    private Transform getTransform() {
        if (transform == null) {
            transform = getComponent(Transform.class);
        }
        return transform;
    }

    @Override
    public void init() {
        partsPosition = new Vector2[bodyParts];
        transform = getComponent(Transform.class);
        if (transform == null) {
            throw new IllegalStateException("Snake requires a Transform component");
        }
        scale = transform.getScale().getX();

        for (int i = 0; i < bodyParts; i++) {
            partsPosition[i] = new Vector2(
                    transform.getPosition().getX() - (scale + spacing) * (i + 1),
                    transform.getPosition().getY()
            );
        }
    }

    @Override
    public void update() {
        handleInput();
        moveSnake();
    }

    @Override
    public void draw(Graphics g) {
        Transform t = getTransform();
        if (t == null || partsPosition == null) {
            return;
        }

        g.setColor(Color.GREEN);
        g.fillRect(
                (int) t.getPosition().getX(),
                (int) t.getPosition().getY(),
                (int) t.getScale().getX(),
                (int) t.getScale().getY()
        );

        g.setColor(Color.WHITE);
        for (int i = 0; i < bodyParts; i++) {
            g.fillRect(
                    (int) partsPosition[i].getX(),
                    (int) partsPosition[i].getY(),
                    (int) t.getScale().getX(),
                    (int) t.getScale().getY()
            );
        }
    }

    private void handleInput() {
        if (Keyboard.right) {
            if (currentDir != Direction.Left) {
                velocityX = 1;
                velocityY = 0;
                currentDir = Direction.Right;
            }
        } else if (Keyboard.left) {
            if (currentDir != Direction.Right) {
                velocityX = -1;
                velocityY = 0;
                currentDir = Direction.Left;
            }
        } else if (Keyboard.up) {
            if (currentDir != Direction.Down) {
                velocityX = 0;
                velocityY = -1;
                currentDir = Direction.UP;
            }
        } else if (Keyboard.down) {
            if (currentDir != Direction.UP) {
                velocityX = 0;
                velocityY = 1;
                currentDir = Direction.Down;
            }
        }
    }

    private void moveSnake() {
        moveAccumulator += Time.deltaTime;
        float stepInterval = 1f / speed;

        while (moveAccumulator >= stepInterval) {
            moveAccumulator -= stepInterval;
            moveSnakeOneStep();
        }
    }

    private void moveSnakeOneStep() {
        Transform t = getTransform();
        if (t == null || partsPosition == null) {
            return;
        }

        float headX = t.getPosition().getX();
        float headY = t.getPosition().getY();
        float segmentDistance = scale + spacing;

        for (int i = bodyParts - 1; i > 0; i--) {
            partsPosition[i].setX(partsPosition[i - 1].getX());
            partsPosition[i].setY(partsPosition[i - 1].getY());
        }

        partsPosition[0].setX(headX);
        partsPosition[0].setY(headY);

        t.getPosition().setX(headX + segmentDistance * velocityX);
        t.getPosition().setY(headY + segmentDistance * velocityY);

        checkBounds(t);
    }

    private void checkBounds(Transform t) {
        if (t.getPosition().getX() < 0) {
            t.getPosition().setX(Game.settings.width - Game.settings.tileSize);
        }

        if (t.getPosition().getX() > Game.settings.width - Game.settings.tileSize) {
            t.getPosition().setX(0);
        }

        if (t.getPosition().getY() < 0) {
            t.getPosition().setY(Game.settings.height - Game.settings.tileSize);
        }

        if (t.getPosition().getY() > Game.settings.height - Game.settings.tileSize) {
            t.getPosition().setY(0);
        }
    }
}

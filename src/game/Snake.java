package game;

import engine.ecs.components.Transform;
import engine.ecs.entities.Entity;
import engine.input.Keyboard;
import engine.math.Vector2;
import engine.runtime.Time;

import java.awt.*;

public class Snake extends Entity {

    private static final int BODY_PARTS = 4;
    private static final float MOVE_SPEED = 5f;

    private Direction currentDirection = Direction.RIGHT;
    private Direction nextDirection = Direction.RIGHT;
    private Vector2[] partsPosition;

    private float segmentSize = 16f;
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
        partsPosition = new Vector2[BODY_PARTS];
        transform = getComponent(Transform.class);
        if (transform == null) {
            throw new IllegalStateException("Snake requires a Transform component");
        }

        segmentSize = transform.getScale().getX();
        snapToGrid(transform.getPosition());

        for (int i = 0; i < BODY_PARTS; i++) {
            partsPosition[i] = new Vector2(
                    transform.getPosition().getX() - segmentSize * (i + 1),
                    transform.getPosition().getY()
            );
            snapToGrid(partsPosition[i]);
        }
    }

    @Override
    public void update() {
        queueDirectionFromInput();
        moveSnake();
    }

    @Override
    public void draw(Graphics g) {
        Transform t = getTransform();
        if (t == null || partsPosition == null) {
            return;
        }

        int size = (int) segmentSize;

        g.setColor(Color.GREEN);
        g.fillRect((int) t.getPosition().getX(), (int) t.getPosition().getY(), size, size);

        g.setColor(Color.WHITE);
        for (int i = 0; i < BODY_PARTS; i++) {
            g.fillRect((int) partsPosition[i].getX(), (int) partsPosition[i].getY(), size, size);
        }
    }

    /**
     * Queues the next direction once per move step.
     * Rejects 180° turns against the direction the snake is actually traveling.
     */
    private void queueDirectionFromInput() {
        Direction requested = readRequestedDirection();
        if (requested == null || isOpposite(currentDirection, requested)) {
            return;
        }
        nextDirection = requested;
    }

    private Direction readRequestedDirection() {
        if (Keyboard.right) {
            return Direction.RIGHT;
        }
        if (Keyboard.left) {
            return Direction.LEFT;
        }
        if (Keyboard.up) {
            return Direction.UP;
        }
        if (Keyboard.down) {
            return Direction.DOWN;
        }
        return null;
    }

    private static boolean isOpposite(Direction current, Direction requested) {
        return current.getDx() + requested.getDx() == 0
                && current.getDy() + requested.getDy() == 0;
    }

    private void moveSnake() {
        moveAccumulator += Time.deltaTime;
        float stepInterval = 1f / MOVE_SPEED;

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

        currentDirection = nextDirection;

        float headX = t.getPosition().getX();
        float headY = t.getPosition().getY();

        for (int i = BODY_PARTS - 1; i > 0; i--) {
            partsPosition[i].setX(partsPosition[i - 1].getX());
            partsPosition[i].setY(partsPosition[i - 1].getY());
        }

        partsPosition[0].setX(headX);
        partsPosition[0].setY(headY);

        t.getPosition().setX(headX + segmentSize * currentDirection.getDx());
        t.getPosition().setY(headY + segmentSize * currentDirection.getDy());

        wrapPosition(t.getPosition());
        snapToGrid(t.getPosition());
    }

    private void wrapPosition(Vector2 position) {
        float maxX = Game.settings.width - segmentSize;
        float maxY = Game.settings.height - segmentSize;

        if (position.getX() < 0) {
            position.setX(maxX);
        } else if (position.getX() > maxX) {
            position.setX(0);
        }

        if (position.getY() < 0) {
            position.setY(maxY);
        } else if (position.getY() > maxY) {
            position.setY(0);
        }
    }

    private void snapToGrid(Vector2 position) {
        position.setX(Math.round(position.getX() / segmentSize) * segmentSize);
        position.setY(Math.round(position.getY() / segmentSize) * segmentSize);
    }
}

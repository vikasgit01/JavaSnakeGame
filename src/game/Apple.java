package game;

import engine.ecs.components.Transform;
import engine.ecs.entities.Entity;

import java.awt.*;

public class Apple extends Entity {

    private Transform transform;

    public Apple(String name) {
        super(name);
    }

    private Transform getTransform() {
        if (transform == null) {
            transform = getComponent(Transform.class);
        }
        return transform;
    }

    @Override
    public void init() { }

    @Override
    public void update() { }

    @Override
    public void draw(Graphics g) {
        Transform t = getTransform();
        g.setColor(Color.RED);
        g.fillOval(
                (int) t.getPosition().getX(),
                (int) t.getPosition().getY(),
                (int) t.getScale().getX(),
                (int) t.getScale().getY()
        );
    }
}

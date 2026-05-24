package engine.ecs.world;

import engine.ecs.entities.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    public String name;

    private final List<Entity> entities;

    public Scene(String name) {
        this.name = name;
        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void init() {
        for (Entity entity : entities) {
            entity.init();
        }
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public void draw(Graphics g) {
        for (Entity entity : entities) {
            entity.draw(g);
        }
    }
}

package engine.ecs.entities;

import engine.ecs.components.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

    protected String name;
    protected List<Component> components;

    public Entity(String name) {
        this.name = name;
        components = new ArrayList<>();
    }

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics g);

    public void addComponent(Component component) {
        if (component == null) {
            return;
        }
        components.add(component);
    }

    public <T extends Component> void removeComponent(Class<T> componentType) {
        if (componentType == null) {
            return;
        }

        for (Component component : components) {
            if (componentType.isAssignableFrom(component.getClass())) {
                components.remove(component);
                return;
            }
        }
    }

    public <T extends Component> T getComponent(Class<T> componentType) {
        if (componentType == null) {
            return null;
        }

        for (Component component : components) {
            if (componentType.isAssignableFrom(component.getClass())) {
                try {
                    return componentType.cast(component);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}

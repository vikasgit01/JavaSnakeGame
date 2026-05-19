package engine.main;

import engine.entities.Entity;
import engine.components.Settings;

import java.awt.*;
import java.util.ArrayList;

public class Engine {

    private ArrayList<Entity> objects;
    private EnginePanel panel;
    private EngineWindow window;

    public Engine(Settings settings) {
        objects = new ArrayList<>();

        panel = new EnginePanel(this, settings);
        window = new EngineWindow(panel, settings);
    }

    public void addObject(Entity obj) {
        objects.add(obj);
    }

    public void update() {
        for(Entity obj : objects) {
            obj.update();
        }
    }

    public void draw(Graphics g) {
        for(Entity obj : objects) {
            obj.draw(g);
        }
    }
}

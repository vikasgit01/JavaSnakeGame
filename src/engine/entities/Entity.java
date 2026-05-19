package engine.entities;

import engine.components.Transform;
import engine.components.Vector2;

import java.awt.*;

public abstract class Entity {

    protected Transform transform;

    public Entity(){
        transform = new Transform(new Vector2(0, 0), new Vector2(1,1));
    }
    public Entity(Transform transform){
        this.transform = transform;
    }

    public Transform getTransform(){
        return this.transform;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

}

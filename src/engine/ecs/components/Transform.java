package engine.ecs.components;

import engine.math.Vector2;

public class Transform extends Component {

    private Vector2 position;
    private Vector2 scale;
    private Vector2 pivot;

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getScale() {
        return scale;
    }

    public Vector2 getPivot() {
        return pivot;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public void setPivot(Vector2 pivot) {
        this.pivot = pivot;
    }

    public Transform() {
        position = new Vector2(0, 0);
        scale = new Vector2(10, 10);
        pivot = new Vector2(0, 0);
    }

    public Transform(Vector2 position, Vector2 scale) {
        setPosition(position);
        setScale(scale);
        pivot = new Vector2(0, 0);
    }

    public Transform(Vector2 position, Vector2 scale, Vector2 pivot) {
        setPosition(position);
        setScale(scale);
        setPivot(pivot);
    }
}

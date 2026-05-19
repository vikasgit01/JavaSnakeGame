package engine.components;

public class Transform {

    private Vector2 pos2D;
    private Vector2 scl2D;
    private Vector2 pivot;

    public Vector2 getPos2D() { return pos2D; }
    public Vector2 getScl2D(){
        return scl2D;
    }
    public Vector2 getPivot() { return pivot; }

    public void setPos2D(Vector2 pos2D){
        this.pos2D = pos2D;
    }
    public void setScl2D(Vector2 scl2D){
        this.scl2D = scl2D;
    }
    public void SetPivot(Vector2 pivot) { this.pivot = pivot; }

    public Transform() {
        pos2D = new Vector2(0,0);
        scl2D = new Vector2(10,10);
        pivot = new Vector2(0,0);
    }
    public Transform(Vector2 pos2D, Vector2 scl2D){
        setPos2D(pos2D);
        setScl2D(scl2D);
    }
    public Transform(Vector2 pos2D, Vector2 scl2D, Vector2 pivot){
        setPos2D(pos2D);
        setScl2D(scl2D);
        SetPivot(pivot);
    }
}

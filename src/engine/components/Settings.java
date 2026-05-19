package engine.components;

public class Settings {

    public String appName;
    public int tileSize;
    public int width;
    public int height;
    public int fps;

    public Settings(String appName, int tileSize, int width, int height, int fps){
        this.appName = appName;
        this.tileSize = tileSize;
        this.width = width;
        this.height = height;
        this.fps = fps;
    }

    public boolean InBounds(Transform transform) {
      return transform.getPos2D().getX() + transform.getScl2D().getX() >= width
                || transform.getPos2D().getX() <= 0
                || transform.getPos2D().getY() + transform.getScl2D().getY() >= height
                || transform.getPos2D().getY() <= 0;
    }

}

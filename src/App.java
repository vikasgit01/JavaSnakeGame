import engine.runtime.Engine;
import engine.runtime.EngineContext;
import game.Game;

public class App {

    public static void main(String[] args) {

        int screenWidth = 512;
        int screenHeight = 512;
        int fps = 60;
        int tileSize = 32;

        EngineContext context = new EngineContext("game", tileSize, screenWidth, screenHeight, fps);
        Engine engine = new Engine(context);
        Game game = new Game(engine, context);

    }
}

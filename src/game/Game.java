package game;

import engine.runtime.Engine;
import engine.runtime.EngineContext;

public class Game {

    public static EngineContext settings;

    public Game(Engine engine, EngineContext context) {
        settings = context;

        MainScene mainScene = new MainScene("Main");

        engine.addScene(mainScene);

        engine.play();
    }
}

package engine.platform;

import engine.input.InputManager;
import engine.runtime.Engine;
import engine.runtime.EngineContext;
import engine.runtime.EngineLoop;

import javax.swing.*;
import java.awt.*;

public class EnginePanel extends JPanel {

    private final Engine engine;
    private final EngineLoop loop;

    public EnginePanel(Engine engine, EngineContext context, InputManager inputManager) {
        this.engine = engine;

        setPreferredSize(new Dimension(context.width, context.height));
        setBackground(Color.BLACK);
        setFocusable(true);

        inputManager.attach(this);
        loop = new EngineLoop(this, context);
    }

    public void startLoop() {
        loop.start();
    }

    public void update() {
        engine.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        engine.draw(g);
    }
}

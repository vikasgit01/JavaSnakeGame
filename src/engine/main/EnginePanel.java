package engine.main;

import engine.inputs.KeyboardInputs;
import engine.components.Settings;
import engine.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class EnginePanel extends JPanel {

    private final Engine engine;
    private final Loop loop;

    public EnginePanel(Engine engine, Settings settings) {
        this.engine = engine;

        this.setPreferredSize(new Dimension(settings.width, settings.height));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        MouseInputs mouseInputs = new MouseInputs();
        addKeyListener(new KeyboardInputs());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        this.loop = new Loop(this, settings);
        this.loop.start();
    }

    public void update(){
        engine.update();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        engine.draw(g);
    }
}

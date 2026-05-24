package engine.input;

import java.awt.Component;

public class InputManager {

    private final Keyboard keyboard = new Keyboard();
    private final Mouse mouse = new Mouse();

    public void attach(Component component) {
        component.addKeyListener(keyboard);
        component.addMouseListener(mouse);
        component.addMouseMotionListener(mouse);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }
}

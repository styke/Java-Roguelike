package gui.elements;

import game.Renderer;

public class Popup extends Element {
    private Element display;

    public Element getDisplay() {
        return display;
    }

    public void setDisplay(Element display) {
        this.display = display;
    }

    @Override
    public void update() {
        display.update();
    }

    @Override
    public void onKey(boolean[] keys) {
        super.onKey(keys);
        display.onKey(keys);
    }

    @Override
    public void render(Renderer renderer) {
        if (display != null) {
            int dw = display.getWidth();
            int dh = display.getHeight();
            renderer.drawRect(getX(), getY(), dw + 2, dh + 2);
            display.setX(getX() + 1);
            display.setY(getY() + 1);
            display.render(renderer);
        }
    }

    @Override
    public int getWidth() {
        return display.getWidth() + 2;
    }

    @Override
    public int getHeight() {
        return display.getHeight() + 2;
    }
}

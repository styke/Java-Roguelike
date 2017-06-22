package gui;

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
    public void render(Renderer renderer) {
        if (display != null) {
            int cx = renderer.getWidth() / 2;
            int cy = renderer.getHeight() / 2;
            int dw = display.getWidth();
            int dh = display.getHeight();
            renderer.drawRect(cx - dw / 2 - 1, cy - dh / 2 - 1, dw + 2, dh + 2);
            display.setX(cx - dw / 2);
            display.setY(cy - dh / 2);
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

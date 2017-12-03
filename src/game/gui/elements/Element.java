package game.gui.elements;

import game.Renderer;

public abstract class Element {
    private int x, y;

    public Element() {

    }

    public abstract void update();

    public abstract void render(Renderer renderer);

    public abstract int getWidth();

    public abstract int getHeight();


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void onKey(boolean[] keys) {

    }
}

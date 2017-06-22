package gui;

import game.Renderer;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_UP;

public class List<T> extends Element {
    private int selected;
    private ArrayList<T> items;

    private boolean keyUp = false;
    private boolean keyDown = false;

    public List() {
        selected = 0;
        items = new ArrayList<>();
    }

    public void up() {
        selected--;
        if (selected < 0) {
            selected = items.size() - 1;
        }
    }

    public void down() {
        selected++;
        selected %= items.size();
    }

    public T getSelected() {
        if (items.size() == 0) return null;
        return items.get(selected);
    }

    public ArrayList<T> getModel() {
        return items;
    }

    public void setModel(ArrayList<T> items) {
        this.items = items;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        for (int i = 0; i < items.size(); i++) {
            if (i == selected) {
                renderer.drawChar(getX(), getY() + i, '>');
            }
            renderer.drawText(getX() + 1, getY() + i, items.get(i).toString());
        }
    }

    // TODO: Precompute widths and heights, but change them when needed instead of computing on every request.

    @Override
    public int getWidth() {
        int maxLength = 0;
        for (T it : items) {
            int l = it.toString().length();
            if (l > maxLength) maxLength = l;
        }
        return maxLength + 1;
    }

    @Override
    public int getHeight() {
        return items.size();
    }

    @Override
    public void onKey(boolean[] keys) {
        if (keyDown && !keys[VK_DOWN]) {
            keyDown = false;
            down();
        } else if (keyUp && !keys[VK_UP]) {
            keyUp = false;
            up();
        } else {
            if (keys[VK_DOWN]) {
                keyDown = true;
            } else if (keys[VK_UP]) {
                keyUp = true;
            }
        }
    }
}

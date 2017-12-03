package game.gui.elements;

import game.Renderer;

import java.util.ArrayList;

public class FlowLayout extends Element {
    private ArrayList<Element> elements;
    private Orientation orientation;

    public FlowLayout(Orientation orientation) {
        this.orientation = orientation;
        elements = new ArrayList<>();
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public void update() {
        for (Element e : elements) {
            e.update();
        }
    }

    @Override
    public void render(Renderer renderer) {
        int x = getX();
        int y = getY();
        for (Element e : elements) {
            e.setX(x);
            e.setY(y);
            if (orientation == Orientation.VERTICAL) {
                y += e.getHeight();
            } else {
                x += e.getWidth();
            }
            e.render(renderer);
        }
    }

    // TODO: Precompute widths and heights, but change them when needed instead of computing on every request.

    @Override
    public int getWidth() {
        if (orientation == Orientation.VERTICAL) {
            int maxWidth = 0;
            for (Element e : elements) {
                if (e.getWidth() > maxWidth) {
                    maxWidth = e.getWidth();
                }
            }
            return maxWidth;
        } else {
            int width = 0;
            for (Element e : elements) {
                width += e.getWidth();
            }
            return width;
        }
    }

    @Override
    public int getHeight() {
        if (orientation == Orientation.VERTICAL) {
            int height = 0;
            for (Element e : elements) {
                height += e.getHeight();
            }
            return height;
        } else {
            int maxHeight = 0;
            for (Element e : elements) {
                if (e.getHeight() > maxHeight) {
                    maxHeight = e.getHeight();
                }
            }
            return maxHeight;
        }
    }
}

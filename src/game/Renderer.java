package game;

import game.gui.elements.FlowLayout;
import game.gui.elements.Orientation;
import game.gui.elements.Popup;

import java.awt.*;

public class Renderer {
    private Spritesheet fontsheet;
    private Spritesheet spritesheet;
    private int fontSize;
    private int tileSize;
    private Graphics g;
    private int width, height;

    private FlowLayout root;
    private Popup popup;

    public Renderer(int width, int height) {
        spritesheet = new Spritesheet("../spritesheet.png", 16);
        fontsheet = new Spritesheet("../terminal.png", 8);

        this.fontSize = 16;
        this.tileSize = 32;
        this.width = width;
        this.height = height;

        root = new FlowLayout(Orientation.HORIZONTAL);
        popup = new Popup();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void renderRootLayout() {
        root.render(this);
    }

    public void updateRootLayout() {
        root.update();
    }

    public void updatePopup() {
        popup.update();
    }

    public void renderPopup() {
        popup.setX(getWidth() / getFontSize() / 2 - popup.getWidth() / 2);
        popup.setY(getHeight() / getFontSize() / 2 - popup.getHeight() / 2);
        popup.render(this);
    }

    public FlowLayout getRootLayout() {
        return root;
    }

    public Popup getPopup() {
        return popup;
    }

    public void drawSprite(int x, int y, int spriteX, int spriteY) {
        spritesheet.drawSprite(g, x, y, spriteX, spriteY, tileSize);
    }

    public int getFontSize() {
        return fontSize;
    }

    public void drawChar(int x, int y, char c) {
        int cx = c / 16;
        int cy = c % 16;
        drawChar(x, y, cx, cy);
    }

    public void drawChar(int x, int y, int charX, int charY) {
        fontsheet.drawSprite(g, x, y, charX, charY, fontSize);
    }

    public void drawText(int x, int y, String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            drawChar(x + i, y, c);
        }
    }

    public void drawHLine(int x, int y, int len) {
        for (int i = x; i < x + len; i++) {
            drawChar(i, y, 12, 4);
        }
    }

    public void drawVLine(int x, int y, int len) {
        for (int i = y; i < y + len; i++) {
            drawChar(x, i, 11, 3);
        }
    }

    public void drawRect(int x, int y, int w, int h) {
        w--;
        h--;
        drawHLine(x + 1, y, w - 1);
        drawHLine(x + 1, y + h, w - 1);
        drawVLine(x, y + 1, h - 1);
        drawVLine(x + w, y + 1, h - 1);
        drawChar(x, y + h, 12, 0);
        drawChar(x + w, y + h, 13, 9);
        drawChar(x, y, 13, 10);
        drawChar(x + w, y, 11, 15);

        for (int i = x + 1; i < x + w; i++) {
            for (int j = y + 1; j < y + h; j++) {
                drawChar(i, j, ' ');
            }
        }
    }

    public void drawTextWrapped(int x, int y, int maxLength, String s) {
        int i = x;
        int j = y;
        for (int k = 0; k < s.length(); k++) {
            char c = s.charAt(k);
            if (c == '\n') {
                j++;
                i = x;
                continue;
            }
            if (i + 1 == x + maxLength) {
                if (c != ' ') drawChar(i, j, '-');
                j++;
                i = x;
            }
            if (!(c == ' ' && i == x)) {
                drawChar(i, j, c);
                i++;
            }
        }
    }

    public Graphics getGraphics() {
        return g;
    }

    public void setGraphics(Graphics g) {
        this.g = g;
    }

    public int getTileSize() {
        return tileSize;
    }
}

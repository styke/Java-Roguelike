package game;

import gui.FlowLayout;
import gui.Orientation;
import gui.Popup;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Renderer {
    private BufferedImage spritesheet;
    private int tileSource;
    private int tileSize;
    private Graphics g;
    private FlowLayout root;
    private Popup popup;
    private int width, height;

    public Renderer(int tileSize, int width, int height) {
        try {
            spritesheet = ImageIO.read(Renderer.class.getResourceAsStream("../terminal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tileSource = 8;
        this.tileSize = tileSize;
        this.width = width / tileSize;
        this.height = height / tileSize;

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
        popup.render(this);
    }

    public FlowLayout getRootLayout() {
        return root;
    }

    public Popup getPopup() {
        return popup;
    }

    public void drawSprite(int dx, int dy, int sx, int sy) {
        int dxt = dx * tileSize;
        int dyt = dy * tileSize;
        int sxt = sx * tileSource;
        int syt = sy * tileSource;
        g.drawImage(spritesheet, dxt, dyt, dxt + tileSize, dyt + tileSize, sxt, syt, sxt + tileSource, syt + tileSource, null);
    }

    public void drawChar(int x, int y, char c) {
        int cx = c / 16;
        int cy = c % 16;
        drawSprite(x, y, cx, cy);
    }

    public void drawText(int x, int y, String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            drawChar(x + i, y, c);
        }
    }

    public void drawHLine(int x, int y, int len) {
        for (int i = x; i < x + len; i++) {
            drawSprite(i, y, 12, 4);
        }
    }

    public void drawVLine(int x, int y, int len) {
        for (int i = y; i < y + len; i++) {
            drawSprite(x, i, 11, 3);
        }
    }

    public void drawRect(int x, int y, int w, int h) {
        w--;
        h--;
        drawHLine(x + 1, y, w - 1);
        drawHLine(x + 1, y + h, w - 1);
        drawVLine(x, y + 1, h - 1);
        drawVLine(x + w, y + 1, h - 1);
        drawSprite(x, y + h, 12, 0);
        drawSprite(x + w, y + h, 13, 9);
        drawSprite(x, y, 13, 10);
        drawSprite(x + w, y, 11, 15);
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

package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {
    private BufferedImage image;
    private int sourceTileSize;

    public Spritesheet(String filename, int sourceTileSize) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sourceTileSize = sourceTileSize;
    }

    public void drawSprite(Graphics g, int x, int y, int spriteX, int spriteY, int tileSize) {
        int dxt = x * tileSize;
        int dyt = y * tileSize;
        int sxt = spriteX * sourceTileSize;
        int syt = spriteY * sourceTileSize;
        g.drawImage(image, dxt, dyt, dxt + tileSize, dyt + tileSize, sxt, syt, sxt + sourceTileSize, syt + sourceTileSize, null);
    }
}

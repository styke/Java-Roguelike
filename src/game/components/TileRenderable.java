package game.components;

import game.Component;
import game.Renderer;

public class TileRenderable extends Component {
    private int spriteX, spriteY;

    public int getSpriteX() {
        return spriteX;
    }

    public void setSpriteX(int spriteX) {
        this.spriteX = spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public void setSpriteY(int spriteY) {
        this.spriteY = spriteY;
    }

    public void render(int x, int y, Renderer renderer) {
        renderer.drawSprite(x, y, spriteX, spriteY);
    }

    /**
     * Render the sprite using {@link Positionable} component.
     */
    public void render(Renderer renderer) {
        try {
            Positionable component = getEntity().getComponent(Positionable.class);
            renderer.drawSprite(component.getX(), component.getY(), spriteX, spriteY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

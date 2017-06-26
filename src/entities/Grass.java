package entities;

import game.Entity;
import game.EntityType;
import game.Renderer;

public class Grass extends Entity {
    @Override
    public EntityType getType() {
        return EntityType.TILE;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawSprite(getX(), getY(), 4, 0);
    }
}

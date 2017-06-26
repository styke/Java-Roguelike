package entities;

import game.Entity;
import game.EntityType;
import game.Renderer;

public class Wall extends Entity {
    @Override
    public EntityType getType() {
        return EntityType.TILE;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawSprite(getX(), getY(), 3, 0);
    }

    @Override
    public boolean canPass(Entity sender) {
        return false;
    }
}

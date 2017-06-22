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
        renderer.drawChar(getX(), getY(), '#');
    }

    @Override
    public boolean canPass(Entity sender) {
        return false;
    }
}

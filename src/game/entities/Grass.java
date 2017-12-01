package game.entities;

import game.Entity;
import game.EntityType;
import game.Renderer;
import game.components.Positionable;

public class Grass extends Entity {
    @Override
    public void initComponents() {
        addComponent(new Positionable(EntityType.TILE));
    }

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

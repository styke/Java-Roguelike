package entities;

import game.Entity;
import game.EntityType;
import game.Renderer;

public class Door extends Entity {

    private boolean unlocked;

    public Door(boolean unlocked) {
        this.unlocked = unlocked;
    }

    @Override
    public EntityType getType() {
        return EntityType.TILE;
    }

    @Override
    public boolean canPass(Entity sender) {
        return unlocked;
    }

    @Override
    public void update() {

    }

    @Override
    public void onInteract(Entity sender) {
        unlocked = !unlocked;
    }

    @Override
    public void render(Renderer renderer) {
        if (unlocked) {
            renderer.drawSprite(getX(), getY(), 1, 0);
        } else {
            renderer.drawSprite(getX(), getY(), 0, 0);
        }
    }
}

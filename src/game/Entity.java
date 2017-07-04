package game;

import gui.FlowLayout;
import gui.Popup;

public abstract class Entity {

    private int x, y;
    private World world;
    private boolean dead = false;

    public void setPosition(int x, int y) {
        if (world != null) world.moveEntity(this.x, this.y, x, y, getType());
        this.x = x;
        this.y = y;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public abstract EntityType getType();

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (world != null) world.moveEntity(this.x, y, x, y, getType());
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (world != null) world.moveEntity(x, this.y, x, y, getType());
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Renderer renderer);

    // Events
    public void onSpawn(World world) {
        this.world = world;
    }

    public void onInitGUI(FlowLayout root, Popup popup) {
    }

    public void onKey(boolean[] keys) {
    }

    public void takeDamage(int amount) {

    }

    public void onAttacked(Entity sender, Item item) {
    }

    public void onInteract(Entity sender) {
    }

    public boolean canPass(Entity sender) {
        return true;
    }

    public Inventory getInventory() {
        return null;
    }
}

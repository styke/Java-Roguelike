package game;

import gui.elements.FlowLayout;

import java.util.HashMap;

public abstract class Entity {

    private int x, y;
    private World world;
    private boolean dead;
    private HashMap<String, Component> components;

    public abstract void initComponents();

    public Entity() {
        this.dead = false;
        this.components = new HashMap<>();
        initComponents();
    }

    public void setPosition(int x, int y) {
        if (world != null) world.moveEntity(this.x, this.y, x, y, getType());
        this.x = x;
        this.y = y;
    }

    public <T extends Component> T getComponent(Class<T> clazz) throws RuntimeException {
        String id = clazz.getName();
        if (components.containsKey(id)) return (T) components.get(id);
        else throw new RuntimeException(String.format("Entity doesn't contain component %s", id));
    }

    public void addComponent(Component component) {
        if (component == null) throw new NullPointerException("Entity component cannot be null.");
        components.put(component.getClass().getName(), component);
    }

    public <T extends Component> boolean hasComponent(Class<T> clazz) {
        return components.containsKey(clazz.getName());
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

    public void onInitGUI(FlowLayout root) {
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

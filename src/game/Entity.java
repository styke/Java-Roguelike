package game;

import java.util.HashMap;

public abstract class Entity {

    private Context context;
    private boolean dead;
    private HashMap<String, Component> components;
    private World world;

    public abstract void initComponents();

    /**
     * Determines what to do when a World is assigned to this Entity.
     */
    public void onSpawn() {
    }

    public void update() {
    }

    public void render(Renderer renderer) {
    }

    public void onKey(boolean[] keys) {
    }

    public Entity() {
        this.dead = false;
        this.components = new HashMap<>();
        initComponents();
    }

    public <T extends Component> T getComponent(Class<T> clazz) throws RuntimeException {
        String id = clazz.getName();
        if (components.containsKey(id)) return (T) components.get(id);
        else throw new RuntimeException(String.format("Entity doesn't contain component %s", id));
    }

    public void addComponent(Component component) {
        if (component == null) throw new NullPointerException("Entity component cannot be null.");
        component.setEntity(this);
        components.put(component.getClass().getName(), component);
    }

    public <T extends Component> boolean hasComponent(Class<T> clazz) {
        return components.containsKey(clazz.getName());
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}

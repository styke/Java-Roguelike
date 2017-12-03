package game;

import game.components.Positionable;

import java.util.ArrayList;
import java.util.Arrays;

public class World {
    public static final int DEFAULT_WORLD_SIZE = 30;
    private Entity entities[][];
    private int width, height;
    private Context context;

    public World(int width, int height) {
        this.entities = new Entity[EntityType.values().length][width * height];
        this.width = width;
        this.height = height;
    }

    public World() {
        this(DEFAULT_WORLD_SIZE, DEFAULT_WORLD_SIZE);
    }

    public Entity getEntity(int x, int y, EntityType type) {
        if (!inBounds(x, y)) return null;
        return entities[type.ordinal()][x + width * y];
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        for (EntityType type : EntityType.values()) {
            ArrayList<Entity> l = new ArrayList<>(Arrays.asList(entities[type.ordinal()]));
            for (Entity e : l) {
                if (e != null) {
                    e.setContext(context);
                }
            }
        }
    }

    public boolean isEntityAt(int x, int y, EntityType type) {
        return getEntity(x, y, type) != null;
    }

    public void addEntity(Entity entity) {
        try {
            Positionable component = entity.getComponent(Positionable.class);
            entities[component.getPositioning().ordinal()][component.getX() + component.getY() * width] = entity;
            entity.setContext(context);
            entity.onSpawn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveEntity(int x1, int y1, int x2, int y2, EntityType type) {
        entities[type.ordinal()][x2 + width * y2] = entities[type.ordinal()][x1 + width * y1];
        entities[type.ordinal()][x1 + width * y1] = null;
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public void render(Renderer renderer) {
        for (EntityType type : EntityType.values()) {
            ArrayList<Entity> l = new ArrayList<>(Arrays.asList(entities[type.ordinal()]));
            for (Entity e : l) {
                if (e != null) {
                    e.render(renderer);
                }
            }
        }
    }

    public void update() {
        for (EntityType type : EntityType.values()) {
            ArrayList<Entity> l = new ArrayList<>(Arrays.asList(entities[type.ordinal()]));
            for (Entity e : l) {
                if (e != null) {
                    e.update();
                }
            }
        }
    }

    public void onKey(boolean[] keys) {
        for (EntityType type : EntityType.values()) {
            ArrayList<Entity> l = new ArrayList<>(Arrays.asList(entities[type.ordinal()]));
            for (Entity e : l) {
                if (e != null) {
                    e.onKey(keys);
                }
            }
        }
    }
}

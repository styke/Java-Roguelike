package game;

public class Component {
    private Entity entity;

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        if (entity == null) throw new NullPointerException("Component must be bound to a non-null Entity.");
        return entity;
    }
}

package game.components;

import game.Component;
import game.EntityType;

public class Positionable extends Component {
    private int x, y;
    private EntityType positioning;

    public Positionable(EntityType positioningType) {
        x = 0;
        y = 0;
        positioning = positioningType;
    }

    public void setXY(int x, int y) {
        getEntity().getWorld().moveEntity(this.x, this.y, x, y, positioning);
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        getEntity().getWorld().moveEntity(this.x, this.y, x, this.y, positioning);
        this.x = x;
    }

    public void setY(int y) {
        getEntity().getWorld().moveEntity(this.x, this.y, this.x, y, positioning);
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public EntityType getPositioning() {
        return positioning;
    }
}

package game;

public abstract class Item {

    public Item() {

    }

    public abstract String getName();

    public void doDamage(Entity target) {

    }

    public void onConsumed(Entity sender) {

    }
}

package game;

public abstract class Item {

    public Item() {

    }

    public abstract String getName();

    public void onUsedOnEntity(Entity sender, Entity target) {

    }

    public void onConsume(Entity sender) {

    }
}

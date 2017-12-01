package game.entities;

import game.Entity;
import game.Inventory;
import game.Item;

public abstract class LivingEntity extends Entity {
    private int hp;
    private int maxHP;
    private int defense;
    private Inventory inventory;

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void takeDamage(int amount) {
        if (amount - defense > hp) setDead(true);
        else {
            hp -= amount - defense;
        }
    }

    @Override
    public void onAttacked(Entity sender, Item item) {
        item.doDamage(this);
    }

    @Override
    public boolean canPass(Entity sender) {
        return false;
    }
}

package gui;

import game.Inventory;
import game.ItemStack;

public class InventoryGUI extends List<ItemStack> {
    private Inventory inventory;

    public InventoryGUI(Inventory inventory) {
        this.inventory = inventory;
        setModel(inventory.getItems());
    }
}

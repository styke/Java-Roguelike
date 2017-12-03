package game.gui;

import game.Inventory;
import game.ItemStack;
import game.gui.elements.List;

public class InventoryGUI extends List<ItemStack> {
    private Inventory inventory;

    public InventoryGUI(Inventory inventory) {
        this.inventory = inventory;
        setModel(inventory.getItems());
    }
}

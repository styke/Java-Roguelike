package game;

import java.util.ArrayList;

public class Inventory {
    private int slots;
    private ArrayList<ItemStack> items;

    public Inventory(int slots) {
        this.slots = slots;
        items = new ArrayList<>();
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public void add(ItemStack itemStack) {
        for (ItemStack is : items) {
            if (is.getItem() == itemStack.getItem()) {
                is.add(itemStack.getCount());
                return;
            }
        }
        if (items.size() < slots - 1) {
            items.add(itemStack);
        }
    }

    public ItemStack getItemStack(Item item) {
        for (ItemStack is : items) {
            if (is.getItem() == item) {
                return is;
            }
        }
        return null;
    }

    public boolean has(Item item) {
        for (ItemStack is : items) {
            if (is.getItem() == item) {
                return true;
            }
        }
        return false;
    }

    public boolean has(ItemStack itemStack) {
        for (ItemStack is : items) {
            if (is.getItem() == itemStack.getItem() && is.getCount() >= itemStack.getCount()) {
                return true;
            }
        }
        return false;
    }

    public int take(ItemStack itemStack) {
        ItemStack it = getItemStack(itemStack.getItem());
        if (it != null) {
            int taken = it.take(itemStack.getCount());
            if (it.getCount() == 0) {
                items.remove(it);
            }
            return taken;
        }
        return 0;
    }
}

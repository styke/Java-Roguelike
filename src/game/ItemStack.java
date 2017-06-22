package game;

public class ItemStack {
    private Item item;
    private int count;

    public ItemStack(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public int take(int amount) {
        if (!has(amount)) {
            count = 0;
            return count;
        }
        count -= amount;
        return amount;
    }

    public boolean has(int amount) {
        return amount <= count;
    }

    public void add(int amount) {
        count += amount;
    }

    public void set(int amount) {
        count = amount;
    }

    @Override
    public String toString() {
        return String.format("%s [%d]", item.getName(), count);
    }
}

package Spring.Aplication.Basket.model;

import java.util.Objects;

public class Item {
    private long ItemId;

    public Item(long itemId) {
        ItemId = itemId;
    }

    public long getItemId() {
        return ItemId;
    }

    public void setItemId(long itemId) {
        ItemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return ItemId == item.ItemId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ItemId);
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemId=" + ItemId +
                '}';
    }
}
package stafftimesheet;

import entity.Item;

public class SellingTimeSheet {
    private Item item;
    private int soldItemNumber;

    public SellingTimeSheet() {
    }

    public SellingTimeSheet(Item item, int soldItemNumber) {
        this.item = item;
        this.soldItemNumber = soldItemNumber;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getSoldItemNumber() {
        return soldItemNumber;
    }

    public void setSoldItemNumber(int soldItemNumber) {
        this.soldItemNumber = soldItemNumber;
    }

    @Override
    public String toString() {
        return "SellingTimeSheet{" +
                "item=" + item +
                ", soldItemNumber=" + soldItemNumber +
                '}';
    }
}

package stafftimesheet;

import entity.Product;

public class SellingTimeSheet {
    private Product product;
    private int soldProductQuantity;

    public SellingTimeSheet() {
    }

    public SellingTimeSheet(Product product, int soldProductQuantity) {
        this.product = product;
        this.soldProductQuantity = soldProductQuantity;
    }

    public Product getItem() {
        return product;
    }

    public void setItem(Product product) {
        this.product = product;
    }

    public int getSoldProductQuantity() {
        return soldProductQuantity;
    }

    public void setSoldProductQuantity(int soldProductQuantity) {
        this.soldProductQuantity = soldProductQuantity;
    }

    @Override
    public String toString() {
        return "SellingTimeSheet{" +
                "item=" + product +
                ", soldItemNumber=" + soldProductQuantity +
                '}';
    }
}

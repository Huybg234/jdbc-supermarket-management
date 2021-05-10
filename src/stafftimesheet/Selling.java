package stafftimesheet;

import entity.Cashier;

import java.util.ArrayList;
import java.util.List;

public class Selling {
    private Cashier cashier;
    private List<SellingTimeSheet> sellingTimeSheets = new ArrayList<>();

    public Selling() {
    }

    public Selling(Cashier cashier, List<SellingTimeSheet> sellingTimeSheets) {
        this.cashier = cashier;
        this.sellingTimeSheets = sellingTimeSheets;
    }

    public Cashier getStaff() {
        return cashier;
    }

    public void setStaff(Cashier cashier) {
        this.cashier = cashier;
    }

    public List<SellingTimeSheet> getSellingTimeSheets() {
        return sellingTimeSheets;
    }

    public void setSellingTimeSheets(List<SellingTimeSheet> sellingTimeSheets) {
        this.sellingTimeSheets = sellingTimeSheets;
    }

    @Override
    public String toString() {
        return "StaffSelling{" +
                "staff=" + cashier +
                ", sellingTimeSheets=" + sellingTimeSheets +
                '}';
    }
}

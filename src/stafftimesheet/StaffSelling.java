package stafftimesheet;

import entity.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffSelling {
    private Staff staff;
    private List<SellingTimeSheet> sellingTimeSheets = new ArrayList<>();

    public StaffSelling() {
    }

    public StaffSelling(Staff staff, List<SellingTimeSheet> sellingTimeSheets) {
        this.staff = staff;
        this.sellingTimeSheets = sellingTimeSheets;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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
                "staff=" + staff +
                ", sellingTimeSheets=" + sellingTimeSheets +
                '}';
    }
}

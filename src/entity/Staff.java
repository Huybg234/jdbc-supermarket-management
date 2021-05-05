package entity;

import java.util.Scanner;

public class Staff extends Person {
    private int id;
    private String day;

    private static int AUTO_ID = 1000;

    public Staff() {
    }

    public Staff(int id, String name, String address, String phoneNumber, String day) {
        this.id = id;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    @Override
    public void inputInfo(){
        this.setId(Staff.AUTO_ID);

        super.inputInfo();
        System.out.println("Nhập ngày hợp đồng: ");
        this.day = new Scanner(System.in).nextLine();

        Staff.AUTO_ID++;
    }

    @Override
    public String toString() {
        return super.toString() + "Staff{" +
                "id=" + id +
                ", day='" + day + '\'' +
                '}';
    }
}

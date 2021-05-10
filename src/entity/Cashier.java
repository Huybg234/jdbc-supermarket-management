package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Cashier {
    private String name;
    private String address;
    private String phoneNumber;
    private int id;
    private String registrationDate;

    public static int AUTO_ID = 1000;

    public Cashier() {
    }

    public Cashier(int id, String name, String address, String phoneNumber, String registrationDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    public void inputInfo(){
        this.setId(Cashier.AUTO_ID);

        boolean check = true;
        System.out.println("Nhập tên: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.println("Nhập địa chỉ: ");
        this.address = new Scanner(System.in).nextLine();
        System.out.println("Nhập số điện thoại: ");
        this.phoneNumber = new Scanner(System.in).nextLine();
        System.out.println("Nhập ngày hợp đồng: ");
        do {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yy");
            sdf.setLenient(false);
            try {
                this.registrationDate = new Scanner(System.in).nextLine();
                sdf.parse(this.registrationDate);
                check = true;
            } catch (ParseException e) {
                System.out.println("Nhập date lỗi! Nhập lại: ");
                check = false;
            }
        }while (!check);

        Cashier.AUTO_ID++;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", id=" + id +
                ", registrationDate='" + registrationDate + '\'' +
                '}';
    }
}

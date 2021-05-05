package main;

import entity.Item;
import entity.Staff;
import repository.ItemDAO;
import repository.StaffDAO;
import repository.StaffSellingDAO;
import stafftimesheet.StaffSelling;
import util.CollectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainRun {
    public static List<Item> items = new ArrayList<>();
    public static List<Staff> staffs = new ArrayList<>();
    public static List<StaffSelling> staffSelling = new ArrayList<>();

    public static final ItemDAO itemDAO =new ItemDAO();
    public static final StaffDAO staffDAO = new StaffDAO();
    public static final StaffSellingDAO staffSellingDAO = new StaffSellingDAO();

    private static final ItemCreator itemCreator = new ItemCreator();
    public static final StaffCreator staffCreator = new StaffCreator();
    public static final StaffSellingCreator staffSellingCreator = new StaffSellingCreator();
    public static final SortAndCalculator sortAndCalculator = new SortAndCalculator();

    public static void main(String[] args) {
        System.out.println("Program is initializing ....");
//        init();
        System.out.println("Program is ready!");
        menu();
    }

    public static void menu(){
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    createNewItem();
                    printItem();
                    break;
                case 2:
                    createNewStaff();
                    printStaff();
                    break;
                case 3:
                    createNewStaffSelling();
                    printStaffSelling();
                    break;
                case 4:
                    sortAndCalculator.sortStaffSellingTable();
                case 6:
                    System.exit(0);
            }
        } while (true);
    }

    private static void init() {
        items = !CollectionUtil.isEmpty(itemDAO.getItems()) ? itemDAO.getItems() : new ArrayList<>();
        staffs = !CollectionUtil.isEmpty(staffDAO.getStaffs()) ? staffDAO.getStaffs() : new ArrayList<>();
    }

    public static int functionChoice() {
        System.out.println("--------QUẢN LÝ TÍNH CÔNG BÁN HÀNG SIÊU THỊ--------");
        System.out.println("1.Nhập danh sách mặt hàng mới");
        System.out.println("2.Nhập danh sách nhân viên bán hàng");
        System.out.println("3.Lập bảng danh sách bán hàng cho từng nhân viên");
        System.out.println("4.Sắp xếp danh sách");
        System.out.println("5.Lập bảng tính công cho mỗi nhân viên");
        System.out.println("6.Thoát");
        System.out.print("Nhập sự lựa chọn của bạn: ");
        int functionChoice = 0;
        boolean checkFunction = true;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                checkFunction = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                continue;
            }
            if (functionChoice <= 0 || functionChoice > 6) {
                System.out.print("Nhập số trong khoảng từ 1 đến 6! Nhập lại: ");
                checkFunction = false;
            } else break;
        } while (!checkFunction);
        return functionChoice;
    }

    public static void createNewItem(){
        itemCreator.createNewItem();
    }
    public static void printItem(){
        items.forEach(System.out::println);
    }

    public static void createNewStaff(){
        staffCreator.createNewStaff();
    }
    public static void printStaff(){
        staffs.forEach(System.out::println);
    }

    public static void createNewStaffSelling(){
        staffSellingCreator.createStaffSellingTable();
    }
    public static void printStaffSelling(){
        staffSelling.forEach(System.out::println);
    }
}

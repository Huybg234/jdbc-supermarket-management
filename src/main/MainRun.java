package main;

import entity.Product;
import entity.Cashier;
import repository.ProductDAO;
import repository.CashierDAO;
import repository.SellingDAO;
import stafftimesheet.Selling;
import util.CollectionUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainRun {
    public static List<Product> products = new ArrayList<>();
    public static List<Cashier> cashiers = new ArrayList<>();
    public static List<Selling> selling = new ArrayList<>();

    public static final ProductDAO PRODUCT_DAO =new ProductDAO();
    public static final CashierDAO CASHIER_DAO = new CashierDAO();
    public static final SellingDAO SELLING_DAO = new SellingDAO();

    private static final ProductCreator PRODUCT_CREATOR = new ProductCreator();
    public static final CashierCreator CASHIER_CREATOR = new CashierCreator();
    public static final CashierSellingCreator SELLING_CREATOR = new CashierSellingCreator();
    public static final SortAndCalculator sortAndCalculator = new SortAndCalculator();

    public static void main(String[] args) {
        System.out.println("Program is initializing ....");
        init();
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
                    break;
                case 5:
                    sortAndCalculator.salaryCashier();
                    break;
                case 6:
                    System.exit(0);
            }
        } while (true);
    }

    private static void init() {
        products = !CollectionUtil.isEmpty(PRODUCT_DAO.getItems()) ? PRODUCT_DAO.getItems() : new ArrayList<>();
        if (CollectionUtil.isEmpty(products)) {
            Product.AUTO_ID = 10000;
        } else {
            products.sort(Comparator.comparing(Product::getId));
            Product.AUTO_ID = products.get(products.size() - 1).getId() + 1;
        }
        cashiers = !CollectionUtil.isEmpty(CASHIER_DAO.getStaffs()) ? CASHIER_DAO.getStaffs() : new ArrayList<>();
        if (CollectionUtil.isEmpty(cashiers)) {
            Cashier.AUTO_ID = 10000;
        } else {
            cashiers.sort(Comparator.comparing(Cashier::getId));
            Cashier.AUTO_ID = cashiers.get(cashiers.size() - 1).getId() + 1;
        }
        selling = !CollectionUtil.isEmpty(SELLING_DAO.getSellingTimeSheet()) ? SELLING_DAO.getSellingTimeSheet() : new ArrayList();
    }

    public static int functionChoice() {
        System.out.println("--------QU???N L?? T??NH C??NG B??N H??NG SI??U TH???--------");
        System.out.println("1.Nh???p danh s??ch m???t h??ng m???i");
        System.out.println("2.Nh???p danh s??ch nh??n vi??n b??n h??ng");
        System.out.println("3.L???p b???ng danh s??ch b??n h??ng cho t???ng nh??n vi??n");
        System.out.println("4.S???p x???p danh s??ch");
        System.out.println("5.L???p b???ng t??nh c??ng cho m???i nh??n vi??n");
        System.out.println("6.Tho??t");
        System.out.print("Nh???p s??? l???a ch???n c???a b???n: ");
        int functionChoice = 0;
        boolean checkFunction = true;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                checkFunction = true;
            } catch (Exception e) {
                System.out.println("Kh??ng ???????c nh???p k?? t??? kh??c ngo??i s???! Nh???p l???i: ");
                continue;
            }
            if (functionChoice <= 0 || functionChoice > 6) {
                System.out.print("Nh???p s??? trong kho???ng t??? 1 ?????n 6! Nh???p l???i: ");
                checkFunction = false;
            } else break;
        } while (!checkFunction);
        return functionChoice;
    }

    public static void createNewItem(){
        PRODUCT_CREATOR.createNewItem();
    }
    public static void printItem(){
        products.forEach(System.out::println);
    }

    public static void createNewStaff(){
        CASHIER_CREATOR.createNewStaff();
    }
    public static void printStaff(){
        cashiers.forEach(System.out::println);
    }

    public static void createNewStaffSelling(){
        SELLING_CREATOR.createStaffSellingTable();
    }
    public static void printStaffSelling(){
        selling.forEach(System.out::println);
    }
}

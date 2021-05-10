package main;

import entity.Product;
import stafftimesheet.SellingTimeSheet;
import stafftimesheet.Selling;
import util.CollectionUtil;
import util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CashierSellingCreator {

    public boolean isValidItemAndStaff() {
        return !CollectionUtil.isEmpty(MainRun.products) && !CollectionUtil.isEmpty(MainRun.cashiers);
    }

    public void createStaffSellingTable() {
        if (!isValidItemAndStaff()) {
            System.out.println("Bạn cần nhập mặt hàng và nhân viên trước khi phân công");
            return;
        }
        boolean check = true;
        List<Selling> tempSelling = new ArrayList<>();
        for (int i = 0; i < MainRun.cashiers.size(); i++) {
            String staffName = MainRun.cashiers.get(i).getName();
            System.out.println("------Khai báo cho nhân viên " + staffName + "---------");
            System.out.println("Nhập số loại mặt hàng mà nhân viên " + staffName + " bán trong ngày: ");
            int staffSellingNumber = inputSellingTypeNumber();
            if (staffSellingNumber == 0){
                continue;
            }

            List<SellingTimeSheet> sellingTimeSheets = new ArrayList<>();
            for (int j = 0; j < staffSellingNumber; j++) {
                List<Integer> checkID = new ArrayList<>();
                System.out.println("Nhập id mặt hàng thứ " + (j + 1) + " mà nhân viên " + staffName + " bán trong ngày: ");
                Product product = inputItemId(checkID);
                System.out.println("Nhập số lượng loại mặt hàng này mà nhân viên bán được: ");
                int sellingTotal = inputSellingNumber();
                sellingTimeSheets.add(new SellingTimeSheet(product, sellingTotal));
            }
            Selling selling = new Selling(MainRun.cashiers.get(i), sellingTimeSheets);
            tempSelling.add(selling);
            MainRun.selling.add(selling);
        }
        MainRun.SELLING_DAO.insertNewStaffSelling(tempSelling);
    }

    private int inputSellingNumber() {
        int sellingTotal = 0;
        boolean isValidSellingTotal = true;
        do {
            try {
                sellingTotal = new Scanner(System.in).nextInt();
                isValidSellingTotal = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                isValidSellingTotal = false;
                continue;
            }
            if (sellingTotal <= 0) {
                System.out.print("Số mặt hàng không được nhỏ hơn 0! Nhập lại: ");
                isValidSellingTotal = false;
            }
        } while (!isValidSellingTotal);
        return sellingTotal;
    }

    private int inputSellingTypeNumber() {
        int staffSellingNumber = 0;
        boolean isValidSellingNumber = true;
        do {
            try {
                staffSellingNumber = new Scanner(System.in).nextInt();
                isValidSellingNumber = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                isValidSellingNumber = false;
                continue;
            }
            if (staffSellingNumber < 0 || staffSellingNumber > MainRun.products.size()) {
                System.out.print("Số mặt hàng không được nhỏ hơn 0 và lớn hơn tổng số mặt hàng! Nhập lại: ");
                isValidSellingNumber = false;
            }
        } while (!isValidSellingNumber);
        return staffSellingNumber;
    }

    private Product inputItemId(List<Integer> checkID) {
        int itemId = 0;
        boolean isValidItemId = true;
        do {
            try {
                itemId = new Scanner(System.in).nextInt();
                isValidItemId = true;
            } catch (Exception e) {
                System.out.println("không được có ký tự khác ngoài số! Nhập lại: ");
                isValidItemId = false;
            }
            for (Integer integer : checkID) {
                if (integer == itemId) {
                    System.out.println("Mặt hàng đã tồn tại! Nhập lại: ");
                    isValidItemId = false;
                    break;
                }
            }
            checkID.add(itemId);
        } while (!isValidItemId);

        Product product = searchItemId(itemId);
        if (ObjectUtil.isEmpty(product)) {
            System.out.print("Không có id mặt hàng vừa nhập! Nhập lại: ");
        }
        return product;
    }

    public static Product searchItemId(int id) {
        Optional<Product> itemOptional = MainRun.products.stream().filter(item -> item.getId() == id).findFirst();
        return itemOptional.orElse(null);
    }
}

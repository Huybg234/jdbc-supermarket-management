package main;

import entity.Item;
import stafftimesheet.SellingTimeSheet;
import stafftimesheet.StaffSelling;
import util.CollectionUtil;
import util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StaffSellingCreator {

    public boolean isValidItemAndStaff() {
        return !CollectionUtil.isEmpty(MainRun.items) && !CollectionUtil.isEmpty(MainRun.staffs);
    }

    public void createStaffSellingTable() {
        if (!isValidItemAndStaff()) {
            System.out.println("Bạn cần nhập mặt hàng và nhân viên trước khi phân công");
            return;
        }
        boolean check = true;
        List<StaffSelling> tempStaffSelling = new ArrayList<>();
        for (int i = 0; i < MainRun.staffs.size(); i++) {
            String staffName = MainRun.staffs.get(i).getName();
            System.out.println("------Khai báo cho nhân viên " + staffName + "---------");
            System.out.println("Nhập số loại mặt hàng mà nhân viên " + staffName + " bán trong ngày: ");
            int staffSellingNumber = inputSellingTypeNumber();

            List<SellingTimeSheet> sellingTimeSheets = new ArrayList<>();
            for (int j = 0; j < staffSellingNumber; j++) {
                List<Integer> checkID = new ArrayList<>();
                System.out.println("Nhập id mặt hàng thứ " + (j + 1) + " mà nhân viên " + staffName + " bán trong ngày: ");
                Item item = inputItemId(checkID);
                System.out.println("Nhập số lượng loại mặt hàng này mà nhân viên bán được: ");
                int sellingTotal = inputSellingNumber();
                sellingTimeSheets.add(new SellingTimeSheet(item, sellingTotal));
            }
            StaffSelling staffSelling = new StaffSelling(MainRun.staffs.get(i), sellingTimeSheets);
            tempStaffSelling.add(staffSelling);
            MainRun.staffSelling.add(staffSelling);
        }
        MainRun.staffSellingDAO.insertNewStaffSelling(tempStaffSelling);
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
            if (staffSellingNumber <= 0 || staffSellingNumber > MainRun.items.size()) {
                System.out.print("Số mặt hàng không được nhỏ hơn 0 và lớn hơn tổng số mặt hàng! Nhập lại: ");
                isValidSellingNumber = false;
            }
        } while (!isValidSellingNumber);
        return staffSellingNumber;
    }

    private Item inputItemId(List<Integer> checkID) {
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

        Item item = searchItemId(itemId);
        if (ObjectUtil.isEmpty(item)) {
            System.out.print("Không có id mặt hàng vừa nhập! Nhập lại: ");
        }
        return item;
    }

    public static Item searchItemId(int id) {
        Optional<Item> itemOptional = MainRun.items.stream().filter(item -> item.getId() == id).findFirst();
        return itemOptional.orElse(null);
    }

}

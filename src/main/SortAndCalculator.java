package main;

import entity.Item;
import stafftimesheet.SellingTimeSheet;
import util.CollectionUtil;

import java.util.Comparator;
import java.util.Scanner;

public class SortAndCalculator {
    public void sortStaffSellingTable(){
        if (CollectionUtil.isEmpty(MainRun.staffSelling)) {
            System.out.println("Nhập bảng phân công trước khi sắp xếp");
            return;
        }
        do {
            System.out.println("-----SẮP XẾP DANH SÁCH BÁN HÀNG-------");
            System.out.println("1.Sắp xếp theo tên nhân viên");
            System.out.println("2.Sắp xếp theo tên mặt hàng");
            System.out.println("3.Thoát");
            System.out.println("Hãy nhập sự lựa chọn của bạn: ");
            int choice = 0;
            boolean checkchoice = true;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    checkchoice = true;
                } catch (Exception e) {
                    System.out.println("Không được chứa ký tự khác ngoài số! Nhập lại");
                    checkchoice = false;
                    continue;
                }
                if (choice <= 0 || choice > 3) {
                    System.out.println("Nhập số trong khoảng từ 1 đến 3! Nhập lại: ");
                    checkchoice = false;
                }
            } while (!checkchoice);
            switch (choice) {
                case 1:
                    sortByStaffName();
                    break;
                case 2:
                    sortByItemName();
                    break;
                case 3:
                    return;
            }
        } while (true);
        }

    private void sortByStaffName() {
        MainRun.staffSelling.sort(Comparator.comparing(staffSelling -> staffSelling.getStaff().getName()));
        MainRun.printStaffSelling();
    }

    private void sortByItemName() {
        for (int i=0; i< MainRun.staffSelling.size(); i++){
            for (int j= 0; j< MainRun.staffSelling.get(i).getSellingTimeSheets().size(); j++){
                for (int k = j+1; k< MainRun.staffSelling.get(i).getSellingTimeSheets().size(); k++){
                    if (MainRun.staffSelling.get(i).getSellingTimeSheets().get(j).getItem().getName()
                            .compareTo(MainRun.staffSelling.get(i).getSellingTimeSheets().get(k).getItem().getName()) < 0){
                        SellingTimeSheet tmp = MainRun.staffSelling.get(i).getSellingTimeSheets().get(j);
                        MainRun.staffSelling.get(i).getSellingTimeSheets().set(j, MainRun.staffSelling.get(i).getSellingTimeSheets().get(k));
                        MainRun.staffSelling.get(i).getSellingTimeSheets().set(k, tmp);
                    }
                }
            }
        }
        MainRun.printStaffSelling();
    }
}

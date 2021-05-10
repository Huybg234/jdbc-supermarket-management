package main;

import stafftimesheet.SellingTimeSheet;
import util.CollectionUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortAndCalculator {
<<<<<<< HEAD
    public void sortStaffSellingTable(){
        if (CollectionUtil.isEmpty(MainRun.selling)) {
=======
    public void sortStaffSellingTable() {
        if (CollectionUtil.isEmpty(MainRun.staffSelling)) {
>>>>>>> d7a4e3e1230d6cc02df01bfcb5b02846181c6d81
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
        MainRun.selling.sort(Comparator.comparing(staffSelling -> staffSelling.getStaff().getName()));
        MainRun.printStaffSelling();
    }

    private void sortByItemName() {
<<<<<<< HEAD
        for (int i = 0; i< MainRun.selling.size(); i++){
            for (int j = 0; j< MainRun.selling.get(i).getSellingTimeSheets().size(); j++){
                for (int k = j+1; k< MainRun.selling.get(i).getSellingTimeSheets().size(); k++){
                    if (MainRun.selling.get(i).getSellingTimeSheets().get(j).getItem().getName()
                            .compareTo(MainRun.selling.get(i).getSellingTimeSheets().get(k).getItem().getName()) < 0){
                        SellingTimeSheet tmp = MainRun.selling.get(i).getSellingTimeSheets().get(j);
                        MainRun.selling.get(i).getSellingTimeSheets().set(j, MainRun.selling.get(i).getSellingTimeSheets().get(k));
                        MainRun.selling.get(i).getSellingTimeSheets().set(k, tmp);
                    }
                }
            }
        }
=======
        MainRun.staffSelling.forEach(staffSelling ->
                staffSelling.getSellingTimeSheets().sort(Comparator.comparing(sellingTimeSheet -> sellingTimeSheet.getItem().getName()))
        );

//        for (int i = 0; i < MainRun.staffSelling.size(); i++) {
//            for (int j = 0; j < MainRun.staffSelling.get(i).getSellingTimeSheets().size(); j++) {
//                for (int k = j + 1; k < MainRun.staffSelling.get(i).getSellingTimeSheets().size(); k++) {
//                    if (MainRun.staffSelling.get(i).getSellingTimeSheets().get(j).getItem().getName()
//                            .compareTo(MainRun.staffSelling.get(i).getSellingTimeSheets().get(k).getItem().getName()) < 0) {
//                        SellingTimeSheet tmp = MainRun.staffSelling.get(i).getSellingTimeSheets().get(j);
//                        MainRun.staffSelling.get(i).getSellingTimeSheets().set(j, MainRun.staffSelling.get(i).getSellingTimeSheets().get(k));
//                        MainRun.staffSelling.get(i).getSellingTimeSheets().set(k, tmp);
//                    }
//                }
//            }
//        }
>>>>>>> d7a4e3e1230d6cc02df01bfcb5b02846181c6d81
        MainRun.printStaffSelling();
    }

    public void salaryCashier(){
        if (CollectionUtil.isEmpty(MainRun.selling)) {
            System.out.println("Nhập bảng thống kê trước khi sắp xếp");
            return;
        }
        for (int i=0; i< MainRun.selling.size() -1; i++){
            System.out.println("Tính tiền công cho nhân viên "+MainRun.cashiers.get(i).getName());
            List<Float> salaryTotal = new ArrayList<>();
            for (int j=0; j < MainRun.selling.get(i).getSellingTimeSheets().size();j++){
                float tmp = (MainRun.selling.get(i).getSellingTimeSheets().get(j).getItem().getCost()
                        - MainRun.selling.get(i).getSellingTimeSheets().get(j).getItem().getImportCost())
                        * 2/100;
                salaryTotal.add(tmp);
            }
            float tmpSalary = 0;
            for (Float aFloat : salaryTotal) {
                tmpSalary += aFloat;
            }
            System.out.println(tmpSalary);
        }
    }
}

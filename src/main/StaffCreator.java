package main;

import entity.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffCreator {
    public void createNewStaff() {
        System.out.println("Nhập số lượng nhân viên muốn thêm mới: ");
        int staffQuantity = 0;
        boolean isValidStaffQuantity = true;
        do {
            try {
                staffQuantity = new Scanner(System.in).nextInt();
                isValidStaffQuantity = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                isValidStaffQuantity = false;
                continue;
            }
            if (staffQuantity <= 0) {
                System.out.print("Số lượng nhân viên không được nhỏ hơn 0! Nhập lại: ");
                isValidStaffQuantity = false;
            }
        } while (!isValidStaffQuantity);

        List<Staff> tempStaff = new ArrayList<>();
        for (int i = 0; i < staffQuantity; i++) {
            Staff staff = new Staff();
            staff.inputInfo();
            tempStaff.add(staff);
        }
        MainRun.staffs.addAll(tempStaff);
        MainRun.staffDAO.insertNewStaff(tempStaff);
    }
}

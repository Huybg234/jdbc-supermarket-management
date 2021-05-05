package main;

import entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemCreator {
    public void createNewItem() {
        System.out.println("Nhập số lượng mặt hàng muốn thêm mới: ");
        int itemQuantity = 0;
        boolean isValidItemQuantity = true;
        do {
            try {
                itemQuantity = new Scanner(System.in).nextInt();
                isValidItemQuantity = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                isValidItemQuantity = false;
                continue;
            }
            if (itemQuantity <= 0) {
                System.out.print("Số lượng mặt hàng không được nhỏ hơn 0! Nhập lại: ");
                isValidItemQuantity = false;
            }
        } while (!isValidItemQuantity);

        List<Item> tempItem = new ArrayList<>();
        for (int i = 0; i < itemQuantity; i++){
            Item item = new Item();
            item.inputItemInfo();
            tempItem.add(item);
        }
        MainRun.items.addAll(tempItem);
        MainRun.itemDAO.insertNewItem(tempItem);
    }
}

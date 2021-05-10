package main;

import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductCreator {
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

        List<Product> tempProduct = new ArrayList<>();
        for (int i = 0; i < itemQuantity; i++){
            Product product = new Product();
            product.inputItemInfo();
            tempProduct.add(product);
        }
        MainRun.products.addAll(tempProduct);
        MainRun.PRODUCT_DAO.insertNewItem(tempProduct);
    }
}

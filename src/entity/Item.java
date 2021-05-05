package entity;

import java.util.Scanner;

public class Item {

    private int id;
    private String name;
    private float buyPrice;
    private float sellPrice;
    private String itemGroup;

    private static int AUTO_ID = 1000;
    private static final String FASHION_ITEM = "Hàng thời trang";
    private static final String CONSUMER_ITEM = "Hàng tiêu dùng";
    private static final String ELECTRICAL_ITEM = "Hàng điện máy";
    private static final String APPLIANCES_ITEM = "Hàng gia dụng";

    public Item() {
    }

    public Item(int id, String name, float buyPrice, float sellPrice, String itemGroup) {
        this.id = id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.itemGroup = itemGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    public static String getFashionItem() {
        return FASHION_ITEM;
    }

    public static String getConsumerItem() {
        return CONSUMER_ITEM;
    }

    public static String getElectricalItem() {
        return ELECTRICAL_ITEM;
    }

    public static String getAppliancesItem() {
        return APPLIANCES_ITEM;
    }

    public void inputItemInfo(){
        this.setId(Item.AUTO_ID);

        boolean check = true;
        System.out.println("Nhập tên mặt hàng: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.println("Nhập giá mua: ");
        this.buyPrice = new Scanner(System.in).nextFloat();
        System.out.println("Nhập giá bán: ");
        this.sellPrice = new Scanner(System.in).nextFloat();
        System.out.println("Nhập loại mặt hàng: ");
        System.out.println("1.Hàng thời trang");
        System.out.println("2.Hàng tiêu dùng");
        System.out.println("3.Hàng điện máy");
        System.out.println("4.Hàng gia dụng");
        System.out.println("Nhập sự lựa chọn của bạn: ");
        do {
            int choice;
            try {
                choice = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.print("Không được nhập ký tự khác ngoài số! Vui lòng thử lại: ");
                check = false;
                continue;
            }
            if (choice <= 0 || choice > 4) {
                System.out.print("Nhập số trong khoảng từ 1 đến 4! Vui lòng thử lại: ");
                check = false;
            }
            switch (choice){
                case 1:
                    this.setItemGroup(Item.FASHION_ITEM);
                    check = true;
                    break;
                case 2:
                    this.setItemGroup(Item.CONSUMER_ITEM);
                    check = true;
                    break;
                case 3:
                    this.setItemGroup(Item.ELECTRICAL_ITEM);
                    check = true;
                    break;
                case 4:
                    this.setItemGroup(Item.APPLIANCES_ITEM);
                    check = true;
                    break;
                default:
                    System.out.println("Nhập không hợp lệ! Nhập lại: ");
                    check = false;
                    break;
            }
        }while (!check);
        Item.AUTO_ID++;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", itemGroup='" + itemGroup + '\'' +
                '}';
    }
}

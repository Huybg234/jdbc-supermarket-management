package entity;

import java.util.Scanner;

public class Product {

    private int id;
    private String name;
    private float importCost;
    private float cost;
    private String category;

    public static int AUTO_ID = 1000;
    private static final String FASHION_ITEM = "Hàng thời trang";
    private static final String CONSUMER_ITEM = "Hàng tiêu dùng";
    private static final String ELECTRICAL_ITEM = "Hàng điện máy";
    private static final String APPLIANCES_ITEM = "Hàng gia dụng";

    public Product() {
    }

    public Product(int id, String name, float importCost, float cost, String category) {
        this.id = id;
        this.name = name;
        this.importCost = importCost;
        this.cost = cost;
        this.category = category;
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

    public float getImportCost() {
        return importCost;
    }

    public void setImportCost(float importCost) {
        this.importCost = importCost;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        this.setId(Product.AUTO_ID);

        boolean check = true;
        System.out.println("Nhập tên mặt hàng: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.println("Nhập giá mua: ");
        do{
            try {
                this.importCost = new Scanner(System.in).nextFloat();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được có ký tự khác ngoài số!");
                check = false;
                continue;
            }
            if (this.importCost <=0 ){
                System.out.println("Giá mua phải lớn hơn 0! Nhập lại");
                check = false;
            }
        }while (!check);
        System.out.println("Nhập giá bán: ");
        do{
            try {
                this.cost = new Scanner(System.in).nextFloat();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được có ký tự khác ngoài số!");
                check = false;
                continue;
            }
            if (this.cost <=0 ){
                System.out.println("Giá bán phải lớn hơn 0! Nhập lại");
                check = false;
            }
        }while (!check);
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
                    this.setCategory(Product.FASHION_ITEM);
                    check = true;
                    break;
                case 2:
                    this.setCategory(Product.CONSUMER_ITEM);
                    check = true;
                    break;
                case 3:
                    this.setCategory(Product.ELECTRICAL_ITEM);
                    check = true;
                    break;
                case 4:
                    this.setCategory(Product.APPLIANCES_ITEM);
                    check = true;
                    break;
                default:
                    System.out.println("Nhập không hợp lệ! Nhập lại: ");
                    check = false;
                    break;
            }
        }while (!check);
        Product.AUTO_ID++;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buyPrice=" + importCost +
                ", sellPrice=" + cost +
                ", itemGroup='" + category + '\'' +
                '}';
    }
}

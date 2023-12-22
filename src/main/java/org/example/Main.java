package org.example;

import org.example.manager.CategoryManager;
import org.example.manager.ProductManager;
import org.example.model.Category;
import org.example.model.Product;

import java.util.Scanner;

public class Main {
    private static final ProductManager PRODUCT_MANAGER = new ProductManager();
    private static final CategoryManager CATEGORY_MANAGER = new CategoryManager();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            printCommands();
            switch (SCANNER.nextLine()) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addCategory();
                    break;
                case "2":
                    editCategoryById();
                    break;
                case "3":
                    deleteCategoryById();
                    break;
                case "4":
                    addProduct();
                    break;
                case "5":
                    editProductById();
                    break;
                case "6":
                    deleteProductById();
                    break;
                case "7":
                    System.out.println(PRODUCT_MANAGER.getAllProductsQty());
                    break;
                case "8":
                    System.out.println(PRODUCT_MANAGER.getMaxOfPrice());
                    break;
                case "9":
                    System.out.println(PRODUCT_MANAGER.getMixOfPrice());
                    break;
                case "10":
                    System.out.println(PRODUCT_MANAGER.getAvgOfPriceProduct());
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }

    }

    private static void deleteProductById() {
        System.out.println("Enter product ID");
        int id = Integer.parseInt(SCANNER.nextLine());
        PRODUCT_MANAGER.deleteById(id);
    }

    private static void editProductById() {
        System.out.println("Enter product ID");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Enter product NAME");
        String name = SCANNER.nextLine();
        System.out.println("Enter product DESCRIPTION");
        String description = SCANNER.nextLine();
        System.out.println("Enter product PRICE");
        double price = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Enter product QUANTITY");
        int qty = Integer.parseInt(SCANNER.nextLine());
        PRODUCT_MANAGER.update(new Product(id, name, description, price, qty));
    }

    private static void addProduct() {
        System.out.println("Enter product NAME");
        String name = SCANNER.nextLine();
        System.out.println("Enter product DESCRIPTION");
        String description = SCANNER.nextLine();
        System.out.println("Enter product PRICE");
        double price = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Enter product QUANTITY");
        int qty = Integer.parseInt(SCANNER.nextLine());
        PRODUCT_MANAGER.add(new Product(name, description, price, qty));
    }

    private static void deleteCategoryById() {
        System.out.println("Enter category ID");
        int id = Integer.parseInt(SCANNER.nextLine());
        CATEGORY_MANAGER.deleteById(id);
    }

    private static void editCategoryById() {
        System.out.println("Enter category ID");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Enter category new NAME");
        String name = SCANNER.nextLine();
        CATEGORY_MANAGER.update(new Category(id, name));
    }

    private static void addCategory() {
        System.out.println("Enter category NAME");
        String name = SCANNER.nextLine();
        CATEGORY_MANAGER.add(new Category(name));
    }

    private static void printCommands() {
        System.out.println("Enter 0 for EXIT");
        System.out.println("Enter 1 for ADD CATEGORY");
        System.out.println("Enter 2 for EDIT CATEGORY BY ID");
        System.out.println("Enter 3 for DELETE CATEGORY BY ID");
        System.out.println("Enter 4 for ADD PRODUCT");
        System.out.println("Enter 5 for EDIT PRODUCT BY ID");
        System.out.println("Enter 6 for DELETE PRODUCT BY ID");
        System.out.println("Enter 7 for PRINT SUM OF PRODUCTS");
        System.out.println("Enter 8 for PRINT MAX OF PRICE PRODUCT");
        System.out.println("Enter 9 for PRINT MIN OF PRICE PRODUCT");
        System.out.println("Enter 10 for PRINT AVG OF PRICE PRODUCT");

    }
}
package com.guildedrose;

import com.guildedrose.models.Product;
import com.guildedrose.services.ProductData;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Guilded Rose!");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Load products from JSON file
        List<Product> products = ProductData.loadProducts("data/products.json");

        if (products == null) {
            System.out.println("Could not load products. Exiting.");
            return;
        }

        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // View Products
                    System.out.println("\nAvailable Products:");
                    for (Product product : products) {
                        product.displayInfo();
                    }
                    break;
                case 2:
                    // Add Product to Cart (To be implemented)
                    System.out.println("Feature not implemented yet.");
                    break;
                case 3:
                    // View Cart (To be implemented)
                    System.out.println("Feature not implemented yet.");
                    break;
                case 4:
                    // Checkout (To be implemented)
                    System.out.println("Feature not implemented yet.");
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

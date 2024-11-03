package com.guildedrose;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            // Display menu
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Call method to view products
                    System.out.println("You pressed 1.");
                    break;
                case 2:
                    // Call method to add product to cart
                    System.out.println("You pressed 2.");
                    break;
                case 3:
                    // Call method to view cart
                    System.out.println("You pressed 3.");
                    break;
                case 4:
                    // Call method to checkout
                    System.out.println("You pressed 4.");
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
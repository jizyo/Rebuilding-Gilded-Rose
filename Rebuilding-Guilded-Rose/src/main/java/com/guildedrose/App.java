package com.guildedrose;

import com.guildedrose.models.Cart;
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

        // Initialize the cart
        Cart cart = new Cart();

        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice = 0;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    // View Products
                    displayProducts(products);
                    break;
                case 2:
                    // Add Product to Cart
                    addProductToCart(products, cart, scanner);
                    break;
                case 3:
                    // View Cart
                    cart.displayCart();
                    break;
                case 4:
                    // Checkout (To be implemented)
                    System.out.println("Checkout feature not implemented yet.");
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for visiting Guilded Rose. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }

        scanner.close();
    }

    // Method to display available products
    private static void displayProducts(List<Product> products) {
        System.out.println("\nAvailable Products:");
        int index = 1;
        for (Product product : products) {
            System.out.println(index + ". " + product.getName() + " - " + product.getPrice() + " " + product.getCurrency());
            index++;
        }
    }

    // Method to add a product to the cart
    private static void addProductToCart(List<Product> products, Cart cart, Scanner scanner) {
        displayProducts(products);
        System.out.print("\nEnter the number of the product you want to add to your cart: ");

        String productChoice = scanner.nextLine();
        int productIndex = 0;

        try {
            productIndex = Integer.parseInt(productChoice);
            if (productIndex < 1 || productIndex > products.size()) {
                System.out.println("Invalid product number. Please try again.");
            } else {
                Product selectedProduct = products.get(productIndex - 1);
                cart.addProduct(selectedProduct);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid product number.");
        }
    }
}

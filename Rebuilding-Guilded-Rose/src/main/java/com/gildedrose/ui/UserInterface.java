package com.gildedrose.ui;

import com.gildedrose.models.Product;
import com.gildedrose.models.Cart;
import com.gildedrose.services.CurrencyConverter;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    // Display the main menu options
    public void displayMainMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. View Products");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
    }

    // Get the user's menu choice
    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        String input = scanner.nextLine();
        int choice;
        try {
            choice = Integer.parseInt(input);
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
        return choice;
    }

    // Display the list of available products
    public void displayProducts(List<Product> products) {
        System.out.println("\nAvailable Products:");
        int index = 1;
        for (Product product : products) {
            System.out.println(index + ". " + product.getName() + " - " + product.getPrice() + " " + product.getCurrency());
            index++;
        }
    }

    // Prompt the user to select a product
    public int getProductSelection(int maxIndex) {
        System.out.print("\nEnter the number of the product you want to add to your cart: ");
        String input = scanner.nextLine();
        int productIndex;
        try {
            productIndex = Integer.parseInt(input);
            if (productIndex < 1 || productIndex > maxIndex) {
                System.out.println("Invalid product number. Please try again.");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid product number.");
            return -1;
        }
        return productIndex - 1; // Adjust for zero-based index
    }

    // Display the contents of the cart
    public void displayCart(Cart cart) {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\nProducts in your cart:");
        int index = 1;
        for (Product product : cart.getProducts()) {
            System.out.println(index + ". " + product.getName() + " - " + product.getPrice() + " " + product.getCurrency());
            index++;
        }
        System.out.println("Total Price: " + String.format("%.2f", cart.calculateTotal()) + " " + cart.getProducts().get(0).getCurrency());
    }

    // Display the checkout information
    public void displayCheckout(Cart cart, double finalTotal) {
        System.out.println("\nCheckout:");
        displayCart(cart);
        System.out.println("Total after discounts: " + String.format("%.2f", finalTotal) + " USD");
    }

    // Ask the user if they want to view the total in a different currency
    public boolean askForCurrencyConversion() {
        System.out.print("Do you want to view the total in a different currency? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    // Get the target currency code from the user
    public String getTargetCurrency(CurrencyConverter currencyConverter) {
        currencyConverter.displaySupportedCurrencies();
        System.out.print("Enter the currency code: ");
        return scanner.nextLine().trim().toUpperCase();
    }

    // Ask the user to confirm the purchase
    public boolean confirmPurchase() {
        System.out.print("Do you wish to proceed with the purchase? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        return confirm.equals("yes");
    }

    // Display a generic message to the user
    public void displayMessage(String message) {
        System.out.println(message);
    }

    // Close the scanner resource
    public void close() {
        scanner.close();
    }
}

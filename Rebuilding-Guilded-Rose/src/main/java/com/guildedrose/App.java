package com.guildedrose;

import com.guildedrose.models.Cart;
import com.guildedrose.models.Product;
import com.guildedrose.services.CurrencyConverter;
import com.guildedrose.services.DiscountManager;
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

        // Initialize dependencies
        DiscountManager discountManager = new DiscountManager();
        CurrencyConverter currencyConverter = new CurrencyConverter();

        // Initialize the cart
        Cart cart = new Cart(discountManager, currencyConverter);

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
                    // Checkout
                    checkout(cart, scanner);
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

    // Method to handle checkout
    private static void checkout(Cart cart, Scanner scanner) {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. Add some products before checking out.");
            return;
        }

        System.out.println("\nCheckout:");
        cart.displayCart();

        // Apply discounts and show final total
        double finalTotal = cart.calculateFinalTotal();
        System.out.println("Total after discounts: " + String.format("%.2f", finalTotal) + " USD");

        // Currency conversion
        System.out.print("Do you want to view the total in a different currency? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            cart.getCurrencyConverter().displaySupportedCurrencies();
            System.out.print("Enter the currency code: ");
            String targetCurrency = scanner.nextLine().trim().toUpperCase();
            double convertedTotal = cart.calculateTotalInCurrency(targetCurrency);
            System.out.println("Total in " + targetCurrency + ": " + String.format("%.2f", convertedTotal) + " " + targetCurrency);
        }

        // Confirm purchase
        System.out.print("Do you wish to proceed with the purchase? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("yes")) {
            System.out.println("Purchase confirmed! Thank you for shopping at Guilded Rose.");
            cart.getProducts().clear(); // Empty the cart after purchase
        } else {
            System.out.println("Purchase cancelled. You can continue shopping.");
        }
    }

    // Accessor for CurrencyConverter in Cart
    private static CurrencyConverter getCurrencyConverterFromCart(Cart cart) {
        return cart.getCurrencyConverter();
    }

}

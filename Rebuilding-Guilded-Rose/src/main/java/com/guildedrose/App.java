package com.guildedrose;

import com.guildedrose.models.Cart;
import com.guildedrose.models.Product;
import com.guildedrose.services.CurrencyConverter;
import com.guildedrose.services.DiscountManager;
import com.guildedrose.services.ProductData;
import com.guildedrose.ui.MenuOption;
import com.guildedrose.ui.UserInterface;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Guilded Rose!");

        // Initialize the UserInterface
        UserInterface ui = new UserInterface();

        // Load products from JSON file
        List<Product> products = ProductData.loadProducts("data/products.json");

        if (products == null) {
            ui.displayMessage("Could not load products. Exiting.");
            return;
        }

        // Initialize dependencies
        DiscountManager discountManager = new DiscountManager();
        CurrencyConverter currencyConverter = new CurrencyConverter();

        // Initialize the cart with dependencies
        Cart cart = new Cart(discountManager, currencyConverter);

        boolean running = true;

        while (running) {
            ui.displayMainMenu();
            int choice = ui.getUserChoice();

            if (choice == -1) {
                continue;
            }

            // Convert user choice to MenuOption
            MenuOption selectedOption = MenuOption.fromInt(choice);

            if (selectedOption == null) {
                ui.displayMessage("Invalid choice. Please enter a number from 1 to 5.");
                continue;
            }

            switch (selectedOption) {
                case VIEW_PRODUCTS:
                    ui.displayProducts(products);
                    break;
                case ADD_TO_CART:
                    addProductToCart(products, cart, ui);
                    break;
                case VIEW_CART:
                    ui.displayCart(cart);
                    break;
                case CHECKOUT:
                    checkout(cart, ui);
                    break;
                case EXIT:
                    running = false;
                    ui.displayMessage("Thank you for visiting Guilded Rose. Goodbye!");
                    break;
                default:
                    ui.displayMessage("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
        // Close resources
        ui.close();
    }

    // Method to add a product to the cart
    private static void addProductToCart(List<Product> products, Cart cart, UserInterface ui) {
        ui.displayProducts(products);
        int productIndex = ui.getProductSelection(products.size());
        if (productIndex != -1) {
            Product selectedProduct = products.get(productIndex);
            cart.addProduct(selectedProduct);
            ui.displayMessage("'" + selectedProduct.getName() + "' has been added to your cart.");
        }
    }

    // Method to handle the checkout process
    private static void checkout(Cart cart, UserInterface ui) {
        if (cart.getProducts().isEmpty()) {
            ui.displayMessage("Your cart is empty. Add some products before checking out.");
            return;
        }

        double finalTotal = cart.calculateFinalTotal();
        ui.displayCheckout(cart, finalTotal);

        // Handle currency conversion
        if (ui.askForCurrencyConversion()) {
            String targetCurrency = ui.getTargetCurrency(cart.getCurrencyConverter());
            double convertedTotal = cart.calculateTotalInCurrency(targetCurrency);
            ui.displayMessage("Total in " + targetCurrency + ": " + String.format("%.2f", convertedTotal) + " " + targetCurrency);
        }

        // Confirm the purchase
        if (ui.confirmPurchase()) {
            ui.displayMessage("Purchase confirmed! Thank you for shopping at Guilded Rose.");
            cart.getProducts().clear(); // Empty the cart after purchase
        } else {
            ui.displayMessage("Purchase cancelled. You can continue shopping.");
        }
    }
}

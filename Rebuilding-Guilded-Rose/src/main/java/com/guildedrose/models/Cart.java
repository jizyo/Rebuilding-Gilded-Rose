package com.guildedrose.models;

import com.guildedrose.services.CurrencyConverter;
import com.guildedrose.services.DiscountManager;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> products;
    private final DiscountManager discountManager;
    private final CurrencyConverter currencyConverter;

    // Constructor with dependencies injected
    public Cart(DiscountManager discountManager, CurrencyConverter currencyConverter) {
        this.products = new ArrayList<>();
        this.discountManager = discountManager;
        this.currencyConverter = currencyConverter;
    }

    // Method to add a product to the cart
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("'" + product.getName() + "' has been added to your cart.");
    }

    // Method to display cart contents
    public void displayCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\nProducts in your cart:");
        int index = 1;
        for (Product product : products) {
            System.out.println(index + ". " + product.getName() + " - " + product.getPrice() + " " + product.getCurrency());
            index++;
        }
        System.out.println("Total Price: " + calculateTotal() + " " + products.get(0).getCurrency());
    }

    // Method to calculate total price
    public double calculateTotal() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    // Method to apply discounts and calculate the final total
    public double calculateFinalTotal() {
        double total = calculateTotal();
        total = discountManager.applyDiscounts(products, total);
        return total;
    }

    // Method to calculate total in a different currency
    public double calculateTotalInCurrency(String targetCurrency) {
        double total = calculateFinalTotal();
        String sourceCurrency = products.get(0).getCurrency();
        double convertedTotal = currencyConverter.convert(total, sourceCurrency, targetCurrency);
        return convertedTotal;
    }

    // Getter for products
    public List<Product> getProducts() {
        return products;
    }

    public CurrencyConverter getCurrencyConverter() {
        return currencyConverter;
    }
}

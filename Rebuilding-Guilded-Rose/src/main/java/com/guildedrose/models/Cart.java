package com.guildedrose.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> products;

    public Cart() {
        products = new ArrayList<>();
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

    // Getter for products
    public List<Product> getProducts() {
        return products;
    }
}

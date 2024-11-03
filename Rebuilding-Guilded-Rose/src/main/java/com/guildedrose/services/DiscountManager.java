package com.guildedrose.services;

import com.guildedrose.models.Product;

import java.util.List;

public class DiscountManager {

    // Apply discounts and return the new total
    public double applyDiscounts(List<Product> products, double total) {
        total = applyBulkDiscount(products, total);
        // Add more discount methods if needed
        return total;
    }

    // Example: Apply a 10% discount if more than 5 items are in the cart
    private double applyBulkDiscount(List<Product> products, double total) {
        if (products.size() >= 5) {
            System.out.println("Bulk discount applied: 10% off");
            total = total * 0.9;
        }
        return total;
    }
}

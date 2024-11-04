package com.gildedrose.models;

public class Product {
    private String name;
    private int sellIn;
    private int quality;
    private double price;
    private String currency;

    public Product(String name, int sellIn, int quality, double price, String currency) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.price = price;
        this.currency = currency;
    }

    // Getters
    public String getName() { return name; }
    public int getSellIn() { return sellIn; }
    public int getQuality() { return quality; }
    public double getPrice() { return price; }
    public String getCurrency() { return currency; }

    // Setters (if needed)
    public void setName(String name) { this.name = name; }
    public void setSellIn(int sellIn) { this.sellIn = sellIn; }
    public void setQuality(int quality) { this.quality = quality; }
    public void setPrice(double price) { this.price = price; }
    public void setCurrency(String currency) { this.currency = currency; }

    // Method to display product information
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Sell In: " + sellIn);
        System.out.println("Quality: " + quality);
        System.out.println("Price: " + price + " " + currency);
        System.out.println("------------------------------");
    }

    // Method to update quality and sellIn
    public void updateQuality() {
        switch (name) {
            case "Aged Brie":
                updateAgedBrie();
                break;
            case "Sulfuras, Hand of Ragnaros":
                // Sulfuras does not change
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                updateBackstagePass();
                break;
            default:
                if (name.startsWith("Conjured")) {
                    updateConjuredItem();
                } else {
                    updateRegularItem();
                }
                break;
        }

        // Ensure quality is within bounds
        if (quality < 0) {
            quality = 0;
        } else if (quality > 50 && !name.equals("Sulfuras, Hand of Ragnaros")) {
            quality = 50;
        }
    }

    private void updateRegularItem() {
        sellIn--;

        if (quality > 0) {
            quality--;
        }

        if (sellIn < 0 && quality > 0) {
            quality--;
        }
    }

    private void updateAgedBrie() {
        sellIn--;

        if (quality < 50) {
            quality++;
        }

        if (sellIn < 0 && quality < 50) {
            quality++;
        }
    }

    private void updateBackstagePass() {
        sellIn--;

        if (sellIn < 0) {
            quality = 0;
        } else if (sellIn < 5) {
            quality += 3;
        } else if (sellIn < 10) {
            quality += 2;
        } else {
            quality++;
        }
    }

    private void updateConjuredItem() {
        sellIn--;

        if (quality > 0) {
            quality -= 2;
        }

        if (sellIn < 0 && quality > 0) {
            quality -= 2;
        }
    }
}

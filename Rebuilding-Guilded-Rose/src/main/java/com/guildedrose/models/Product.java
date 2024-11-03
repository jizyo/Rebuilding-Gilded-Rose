package com.guildedrose.models;

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
}

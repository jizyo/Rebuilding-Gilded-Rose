package com.guildedrose.services;

import com.guildedrose.models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ProductData {
    public static List<Product> loadProducts(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type productListType = new TypeToken<List<Product>>() {}.getType();
            List<Product> products = gson.fromJson(reader, productListType);
            return products;
        } catch (IOException e) {
            System.out.println("Error reading products file: " + e.getMessage());
            return null;
        }
    }
}

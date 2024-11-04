package com.gildedrose.services;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private final Map<String, Double> exchangeRates;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        // Static exchange rates for simplicity
        exchangeRates.put("USD", 1.0);       // Base currency
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            System.out.println("Currency conversion error: Unsupported currency.");
            return amount;
        }
        double amountInBase = amount / exchangeRates.get(fromCurrency);
        return amountInBase * exchangeRates.get(toCurrency);
    }

    public void displaySupportedCurrencies() {
        System.out.println("Supported currencies:");
        for (String currency : exchangeRates.keySet()) {
            System.out.println("- " + currency);
        }
    }
}

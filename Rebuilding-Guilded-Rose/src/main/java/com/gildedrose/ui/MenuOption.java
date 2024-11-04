package com.gildedrose.ui;

public enum MenuOption {
    VIEW_PRODUCTS(1),
    ADD_TO_CART(2),
    VIEW_CART(3),
    CHECKOUT(4),
    EXIT(5);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public static MenuOption fromInt(int value) {
        for (MenuOption option : MenuOption.values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }
}

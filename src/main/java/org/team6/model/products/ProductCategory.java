package org.team6.model.products;


public enum ProductCategory {
    FRIDGE("Fridge"),
    FREEZER("Freezer"),
    FRIDGE_FREEZER("Fridge Freezer"),
    OVEN("Oven"),
    WASHING_MACHINE("Washing Machine"),
    FORCED_AIR_OVEN("Forced Air Oven"),
    DISHWASHER("Dishwasher");

    private final String categoryLabel;

    ProductCategory(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    @Override
    public String toString() {
        return categoryLabel;
    }
}

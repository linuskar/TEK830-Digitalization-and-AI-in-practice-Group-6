package org.team6.model;


public enum ProductCategory {
    FRIDGE,
    OVEN;

    @Override
    public String toString() {
        switch (this) {
            case FRIDGE:
                return "Fridge";
            case OVEN:
                return "Oven";
            default:
                return super.toString();
        }
    }

}

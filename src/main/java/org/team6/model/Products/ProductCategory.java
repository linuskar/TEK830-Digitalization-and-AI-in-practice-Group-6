package org.team6.model.Products;


public enum ProductCategory {
    FRIDGE,
    FREEZER,
    FRIDGE_FREEZER,
    OVEN,
    WASHING_MACHINE,
    FORCED_AIR_OVEN,
    DISHWASHER;

    @Override
    public String toString() {
        switch (this) {
            case FRIDGE:
                return "Fridge";
            case FREEZER:
                return "Freezer";
            case FRIDGE_FREEZER:
                return "Fridge Freezer";
            case OVEN:
                return "Oven";
            case FORCED_AIR_OVEN:
                return "Forced Air Oven";
            case WASHING_MACHINE:
                return "Washing Machine";
            case DISHWASHER:
                return "Dishwasher";
            default:
                return super.toString();
        }
    }

}

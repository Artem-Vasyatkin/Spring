package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercent;

    public DiscountedProduct(String nameProduct, int basePrice, int discountPercent) {
        super(nameProduct);
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public int getPrice() {
        return basePrice - (basePrice * discountPercent / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "DiscountedProduct{" +
                "basePrice=" + basePrice +
                ", discountPercent=" + discountPercent +
                '}';
    }
}

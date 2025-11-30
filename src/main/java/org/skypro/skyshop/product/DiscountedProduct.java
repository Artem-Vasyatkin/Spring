package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercent;

    public DiscountedProduct(String nameProduct, int basePrice, int discountPercent) {
        super(nameProduct);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена не может быть ноль!");
        }
        this.basePrice = basePrice;
        if (discountPercent <= 0 || discountPercent >= 100) {
            throw new IllegalArgumentException("Вам не правильно рассчитали скидку! Обратитесь к оператору!");
        }
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

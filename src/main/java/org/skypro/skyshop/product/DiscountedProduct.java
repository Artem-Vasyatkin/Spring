package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice; // цена без скидки
    private final int discountPercent; // скидка в %

    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name);
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
    public int getPrice() { // цена со скидкой
        return basePrice - (basePrice * discountPercent / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return "Товар со скидкой: " +
                "Начальная цена: " + basePrice +
                ", скидка на товар: " + discountPercent;
    }
}

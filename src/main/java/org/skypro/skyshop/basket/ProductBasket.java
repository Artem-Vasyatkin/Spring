package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket{
    private final Product[] products;
    private int productCount;

    public ProductBasket() {
        this.products = new Product[5];
        this.productCount = 0;
    }


    public void addProduct(Product product) {
        if (productCount < products.length) {
            products[productCount] = product;
            productCount++;
        } else {
            System.out.println("Невозможно добавить продукт");
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < productCount; i++) {
            totalPrice += products[i].getPrice();
        }
        return totalPrice;
    }

    public void printBasket() {
        if (productCount == 0) {
            System.out.println("В корзине пусто");
            return;
        }

        int specialCount = 0;

        for (int i = 0; i < productCount; i++) {
            Product product = products[i];
            System.out.println(product.toString());
            if (product.isSpecial()) {
                specialCount++;
            }
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean productByName(String productName) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getNameProduct().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
        productCount = 0;
    }

}

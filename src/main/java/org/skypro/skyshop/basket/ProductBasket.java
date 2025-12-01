package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class ProductBasket {
    private final List<Product> products;


    public ProductBasket() {
        this.products = new ArrayList<>();

    }


    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> removeProduct(String nameProduct) {
        List<Product> removeProduct = new ArrayList<>();
        List<Product> productsToRemove = new ArrayList<>();

        for (Product product : products) {
            if (product.getNameProduct().equals(nameProduct)) {
                productsToRemove.add(product);
            }
        }

        for (Product productToRemove : productsToRemove) {
            if (products.remove(productToRemove)) {
                removeProduct.add(productToRemove);
            }
        }
        return removeProduct;
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : products) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пустая");
            return;
        }

        int specialCount = 0;

        for (Product product : products) {
            System.out.println(product.toString());
            if (product.isSpecial()) {
                specialCount++;
            }
        }
        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров в корзине: " + specialCount);
    }

    public boolean containsProductBuName(String nameProduct) {
        for (Product product : products) {
            if (product.getNameProduct().equals(nameProduct)) {
                return true;
            }
        }
        return false;
    }


    public void clearBasket() {
        products.clear();
    }

    public int getProductCount() {
       return products.size();
    }
}


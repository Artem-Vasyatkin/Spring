package org.skypro.skyshop.product;

import org.skypro.skyshop.model.Searchable;


public abstract class Product implements Searchable {
    private final String nameProduct;

    public Product(String nameProduct) {
        if (nameProduct == null || nameProduct.trim().isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или null!");
        }
        this.nameProduct = nameProduct.trim();
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public abstract String toString();

    @Override
    public String getSearchTerm() {
        return nameProduct;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }
}

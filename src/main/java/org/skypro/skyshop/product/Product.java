package org.skypro.skyshop.product;

import org.skypro.skyshop.model.Searchable;


public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.trim().isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым или null!");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice(); // цена

    public abstract boolean isSpecial(); // специальны товар

    @Override
    public abstract String toString(); // что выводить

    @Override
    public String getSearchTerm() {
        return name; // ищет по названию
    }

    @Override
    public String getContentType() {
        return "Продукт";
    }
}

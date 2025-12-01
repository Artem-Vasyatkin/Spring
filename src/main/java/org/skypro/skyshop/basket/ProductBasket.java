package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products = new HashMap<>();


    // Добавить товар
    public void addProduct(Product product) {
        String name = product.getName();

        if (!products.containsKey(name)) {
            products.put(name, new ArrayList<>());
        }

        products.get(name).add(product);
    }

    // Удалить товар
    public List<Product> removeProduct(String name) {
        List<Product> remove = products.remove(name);

        if (remove == null) {
            return new ArrayList<>();
        }
        return remove;
    }

    // посчитать общую стоимость
    public int getTotalCost() {
        int totalCost = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                totalCost += product.getPrice();
            }
        }
        return totalCost;
    }
    // содержимое корзины
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пустая");
            return;
        }

        int specialCount = 0;

        List<String> sortedNames = new ArrayList<>(products.keySet());
        Collections.sort(sortedNames);

        for (String name : sortedNames) {
            List<Product> productList = products.get(name);
            for (Product product : productList) {
                System.out.println(product.toString());
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальные товары в корзине: " + specialCount);

    }
    // есть ли товар с таким именем
    public boolean containsProductByName(String name) {
        return products.containsKey(name);
    }

    // Очистить корзину
    public void clearBasket() {
        products.clear();
    }

    // Получить общее количество товаров
    public int getProductCount() {
        int totalCount = 0;

        for (List<Product> productList : products.values()) {
            totalCount += productList.size();
        }
        return totalCount;
    }

    // Получить все товары
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();

        for (List<Product> productList : products.values()) {
            allProducts.addAll(productList);
        }
        return allProducts;
    }
}


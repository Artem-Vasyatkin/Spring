package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> products;


    public ProductBasket() {
        this.products = new HashMap();

    }


    public void addProduct(Product product) {
        String productName = product.getNameProduct();

        products.putIfAbsent(productName, new ArrayList<>());

        products.get(productName).add(product);
    }

    public List<Product> removeProduct(String nameProduct) {
        List<Product> removeProduct = products.remove(nameProduct);

        return removeProduct != null ? removeProduct : new ArrayList<>();

    }

    public int getTotalCost() {
        int totalCost = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                totalCost += product.getPrice();
            }
        }
        return totalCost;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("Корзина пустая");
            return;
        }

        int specialCount = 0;

        List<String> sortedNames = new ArrayList<>(products.keySet());
        Collections.sort(sortedNames);

        for (String productName : sortedNames) {
            List<Product> productList = products.get(productName);
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

    public boolean containsProductBuName(String nameProduct) {
        return products.containsKey(nameProduct);
    }


    public void clearBasket() {
        products.clear();
    }

    public int getProductCount() {
        int totalCount = 0;

        for (List<Product> productList : products.values()) {
            totalCount += productList.size();
        }
        return totalCount;
    }


    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();

        for (List<Product> productList : products.values()) {
            allProducts.addAll(productList);
        }
        return allProducts;
    }
}


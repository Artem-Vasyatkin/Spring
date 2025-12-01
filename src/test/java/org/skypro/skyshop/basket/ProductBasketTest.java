package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ProductBasketTest {

    private ProductBasket basket;
    private Product laptop;
    private Product mouse;

    @BeforeEach
    void setUp() {
        basket = new ProductBasket();

        laptop = new SimpleProduct("Ноутбук", 50000);
        mouse = new DiscountedProduct("Мышь", 2000, 10);
    }

    @Test
    void testAddProduct() {
        // добавляем товар в корзину
        basket.addProduct(laptop);

        // Проверяем, что товар добавился
        assertEquals(1, basket.getProductCount());
        assertTrue(basket.containsProductByName("Ноутбук"));
    }

    @Test
    void testAddMultipleProductsSameName() {
        // добавляем несколько товаров с одинаковым именем
        basket.addProduct(laptop);
        basket.addProduct(new SimpleProduct("Ноутбук", 45000));

        // Должно быть 2 товара
        assertEquals(2, basket.getProductCount());
    }

    @Test
    void testAddDifferentProducts() {
        // Тест: добавляем разные товары
        basket.addProduct(laptop);
        basket.addProduct(mouse);

        assertEquals(2, basket.getProductCount());
        assertTrue(basket.containsProductByName("Ноутбук"));
        assertTrue(basket.containsProductByName("Мышь"));
    }

    @Test
    void testRemoveProduct() {
        // удаляем товар
        basket.addProduct(mouse);

        // Удаляем мышь
        List<Product> removed = basket.removeProduct("Мышь");

        // Проверяем, что удалили
        assertEquals(1, removed.size());
        assertEquals(0, basket.getProductCount());
        assertFalse(basket.containsProductByName("Мышь"));
    }

    @Test
    void testRemoveProductWhenEmpty() {
        // пытаемся удалить из пустой корзины
        List<Product> removed = basket.removeProduct("Ноутбук");

        // Должен вернуться пустой список
        assertTrue(removed.isEmpty());
        assertEquals(0, basket.getProductCount());
    }

    @Test
    void testRemoveMultipleProductsSameName() {
        // удаляем несколько товаров с одинаковым именем
        basket.addProduct(new SimpleProduct("Ноутбук", 50000));
        basket.addProduct(new SimpleProduct("Ноутбук", 45000));

        // Удаляем все ноутбуки
        List<Product> removed = basket.removeProduct("Ноутбук");

        assertEquals(2, removed.size());
        assertEquals(0, basket.getProductCount());
    }

    @Test
    void testGetTotalCost() {
        // считаем общую стоимость
        basket.addProduct(laptop); // 50000
        basket.addProduct(mouse);  // 2000 - 10% = 1800

        // 50000 + 1800 = 51800
        assertEquals(51800, basket.getTotalCost());
    }

    @Test
    void testGetTotalCostEmptyBasket() {
        // стоимость пустой корзины
        assertEquals(0, basket.getTotalCost());
    }

    @Test
    void testContainsProductByName() {
        // проверяем наличие товара
        basket.addProduct(laptop);

        assertTrue(basket.containsProductByName("Ноутбук"));
        assertFalse(basket.containsProductByName("Мышь"));
    }

    @Test
    void testClearBasket() {
        // очищаем корзину
        basket.addProduct(laptop);
        basket.addProduct(mouse);

        basket.clearBasket();

        assertEquals(0, basket.getProductCount());
        assertFalse(basket.containsProductByName("Ноутбук"));
        assertFalse(basket.containsProductByName("Мышь"));
    }

    @Test
    void testGetProductCount() {
        // считаем количество товаров
        assertEquals(0, basket.getProductCount());

        basket.addProduct(laptop);
        assertEquals(1, basket.getProductCount());

        basket.addProduct(mouse);
        assertEquals(2, basket.getProductCount());
    }

    @Test
    void testPrintBasketEmpty() {
        // печать пустой корзины
        basket.printBasket(); // Должно вывести "Корзина пуста"
    }

    @Test
    void testGetAllProducts() {
        // получаем все товары
        basket.addProduct(laptop);
        basket.addProduct(mouse);

        List<Product> allProducts = basket.getAllProducts();

        assertEquals(2, allProducts.size());
        assertTrue(allProducts.contains(laptop));
        assertTrue(allProducts.contains(mouse));
    }

    @Test
    void testDifferentProductTypes() {
        // добавляем разные типы товаров
        Product fixPrice = new FixPriceProduct("Клавиатура");
        Product discounted = new DiscountedProduct("Наушники", 5000, 20);

        basket.addProduct(laptop);
        basket.addProduct(fixPrice);
        basket.addProduct(discounted);

        assertEquals(3, basket.getProductCount());
        assertEquals(50000 + 1000 + 4000, basket.getTotalCost()); // 55000
    }
}
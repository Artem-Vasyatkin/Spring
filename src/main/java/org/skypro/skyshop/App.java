package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.model.Article;
import org.skypro.skyshop.model.Searchable;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.system.BestResultNotFound;
import org.skypro.skyshop.system.SearchEngine;

import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) {

        System.out.println("=== ДЕМОНСТРАЦИЯ ИНТЕРНЕТ-МАГАЗИНА ===\n");

        System.out.println("ЧАСТЬ 1: РАБОТА С КОРЗИНОЙ\n");
        testBasket();

        System.out.println("\n\nЧАСТЬ 2: РАБОТА С ПОИСКОМ\n");
        testSearch();
    }

    private static void testBasket() {
        // Создаем корзину
        ProductBasket basket = new ProductBasket();

        // Создаем товары
        Product laptop1 = new SimpleProduct("Ноутбук", 50000);
        Product mouse1 = new DiscountedProduct("Мышь", 2000, 20);
        Product keyboard = new FixPriceProduct("Клавиатура");
        Product laptop2 = new SimpleProduct("Ноутбук", 45000);
        Product mouse2 = new SimpleProduct("Мышь", 1500);

        // Добавляем в корзину
        System.out.println("1. Добавляем товары в корзину:");
        basket.addProduct(laptop1);
        basket.addProduct(mouse1);
        basket.addProduct(keyboard);
        basket.addProduct(laptop2);
        basket.addProduct(mouse2);

        System.out.println("   В корзине: " + basket.getProductCount() + " товаров");

        // Выводим содержимое
        System.out.println("\n2. Содержимое корзины:");
        basket.printBasket();

        // Удаляем мыши
        System.out.println("\n3. Удаляем все мыши:");
        List<Product> removed = basket.removeProduct("Мышь");
        System.out.println("   Удалено: " + removed.size() + " мышь(ей)");

        // Проверяем наличие
        System.out.println("\n4. Проверяем наличие товаров:");
        System.out.println("   Есть ли ноутбук? " + basket.containsProductByName("Ноутбук"));
        System.out.println("   Есть ли мышь? " + basket.containsProductByName("Мышь"));

        // Итоговое содержимое
        System.out.println("\n5. Итоговое содержимое:");
        basket.printBasket();
    }

    private static void testSearch() {
        // Создаем поисковый движок
        SearchEngine engine = new SearchEngine();

        // Добавляем товары
        engine.add(new SimpleProduct("Игровой ноутбук", 70000));
        engine.add(new DiscountedProduct("Игровая мышь", 3000, 15));
        engine.add(new FixPriceProduct("Игровая клавиатура"));

        // Добавляем статьи
        engine.add(new Article("Обзор игровых ноутбуков",
                "Лучшие ноутбуки для игр в 2024 году."));
        engine.add(new Article("Как выбрать мышь",
                "Советы по выбору компьютерной мыши."));

        // Ищем "игровой"
        System.out.println("1. Ищем 'игровой':");
        Map<String, Searchable> results = engine.search("игровой");

        System.out.println("   Найдено: " + results.size() + " результат(ов)");
        for (Map.Entry<String, Searchable> entry : results.entrySet()) {
            System.out.println("   - " + entry.getValue().getStringRepresentation());
        }

        // Ищем "мышь"
        System.out.println("\n2. Ищем 'мышь':");
        Map<String, Searchable> mouseResults = engine.search("мышь");
        System.out.println("   Найдено: " + mouseResults.size() + " результат(ов)");

        // Ищем лучшее совпадение
        System.out.println("\n3. Ищем лучшее совпадение для 'игровой':");
        try {
            Searchable best = engine.findBestMatch("игровой");
            System.out.println("   Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }
    }
}




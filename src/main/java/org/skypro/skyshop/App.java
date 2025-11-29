package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.model.Article;
import org.skypro.skyshop.model.Searchable;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.system.SearchEngine;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        // Создание продуктов для тестирования
        SimpleProduct laptop = new SimpleProduct("Ноутбук", 50000);
        DiscountedProduct mouse = new DiscountedProduct("Компьютерная мышь", 2000, 25);
        FixPriceProduct keyboard = new FixPriceProduct("Клавиатура");
        SimpleProduct monitor = new SimpleProduct("Монитор", 20000);
        DiscountedProduct headphones = new DiscountedProduct("Беспроводные наушники", 8000, 50);

        // Создание статей
        Article article1 = new Article("Обзор ноутбуков 2024",
                "В этом обзоре мы рассмотрим лучшие ноутбуки 2024 года для работы и игр.");
        Article article2 = new Article("Как выбрать компьютерную мышь",
                "Правильный выбор мыши важен для комфортной работы за компьютером.");
        Article article3 = new Article("Топ беспроводных наушников",
                "Рассматриваем лучшие модели беспроводных наушников по соотношению цена/качество.");
        Article article4 = new Article("Уход за компьютерной техникой",
                "Советы по уходу за техникой для продления срока службы устройств.");
        Article article5 = new Article("Новые технологии в мониторах",
                "OLED, QLED и другие современные технологии в производстве мониторов.");

        // Создание поискового движка с емкостью 15 элементов
        SearchEngine searchEngine = new SearchEngine(15);

        // Добавление всех товаров в поисковый движок
        searchEngine.add(laptop);
        searchEngine.add(mouse);
        searchEngine.add(keyboard);
        searchEngine.add(monitor);
        searchEngine.add(headphones);

        // Добавление всех статей в поисковый движок
        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);
        searchEngine.add(article4);
        searchEngine.add(article5);

        System.out.println("=== ТЕСТИРОВАНИЕ ПОИСКОВОЙ СИСТЕМЫ ===\n");

        // Тестирование поиска по разным запросам
        System.out.println("1. Поиск по запросу 'ноутбук':");
        testSearch(searchEngine, "ноутбук");

        System.out.println("\n2. Поиск по запросу 'мышь':");
        testSearch(searchEngine, "мышь");

        System.out.println("\n3. Поиск по запросу 'беспроводные':");
        testSearch(searchEngine, "беспроводные");

        System.out.println("\n4. Поиск по запросу 'техник':");
        testSearch(searchEngine, "техник");

        System.out.println("\n5. Поиск по запросу 'монитор':");
        testSearch(searchEngine, "монитор");

        System.out.println("\n6. Поиск по запросу '2024':");
        testSearch(searchEngine, "2024");

        System.out.println("\n7. Поиск по запросу 'xyz' (нет результатов):");
        testSearch(searchEngine, "xyz");

        // Демонстрация метода getStringRepresentation()
        System.out.println("\n=== ДЕМОНСТРАЦИЯ getStringRepresentation() ===");
        System.out.println("Товар: " + laptop.getStringRepresentation());
        System.out.println("Статья: " + article1.getStringRepresentation());

        // Проверка обратной совместимости с корзиной
        System.out.println("\n=== ПРОВЕРКА ОБРАТНОЙ СОВМЕСТИМОСТИ ===");
        ProductBasket basket = new ProductBasket();
        basket.addProduct(laptop);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);

        System.out.println("Содержимое корзины:");
        basket.printBasket();
    }

    private static void testSearch(SearchEngine searchEngine, String searchQuery) {
        System.out.println("Запрос: \"" + searchQuery + "\"");
        Searchable[] results = searchEngine.search(searchQuery);

        boolean foundResults = false;
        for (Searchable result : results) {
            if (result != null) {
                System.out.println("  Найден: " + result.getStringRepresentation());
                foundResults = true;
            }
        }

        if (!foundResults) {
            System.out.println("  Результаты не найдены");
        }
    }
}



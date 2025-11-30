package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.model.Article;
import org.skypro.skyshop.model.Searchable;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.system.BestResultNotFound;
import org.skypro.skyshop.system.SearchEngine;



public class App {
    public static void main(String[] args) {

        System.out.println("=== ДЕМОНСТРАЦИЯ ПРОВЕРОК ДАННЫХ ===\n");

        // Демонстрация проверок данных с обработкой исключений
        demonstrateDataValidation();

        System.out.println("\n=== ДЕМОНСТРАЦИЯ РАБОТЫ ПОИСКА ===\n");

        // Создание корректных данных для демонстрации поиска
        demonstrateSearchFunctionality();
    }

    private static void demonstrateDataValidation() {
        // Тестирование неправильных данных с обработкой исключений

        // 1. Неправильное название продукта
        System.out.println("1. Попытка создания продукта с пустым названием:");
        try {
            SimpleProduct invalidProduct = new SimpleProduct("", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // 2. Неправильная цена
        System.out.println("\n2. Попытка создания продукта с ценой 0:");
        try {
            SimpleProduct invalidPrice = new SimpleProduct("Телефон", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // 3. Неправильный процент скидки
        System.out.println("\n3. Попытка создания продукта со скидкой 150%:");
        try {
            DiscountedProduct invalidDiscount = new DiscountedProduct("Планшет", 20000, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // 4. Отрицательная базовая цена
        System.out.println("\n4. Попытка создания продукта с отрицательной ценой:");
        try {
            DiscountedProduct negativePrice = new DiscountedProduct("Мышь", -100, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // 5. Неправильное название статьи
        System.out.println("\n5. Попытка создания статьи с пустым названием:");
        try {
            Article invalidArticle = new Article("   ", "Текст статьи");
        } catch (IllegalArgumentException e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // 6. Неправильный текст статьи
        System.out.println("\n6. Попытка создания статьи с пустым текстом:");
        try {
            Article emptyTextArticle = new Article("Заголовок", null);
        } catch (IllegalArgumentException e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // 7. Успешное создание объектов
        System.out.println("\n7. Успешное создание корректных объектов:");
        try {
            SimpleProduct validProduct = new SimpleProduct("Ноутбук", 50000);
            DiscountedProduct validDiscounted = new DiscountedProduct("Мышь", 2000, 25);
            Article validArticle = new Article("Обзор техники", "Отличный ноутбук для работы и игр.");

            System.out.println("   Продукт создан: " + validProduct.getNameProduct());
            System.out.println("   Продукт со скидкой создан: " + validDiscounted.getNameProduct());
            System.out.println("   Статья создана: " + validArticle.getTitleArticle());
        } catch (IllegalArgumentException e) {
            System.out.println("   Неожиданная ошибка: " + e.getMessage());
        }
    }

    private static void demonstrateSearchFunctionality() {
        // Создание поискового движка и добавление данных
        SearchEngine searchEngine = new SearchEngine(20);

        // Добавление товаров
        searchEngine.add(new SimpleProduct("Ноутбук игровой", 70000));
        searchEngine.add(new DiscountedProduct("Игровая мышь", 3000, 15));
        searchEngine.add(new FixPriceProduct("Игровая клавиатура"));
        searchEngine.add(new SimpleProduct("Монитор игровой", 25000));
        searchEngine.add(new DiscountedProduct("Игровые наушники", 5000, 20));

        // Добавление статей с повторяющимися словами для тестирования
        searchEngine.add(new Article("Игровые ноутбуки 2024",
                "Лучшие игровые ноутбуки 2024 года. Игровые ноутбуки становятся все мощнее."));
        searchEngine.add(new Article("Обзор игровой мыши",
                "Игровая мышь для профессиональных геймеров. Игровая мышь должна быть удобной."));
        searchEngine.add(new Article("Топ игровых мониторов",
                "Игровые мониторы с высокой частотой обновления. Игровые мониторы важны для комфортной игры."));

        // Демонстрация обычного поиска
        System.out.println("1. Обычный поиск по запросу 'игровой':");
        Searchable[] results = searchEngine.search("игровой");
        for (Searchable result : results) {
            if (result != null) {
                System.out.println("   - " + result.getStringRepresentation());
            }
        }

        // Демонстрация поиска лучшего совпадения (успешный случай)
        System.out.println("\n2. Поиск лучшего совпадения для 'игровой':");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("игровой");
            System.out.println("   Лучший результат: " + bestMatch.getStringRepresentation());

            // Покажем сколько раз встречается слово
            String searchTerm = bestMatch.getSearchTerm().toLowerCase();
            int occurrences = countOccurrences(searchTerm, "игровой");
            System.out.println("   Слово 'игровой' встречается " + occurrences + " раз(а)");

        } catch (BestResultNotFound e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // Демонстрация поиска лучшего совпадения (исключение)
        System.out.println("\n3. Поиск лучшего совпадения для несуществующего запроса:");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("несуществующийзапрос");
            System.out.println("   Лучший результат: " + bestMatch.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // Демонстрация с конкретным словом
        System.out.println("\n4. Поиск лучшего совпадения для 'ноутбук':");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("ноутбук");
            System.out.println("   Лучший результат: " + bestMatch.getStringRepresentation());

            String searchTerm = bestMatch.getSearchTerm().toLowerCase();
            int occurrences = countOccurrences(searchTerm, "ноутбук");
            System.out.println("   Слово 'ноутбук' встречается " + occurrences + " раз(а)");

        } catch (BestResultNotFound e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // Демонстрация работы корзины с проверенными данными
        System.out.println("\n=== ПРОВЕРКА РАБОТЫ КОРЗИНЫ ===\n");
        ProductBasket basket = new ProductBasket();
        try {
            basket.addProduct(new SimpleProduct("Проверенный товар", 1000));
            basket.addProduct(new DiscountedProduct("Товар со скидкой", 2000, 10));
            basket.printBasket();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при добавлении в корзину: " + e.getMessage());
        }
    }

    // Вспомогательный метод для подсчета вхождений
    private static int countOccurrences(String text, String substring) {
        int count = 0;
        int index = 0;
        int substringIndex = text.indexOf(substring, index);

        while (substringIndex != -1) {
            count++;
            index = substringIndex + substring.length();
            substringIndex = text.indexOf(substring, index);
        }

        return count;
    }

}



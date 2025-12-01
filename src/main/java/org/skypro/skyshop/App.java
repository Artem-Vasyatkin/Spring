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

        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ С MAP В КОРЗИНЕ ===\n");

        // Демонстрация работы корзины с Map
        demonstrateBasketWithMap();

        System.out.println("\n=== ДЕМОНСТРАЦИЯ РАБОТЫ ПОИСКА С ОТСОРТИРОВАННОЙ MAP ===\n");

        // Демонстрация работы поиска с отсортированной Map
        demonstrateSearchWithSortedMap();
    }

    private static void demonstrateBasketWithMap() {
        ProductBasket basket = new ProductBasket();

        // Создание продуктов - добавляем несколько продуктов с одинаковыми именами
        SimpleProduct laptop1 = new SimpleProduct("Ноутбук", 50000);
        DiscountedProduct mouse1 = new DiscountedProduct("Мышь", 2000, 25);
        FixPriceProduct keyboard = new FixPriceProduct("Клавиатура");
        SimpleProduct monitor = new SimpleProduct("Монитор", 20000);
        DiscountedProduct headphones = new DiscountedProduct("Наушники", 8000, 50);
        SimpleProduct laptop2 = new SimpleProduct("Ноутбук", 45000); // Второй ноутбук
        SimpleProduct mouse2 = new SimpleProduct("Мышь", 1500); // Вторая мышь

        System.out.println("1. Добавление продуктов в корзину (используется Map):");

        // Добавляем продукты
        basket.addProduct(laptop1);
        basket.addProduct(mouse1);
        basket.addProduct(keyboard);
        basket.addProduct(monitor);
        basket.addProduct(headphones);
        basket.addProduct(laptop2);
        basket.addProduct(mouse2);

        System.out.println("   В корзине " + basket.getProductCount() + " товаров");
        System.out.println("   Уникальных наименований: " + basket.getAllProducts().stream()
                .map(Product::getNameProduct)
                .distinct()
                .count());

        System.out.println("\n2. Содержимое корзины (отсортировано по именам):");
        basket.printBasket();

        // Демонстрация удаления продукта по имени
        System.out.println("\n3. Удаление продукта 'Мышь':");
        List<Product> removedMice = basket.removeProduct("Мышь");
        System.out.println("   Удалено " + removedMice.size() + " товаров с именем 'Мышь'");

        System.out.println("\n   Содержимое корзины после удаления:");
        basket.printBasket();

        // Проверка contains методом
        System.out.println("\n4. Проверка наличия товаров:");
        System.out.println("   'Ноутбук' в корзине: " + basket.containsProductBuName("Ноутбук"));
        System.out.println("   'Мышь' в корзине: " + basket.containsProductBuName("Мышь"));
        System.out.println("   'Принтер' в корзине: " + basket.containsProductBuName("Принтер"));

        // Демонстрация добавления еще одного товара с существующим именем
        System.out.println("\n5. Добавление еще одного 'Ноутбука':");
        basket.addProduct(new SimpleProduct("Ноутбук", 55000));

        System.out.println("\n   Финальное содержимое корзины:");
        basket.printBasket();
    }

    private static void demonstrateSearchWithSortedMap() {
        SearchEngine searchEngine = new SearchEngine();

        // Добавление товаров в разном порядке
        searchEngine.add(new SimpleProduct("Монитор игровой", 25000));
        searchEngine.add(new DiscountedProduct("Мышь игровая", 3000, 15));
        searchEngine.add(new FixPriceProduct("Аксессуар"));
        searchEngine.add(new SimpleProduct("Клавиатура игровая", 5000));
        searchEngine.add(new DiscountedProduct("Наушники игровые", 5000, 20));
        searchEngine.add(new SimpleProduct("Ноутбук игровой", 70000));

        // Добавление статей в разном порядке
        searchEngine.add(new Article("Обзор аксессуаров",
                "Лучшие игровые аксессуары для комфортной игры."));
        searchEngine.add(new Article("Выбор игровой мыши",
                "Как выбрать игровую мышь для профессиональных геймеров."));
        searchEngine.add(new Article("Игровые мониторы",
                "Топ игровых мониторов с высокой частотой обновления."));
        searchEngine.add(new Article("Ноутбуки для игр",
                "Лучшие игровые ноутбуки 2024 года."));

        System.out.println("1. Поиск ВСЕХ результатов по запросу 'игровой' (отсортировано по именам):");
        Map<String, Searchable> sortedResults = searchEngine.search("игровой");

        System.out.println("   Найдено " + sortedResults.size() + " результатов:");
        for (Map.Entry<String, Searchable> entry : sortedResults.entrySet()) {
            Searchable result = entry.getValue();
            System.out.println("   - " + result.getStringRepresentation());
        }

        // Проверка сортировки - выводим ключи
        System.out.println("\n   Ключи в отсортированном порядке:");
        for (String key : sortedResults.keySet()) {
            System.out.println("   * " + key);
        }

        System.out.println("\n2. Поиск результатов по запросу 'аксессуар':");
        Map<String, Searchable> accessoryResults = searchEngine.search("аксессуар");

        System.out.println("   Найдено " + accessoryResults.size() + " результатов:");
        for (Map.Entry<String, Searchable> entry : accessoryResults.entrySet()) {
            Searchable result = entry.getValue();
            System.out.println("   - " + result.getStringRepresentation());
        }

        // Демонстрация поиска без результатов
        System.out.println("\n3. Поиск результатов по запросу 'планшет':");
        Map<String, Searchable> tabletResults = searchEngine.search("планшет");

        if (tabletResults.isEmpty()) {
            System.out.println("   Результаты не найдены");
        } else {
            System.out.println("   Найдено " + tabletResults.size() + " результатов:");
            for (Map.Entry<String, Searchable> entry : tabletResults.entrySet()) {
                Searchable result = entry.getValue();
                System.out.println("   - " + result.getStringRepresentation());
            }
        }

        // Демонстрация того, что дубликаты имен перезаписываются
        System.out.println("\n4. Демонстрация обработки дубликатов имен:");
        searchEngine.add(new Article("Игровые мониторы",
                "Новая статья про игровые мониторы (дубликат имени)."));

        Map<String, Searchable> monitorResults = searchEngine.search("монитор");
        System.out.println("   Найдено " + monitorResults.size() + " результатов по запросу 'монитор':");
        for (Map.Entry<String, Searchable> entry : monitorResults.entrySet()) {
            Searchable result = entry.getValue();
            System.out.println("   - " + result.getStringRepresentation() +
                    " (тип: " + result.getContentType() + ")");
        }
        System.out.println("   Примечание: при дубликатах имен сохраняется последний добавленный элемент");

        // Демонстрация поиска лучшего совпадения
        System.out.println("\n5. Поиск лучшего совпадения для 'игровой':");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("игровой");
            System.out.println("   Лучший результат: " + bestMatch.getStringRepresentation());

            String searchTerm = bestMatch.getSearchTerm().toLowerCase();
            int occurrences = countOccurrences(searchTerm, "игровой");
            System.out.println("   Слово 'игровой' встречается " + occurrences + " раз(а)");

        } catch (BestResultNotFound e) {
            System.out.println("   Ошибка: " + e.getMessage());
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



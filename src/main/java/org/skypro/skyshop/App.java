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


public class App {
    public static void main(String[] args) {

        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ С ОБНОВЛЕННЫМИ СТРУКТУРАМИ ДАННЫХ ===\n");

        // Демонстрация работы корзины со списком
        demonstrateBasketWithList();

        System.out.println("\n=== ДЕМОНСТРАЦИЯ РАБОТЫ ПОИСКА СО СПИСКОМ ===\n");

        // Демонстрация работы поиска со списком
        demonstrateSearchWithList();
    }

    private static void demonstrateBasketWithList() {
        ProductBasket basket = new ProductBasket();

        // Создание продуктов
        SimpleProduct laptop1 = new SimpleProduct("Ноутбук", 50000);
        DiscountedProduct mouse1 = new DiscountedProduct("Мышь", 2000, 25);
        FixPriceProduct keyboard = new FixPriceProduct("Клавиатура");
        SimpleProduct monitor = new SimpleProduct("Монитор", 20000);
        DiscountedProduct headphones = new DiscountedProduct("Наушники", 8000, 50);
        SimpleProduct laptop2 = new SimpleProduct("Ноутбук", 45000); // Второй ноутбук с другим именем
        SimpleProduct mouse2 = new SimpleProduct("Мышь", 1500); // Вторая мышь

        System.out.println("1. Добавление продуктов в корзину (без ограничения по количеству):");

        // Добавляем много продуктов - теперь нет ограничения в 5 элементов
        basket.addProduct(laptop1);
        basket.addProduct(mouse1);
        basket.addProduct(keyboard);
        basket.addProduct(monitor);
        basket.addProduct(headphones);
        basket.addProduct(laptop2);
        basket.addProduct(mouse2);

        System.out.println("   В корзине теперь " + basket.getProductCount() + " товаров");
        basket.printBasket();

        // Демонстрация удаления продукта по имени (существующий продукт)
        System.out.println("\n2. Удаление существующего продукта 'Мышь':");
        List<Product> removedProducts = basket.removeProduct("Мышь");

        if (!removedProducts.isEmpty()) {
            System.out.println("   Удаленные продукты:");
            for (Product removed : removedProducts) {
                System.out.println("   - " + removed.toString());
            }
        } else {
            System.out.println("   Список пуст");
        }

        System.out.println("\n   Содержимое корзины после удаления:");
        basket.printBasket();

        // Демонстрация удаления несуществующего продукта
        System.out.println("\n3. Удаление несуществующего продукта 'Принтер':");
        List<Product> removedNonExistent = basket.removeProduct("Принтер");

        if (!removedNonExistent.isEmpty()) {
            System.out.println("   Удаленные продукты:");
            for (Product removed : removedNonExistent) {
                System.out.println("   - " + removed.toString());
            }
        } else {
            System.out.println("   Список пуст - продукт 'Принтер' не найден в корзине");
        }

        System.out.println("\n   Содержимое корзины остается неизменным:");
        basket.printBasket();

        // Демонстрация удаления продукта, который есть в нескольких экземплярах
        System.out.println("\n4. Удаление продукта 'Ноутбук' (несколько экземпляров):");
        List<Product> removedLaptops = basket.removeProduct("Ноутбук");

        if (!removedLaptops.isEmpty()) {
            System.out.println("   Удаленные ноутбуки:");
            for (Product removed : removedLaptops) {
                System.out.println("   - " + removed.toString());
            }
            System.out.println("   Всего удалено: " + removedLaptops.size() + " ноутбук(ов)");
        } else {
            System.out.println("   Список пуст");
        }

        System.out.println("\n   Финальное содержимое корзины:");
        basket.printBasket();

        // Демонстрация очистки корзины
        System.out.println("\n5. Очистка корзины:");
        basket.clearBasket();
        basket.printBasket();
    }

    private static void demonstrateSearchWithList() {
        SearchEngine searchEngine = new SearchEngine(10);

        // Добавление товаров
        searchEngine.add(new SimpleProduct("Игровой ноутбук", 70000));
        searchEngine.add(new DiscountedProduct("Игровая мышь", 3000, 15));
        searchEngine.add(new FixPriceProduct("Игровая клавиатура"));
        searchEngine.add(new SimpleProduct("Игровой монитор", 25000));
        searchEngine.add(new DiscountedProduct("Игровые наушники", 5000, 20));
        searchEngine.add(new SimpleProduct("Обычный ноутбук", 40000));
        searchEngine.add(new SimpleProduct("Офисная мышь", 1000));

        // Добавление статей
        searchEngine.add(new Article("Обзор игровых ноутбуков",
                "Лучшие игровые ноутбуки 2024 года. Игровые ноутбуки для геймеров."));
        searchEngine.add(new Article("Выбор игровой мыши",
                "Как выбрать игровую мышь. Игровая мышь должна быть удобной для длительных игровых сессий."));
        searchEngine.add(new Article("Игровые аксессуары",
                "Топ игровых аксессуаров для комфортной игры. Игровые клавиатуры, мыши и наушники."));

        // Демонстрация поиска с возвратом всех результатов
        System.out.println("1. Поиск ВСЕХ результатов по запросу 'игровой':");
        List<Searchable> allResults = searchEngine.search("игровой");

        System.out.println("   Найдено " + allResults.size() + " результатов:");
        for (Searchable result : allResults) {
            System.out.println("   - " + result.getStringRepresentation());
        }

        // Демонстрация поиска с небольшим количеством результатов
        System.out.println("\n2. Поиск ВСЕХ результатов по запросу 'офисная':");
        List<Searchable> officeResults = searchEngine.search("офисная");

        System.out.println("   Найдено " + officeResults.size() + " результатов:");
        for (Searchable result : officeResults) {
            System.out.println("   - " + result.getStringRepresentation());
        }

        // Демонстрация поиска без результатов
        System.out.println("\n3. Поиск ВСЕХ результатов по запросу 'планшет':");
        List<Searchable> tabletResults = searchEngine.search("планшет");

        if (tabletResults.isEmpty()) {
            System.out.println("   Результаты не найдены");
        } else {
            System.out.println("   Найдено " + tabletResults.size() + " результатов:");
            for (Searchable result : tabletResults) {
                System.out.println("   - " + result.getStringRepresentation());
            }
        }

        // Демонстрация поиска лучшего совпадения (работает как и раньше)
        System.out.println("\n4. Поиск лучшего совпадения для 'игровой':");
        try {
            Searchable bestMatch = searchEngine.findBestMatch("игровой");
            System.out.println("   Лучший результат: " + bestMatch.getStringRepresentation());

            String searchTerm = bestMatch.getSearchTerm().toLowerCase();
            int occurrences = countOccurrences(searchTerm, "игровой");
            System.out.println("   Слово 'игровой' встречается " + occurrences + " раз(а)");

        } catch (BestResultNotFound e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        // Демонстрация добавления большого количества элементов (нет ограничения)
        System.out.println("\n5. Добавление дополнительных элементов в поисковый движок:");
        for (int i = 1; i <= 10; i++) {
            searchEngine.add(new SimpleProduct("Дополнительный товар " + i, 1000 * i));
        }

        List<Searchable> additionalResults = searchEngine.search("Дополнительный");
        System.out.println("   Теперь найдено " + additionalResults.size() + " дополнительных товаров");
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



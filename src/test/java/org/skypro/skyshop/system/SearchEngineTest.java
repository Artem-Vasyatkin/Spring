package org.skypro.skyshop.system;

import org.skypro.skyshop.model.Article;
import org.skypro.skyshop.model.Searchable;
import org.skypro.skyshop.product.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class SearchEngineTest {

    private SearchEngine searchEngine;
    private Product laptop;
    private Article article;

    @BeforeEach
    void setUp() {

        searchEngine = new SearchEngine();

        laptop = new SimpleProduct("Игровой ноутбук", 70000);
        article = new Article("Обзор ноутбуков",
                "Лучшие игровые ноутбуки 2024 года.");
    }

    @Test
    void testAddSearchable() {
        //  добавляем объект для поиска
        searchEngine.add(laptop);

        // Ищем этот объект
        Map<String, Searchable> results = searchEngine.search("ноутбук");

        assertEquals(1, results.size());
        assertTrue(results.containsKey("Игровой ноутбук"));
    }

    @Test
    void testSearchEmptyEngine() {
        // поиск в пустом движке
        Map<String, Searchable> results = searchEngine.search("ноутбук");

        // Должен вернуть пустую Map
        assertTrue(results.isEmpty());
    }

    @Test
    void testSearchCaseInsensitive() {
        // поиск без учета регистра
        searchEngine.add(laptop);

        Map<String, Searchable> results1 = searchEngine.search("НОУТБУК");
        Map<String, Searchable> results2 = searchEngine.search("ноутбук");
        Map<String, Searchable> results3 = searchEngine.search("НоутБук");

        assertEquals(1, results1.size());
        assertEquals(1, results2.size());
        assertEquals(1, results3.size());
    }

    @Test
    void testSearchPartialMatch() {
        // частичное совпадение
        searchEngine.add(laptop);

        // Ищем по части слова
        Map<String, Searchable> results = searchEngine.search("игр");

        assertEquals(1, results.size());
    }

    @Test
    void testSearchMultipleItems() {
        // поиск среди нескольких объектов
        searchEngine.add(laptop);
        searchEngine.add(article);
        searchEngine.add(new SimpleProduct("Мышь", 2000));

        // Ищем только ноутбуки
        Map<String, Searchable> results = searchEngine.search("ноутбук");

        assertEquals(2, results.size()); // И товар, и статья
        assertTrue(results.containsKey("Игровой ноутбук"));
        assertTrue(results.containsKey("Обзор ноутбуков"));
        assertFalse(results.containsKey("Мышь"));
    }

    @Test
    void testSearchReturnsSortedMap() {
        // проверяем, что результаты отсортированы
        searchEngine.add(new SimpleProduct("Яблоко", 100));
        searchEngine.add(new SimpleProduct("Банан", 50));
        searchEngine.add(new SimpleProduct("Апельсин", 80));

        Map<String, Searchable> results = searchEngine.search("");

        // Проверяем порядок ключей
        String[] keys = results.keySet().toArray(new String[0]);
        assertEquals("Апельсин", keys[0]);
        assertEquals("Банан", keys[1]);
        assertEquals("Яблоко", keys[2]);
    }

    @Test
    void testSearchWithEmptyString() {
        // поиск с пустой строкой
        searchEngine.add(laptop);
        searchEngine.add(article);

        // Пустая строка должна найти все
        Map<String, Searchable> results = searchEngine.search("");

        assertEquals(2, results.size());
    }

    @Test
    void testSearchArticleByText() {
        // поиск по тексту статьи
        searchEngine.add(article);

        // Ищем слово из текста статьи
        Map<String, Searchable> results = searchEngine.search("2024");

        assertEquals(1, results.size());
        assertTrue(results.containsKey("Обзор ноутбуков"));
    }

    @Test
    void testSearchNotFound() {
        // поиск несуществующего
        searchEngine.add(laptop);

        Map<String, Searchable> results = searchEngine.search("телефон");

        // Не должно найти ничего
        assertTrue(results.isEmpty());
    }

    @Test
    void testFindBestMatch() throws Exception {
        //  поиск лучшего совпадения
        Article articleWithManyMatches = new Article(
                "Игровой ноутбук",
                "Игровые ноутбуки, игровые ноутбуки, игровые ноутбуки."
        );

        searchEngine.add(laptop); // "игровой" встречается 1 раз
        searchEngine.add(articleWithManyMatches); // "игровой" встречается 3 раза

        Searchable bestMatch = searchEngine.findBestMatch("игровой");


        assertEquals("Игровой ноутбук", bestMatch.getName());
    }

    @Test
    void testFindBestMatchNotFound() {
        //  лучший результат не найден
        searchEngine.add(laptop);

        // Пытаемся найти то, чего нет
        try {
            searchEngine.findBestMatch("телефон");
            fail("Должно было выбросить исключение");
        } catch (BestResultNotFound e) {
            // Проверяем сообщение об ошибке
            assertTrue(e.getMessage().contains("телефон"));
        }
    }

    @Test
    void testFindBestMatchEmptyQuery() {
        // пустой поисковый запрос
        try {
            searchEngine.findBestMatch("   ");
            fail("Должно было выбросить исключение");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("пустым"));
        } catch (Exception e) {
            fail("Должно быть IllegalArgumentException");
        }
    }

    @Test
    void testDuplicateNamesOverwrite() {
        // дублирующиеся имена перезаписываются
        Product product1 = new SimpleProduct("Ноутбук", 50000);
        Product product2 = new DiscountedProduct("Ноутбук", 60000, 10);

        searchEngine.add(product1);
        searchEngine.add(product2);

        Map<String, Searchable> results = searchEngine.search("ноутбук");

        // Должен остаться только один (последний добавленный)
        assertEquals(1, results.size());

        // Проверяем, что остался второй товар
        Searchable result = results.get("Ноутбук");
        assertEquals("Продукт", result.getContentType());
    }

    @Test
    void testMixedContentTypes() {
        // смешанные типы контента
        searchEngine.add(new SimpleProduct("Телефон", 30000));
        searchEngine.add(new Article("Новости телефонов", "Новые модели телефонов."));
        searchEngine.add(new FixPriceProduct("Чехол для телефона"));

        Map<String, Searchable> results = searchEngine.search("телефон");

        assertEquals(3, results.size());
    }
}
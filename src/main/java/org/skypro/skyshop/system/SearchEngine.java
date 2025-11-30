package org.skypro.skyshop.system;

import org.skypro.skyshop.model.Searchable;

public class SearchEngine {
    private final Searchable[] searchables;
    private int currentIndex;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.currentIndex = 0;
    }

    public void add(Searchable searchable) {
        if (currentIndex < searchables.length) {
            searchables[currentIndex] = searchable;
            currentIndex++;
        } else {
            System.out.println("Невозможно добавить элемент, поисковый движок заполнен!");
        }
    }

    public Searchable[] search(String searchString) {
        Searchable[] results = new Searchable[5];
        int resulsIndex = 0;

        for (int i = 0; i < currentIndex; i++) {
            Searchable item = searchables[i];
            if (item != null && item.getSearchTerm().toLowerCase().contains(searchString.toLowerCase())) {
                results[resulsIndex] = item;
                resulsIndex++;

                if (resulsIndex >= 5) {
                    break;
                }
            }
        }
        return results;
    }

    public Searchable findBestMatch (String search) throws BestResultNotFound {
        if (search == null || search.trim().isBlank()) {
            throw new IllegalArgumentException("поисковый запрос не может быть пустым!");
        }

        Searchable bestMatch = null;
        int maxOccurrences = 0;
        String searchLower = search.toLowerCase();

        for (int i = 0; i < currentIndex; i++) {
            Searchable item = searchables[i];
            if (item != null) {
                String searchTerm = item.getSearchTerm().toLowerCase();
                int occurrences = countOccurrences(searchTerm, searchLower);


                if (occurrences > maxOccurrences) {
                    maxOccurrences = occurrences;
                    bestMatch = item;
                }
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }
        return bestMatch;
    }

    private int countOccurrences(String text, String substring) {
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

package org.skypro.skyshop.system;

import org.skypro.skyshop.model.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final List<Searchable> items = new ArrayList<>();


    public void add(Searchable item) {
        items.add(item);
    }

    public Map<String, Searchable> search(String searchString) {
        Map<String, Searchable> results = new TreeMap<>();

        for (Searchable item : items) {
            if (item != null) {
                String searchTerm = item.getSearchTerm().toLowerCase();
                String searchLower = searchString.toLowerCase();

                if (searchTerm.contains(searchLower)) {
                    results.put(item.getName(), item);
                }
            }
        }
        return results;
    }


    public Searchable findBestMatch(String search) throws BestResultNotFound {
        if (search == null || search.trim().isBlank()) {
            throw new IllegalArgumentException("поисковый запрос не может быть пустым!");
        }

        Searchable bestMatch = null;
        int maxCount = 0;
        String searchLower = search.toLowerCase();

        for (Searchable item : items) {
            if (item != null) {
                String searchTerm = item.getSearchTerm().toLowerCase();
                int count = countOccurrences(searchTerm, searchLower);

                if (count > maxCount) {
                    maxCount = count;
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

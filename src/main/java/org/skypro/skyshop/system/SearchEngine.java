package org.skypro.skyshop.system;

import org.skypro.skyshop.model.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final List<Searchable> searchables;


    public SearchEngine() {
        this.searchables = new ArrayList<>();
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Map<String, Searchable> search(String searchString) {
        Map<String, Searchable> results = new TreeMap<>();

        for (Searchable item : searchables) {
            if (item != null && item.getSearchTerm().toLowerCase().contains(searchString.toLowerCase())) {
                results.put(item.getNameProduct(), item);
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

        for (Searchable item : searchables) {
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

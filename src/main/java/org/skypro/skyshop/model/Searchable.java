package org.skypro.skyshop.model;

public interface Searchable {
    String getSearchTerm();  // что искать

    String getContentType(); // тип товара

    String getName(); // название

    default String getStringRepresentation() {
        return getName() + " - " + getContentType();
    }

}

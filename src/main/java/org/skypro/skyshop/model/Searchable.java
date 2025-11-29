package org.skypro.skyshop.model;

public interface Searchable {
    String getSearchTerm();

    String getContentType();

    String getNameProduct();

    default String getStringRepresentation() {
        return getNameProduct() + " - " + getContentType();
    }

}

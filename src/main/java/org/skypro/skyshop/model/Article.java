package org.skypro.skyshop.model;

public class Article implements Searchable {

    private final String titleArticle;
    private final String textArticle;

    public Article(String titleArticle, String textArticle) {
        if (titleArticle == null || titleArticle.trim().isBlank()) {
            throw new IllegalArgumentException("Название статьи не может быть пустым или null");
        }
        this.titleArticle = titleArticle;
        if (textArticle == null || textArticle.trim().isBlank()) {
            throw new IllegalArgumentException("Текст статьи не может быть пустым или null");
        }
        this.textArticle = textArticle;
    }

    public String getTitleArticle() {
        return titleArticle;
    }

    public String getTextArticle() {
        return textArticle;
    }

    @Override
    public String toString() {
        return "Article{" +
                "Название статьи='" + titleArticle + '\'' +
                ", Текст статьи='" + textArticle + '\'' +
                '}';
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    public String getNameProduct() {
        return titleArticle;
    }
}

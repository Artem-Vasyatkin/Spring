package org.skypro.skyshop.model;

public class Article implements Searchable {

    private final String titleArticle;
    private final String textArticle;

    public Article(String titleArticle, String textArticle) {
        this.titleArticle = titleArticle;
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

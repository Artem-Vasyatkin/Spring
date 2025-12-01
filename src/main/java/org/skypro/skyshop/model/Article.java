package org.skypro.skyshop.model;

public class Article implements Searchable {

    private final String title;
    private final String text;

    public Article(String title, String text) {
        if (title == null || title.trim().isBlank()) {
            throw new IllegalArgumentException("Название статьи не может быть пустым или null");
        }
        this.title = title.trim();
        if (text == null || text.trim().isBlank()) {
            throw new IllegalArgumentException("Текст статьи не может быть пустым или null");
        }
        this.text = text.trim();
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "СТАТЬЯ{" +
                "Название статьи='" + title + '\'' +
                ", Текст статьи='" + text + '\'' +
                '}';
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "Статья";
    }

    public String getName() {
        return title;
    }
}

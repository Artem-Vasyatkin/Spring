package org.skypro.skyshop.system;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String searchQuery) {
        super("Не найден подходящий результат для поискового запроса: '" + searchQuery + "'");
    }
}


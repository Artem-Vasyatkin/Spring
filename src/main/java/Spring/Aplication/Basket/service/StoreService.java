package Spring.Aplication.Basket.service;

import Spring.Aplication.Basket.model.Item;

import java.util.List;

public interface StoreService {
    void add(List<Long> itemIds);

    List<Item> get();
}

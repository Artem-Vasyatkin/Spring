package Spring.Aplication.Basket.service;

import Spring.Aplication.Basket.model.Item;
import Spring.Aplication.Basket.repository.Basket;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class StoreServiceImpl implements StoreService {

    private final Basket basket;

    public StoreServiceImpl(Basket basket) {
        this.basket = basket;
    }

    @Override
    public void add(List<Long> itemIds) {
        basket.addAll(itemIds.stream()
                .map(Item :: new)
                .toList());
    }


    @Override
    public List<Item> get() {
        return basket.getAll();
    }

}
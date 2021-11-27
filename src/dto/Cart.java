package dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tackedev
 */
public class Cart {

    private Map<Product, Integer> items;

    // singleton
    private static Cart instance;

    private Cart() {
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void add(Product product) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        if (this.items.get(product) != null) {
            this.items.put(product, items.get(product) + 1);
        } else {
            this.items.put(product, 1);
        }

    }

    public void clear() {
        this.items = null;
    }

    public int getTotal() {
        int total = 0;
        if (this.items != null) {
            for (var item : items.entrySet()) {
                total += item.getKey().getPrice() * item.getValue();
            }
        }
        return total;
    }

}

package dto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tackedev
 */
public class ProductStorage {

    private final List<Product> products = new ArrayList<>();

    // singleton
    private static ProductStorage instance;

    private ProductStorage() {
    }

    private static void initFromFile() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int seperatorId = line.indexOf('=');

                String name = line.substring(0, seperatorId);
                int price = Integer.parseInt(line.substring(seperatorId + 1));

                instance.add(new Product(name, price));
            }
        }
    }

    public static ProductStorage getInstance() throws IOException {
        if (instance == null) {
            instance = new ProductStorage();
            initFromFile();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Product get(int id) {
        return this.products.get(id);
    }

    public int size() {
        return this.products.size();
    }

    public void add(Product product) {
        this.products.add(product);
    }
}

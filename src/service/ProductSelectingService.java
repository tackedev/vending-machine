package service;

import dto.Cash;
import dto.Product;
import dto.ProductStorage;

import java.io.IOException;
import java.util.List;

/**
 * @author tackedev
 */
public class ProductSelectingService implements Service {

    private final Cash currentCash;
    private final ProductStorage productStorage;

    public ProductSelectingService() throws IOException {
        currentCash = Cash.getInstance();
        productStorage = ProductStorage.getInstance();
    }

    @Override
    public void execute(char input) throws CanceledRequestException, FinishedStepException {

        List<Product> products = productStorage.getProducts();

        switch (input) {
            case 'c': case 'C':
                throw new CanceledRequestException();
            default:
                int inputValue = Character.digit(input, 10);

                if (1 <= inputValue && inputValue <= products.size()) {
                    Product selectedProduct = productStorage.get(inputValue - 1);
                    if (currentCash.getAmount() >= selectedProduct.getPrice()) {
                        currentCash.decreaseAmount(selectedProduct.getPrice());
                        throw new FinishedStepException(selectedProduct);
                    } else {
                        throw new FinishedStepException("Not enough money!");
                    }
                }
        }

    }
}

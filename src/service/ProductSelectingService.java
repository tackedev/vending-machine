package service;

import dto.Cart;
import dto.Cash;
import dto.PayInfo;
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
    private final Cart currentCart;

    public ProductSelectingService() throws IOException {
        currentCash = Cash.getInstance();
        productStorage = ProductStorage.getInstance();
        currentCart = Cart.getInstance();
    }

    @Override
    public void execute(char input) throws CanceledRequestException, FinishedStepException {

        List<Product> products = productStorage.getProducts();

        switch (input) {
            case 'p': case 'P':
                int total = currentCart.getTotal();

                if (total > currentCash.getAmount()) {
                    // clear the cart and send notification
                    currentCart.clear();
                    throw new FinishedStepException("Not enough money!");
                } else {
                    // clear currentCart and currentCash, then return the payInfo
                    PayInfo payInfo = new PayInfo(currentCart.getItems(), currentCash.getAmount() - total);
                    currentCart.clear();
                    currentCash.refund();
                    throw new FinishedStepException(payInfo);
                }
            case 'c': case 'C':
                currentCart.clear();
                throw new CanceledRequestException();
            default:
                int inputValue = Character.digit(input, 10);
                if (1 <= inputValue && inputValue <= products.size()) {
                    currentCart.add(products.get(inputValue-1));
                }
        }

    }
}

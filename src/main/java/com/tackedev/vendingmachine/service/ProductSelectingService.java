package com.tackedev.vendingmachine.service;

import com.tackedev.vendingmachine.dto.Cash;
import com.tackedev.vendingmachine.dto.Product;
import com.tackedev.vendingmachine.dto.ProductStorage;

import java.io.IOException;

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

        switch (input) {
            case 'c': case 'C':
                throw new CanceledRequestException();
            default:
                int inputValue = Character.digit(input, 10);

                if (1 <= inputValue && inputValue <= productStorage.size()) {
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

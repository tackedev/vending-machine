package com.tackedev.vendingmachine.service;

import com.tackedev.vendingmachine.dto.Award;
import com.tackedev.vendingmachine.dto.Cash;
import com.tackedev.vendingmachine.dto.Product;
import com.tackedev.vendingmachine.dto.ProductStorage;

import java.io.IOException;

/**
 * @author tackedev
 */
public class FreeProductSelectingService implements Service {

    private final ProductStorage productStorage;
    private final Award award;

    public FreeProductSelectingService() throws IOException {
        productStorage = ProductStorage.getInstance();
        award = Award.getInstance();
    }


    @Override
    public void execute(char input) throws CanceledRequestException, FinishedStepException {

        switch (input) {
            case 'c': case 'C':
                throw new CanceledRequestException();
            default:
                int inputValue = Character.digit(input, 10);

                if (1 <= inputValue && inputValue <= productStorage.size()) {
                    award.setGiven(false);

                    Product selectedProduct = productStorage.get(inputValue - 1);
                    throw new FinishedStepException(selectedProduct);
                }
        }
    }
}

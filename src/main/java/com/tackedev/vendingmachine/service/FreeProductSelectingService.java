package com.tackedev.vendingmachine.service;

import com.tackedev.vendingmachine.dto.Award;
import com.tackedev.vendingmachine.dto.Product;
import com.tackedev.vendingmachine.dto.ProductStorage;
import com.tackedev.vendingmachine.dto.StreakSelectedProduct;
import com.tackedev.vendingmachine.util.NumberFormatter;

import java.io.IOException;

/**
 * @author tackedev
 */
public class FreeProductSelectingService implements Service {

    private final ProductStorage productStorage;
    private final Award award;
    private final StreakSelectedProduct streakSelectedProduct;

    public FreeProductSelectingService() throws IOException {
        productStorage = ProductStorage.getInstance();
        award = Award.getInstance();
        streakSelectedProduct = StreakSelectedProduct.getInstance();
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

                    if (streakSelectedProduct.getRemainBudget() >= selectedProduct.getPrice()) {
                        // if remain budget is enough for this product
                        award.setGiven(false);
                        streakSelectedProduct.updateRemainBudge(selectedProduct);
                        throw new FinishedStepException(selectedProduct);
                    } else {
                        throw new FinishedStepException("Cannot select this product. The remain program's budget is " +
                                NumberFormatter.formatToVND(streakSelectedProduct.getRemainBudget()));
                    }
                }
        }
    }
}

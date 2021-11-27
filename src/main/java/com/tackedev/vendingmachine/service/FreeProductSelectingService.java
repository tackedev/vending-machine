package com.tackedev.vendingmachine.service;

import com.tackedev.vendingmachine.dto.Product;
import com.tackedev.vendingmachine.dto.ProductStorage;
import com.tackedev.vendingmachine.dto.AwardStatus;
import com.tackedev.vendingmachine.util.NumberFormatter;

import java.io.IOException;

/**
 * @author tackedev
 */
public class FreeProductSelectingService implements Service {

    private final ProductStorage productStorage;
    private final AwardStatus awardStatus;

    public FreeProductSelectingService() throws IOException {
        productStorage = ProductStorage.getInstance();
        awardStatus = AwardStatus.getInstance();
    }
    
    @Override
    public void execute(char input) throws CanceledRequestException, FinishedStepException {

        switch (input) {
            case 'c':
            case 'C':
                throw new CanceledRequestException();
            default:
                int inputValue = Character.digit(input, 10);

                if (1 <= inputValue && inputValue <= productStorage.size()) {
                    Product selectedProduct = productStorage.get(inputValue - 1);

                    if (awardStatus.getRemainBudget() >= selectedProduct.getPrice()) {
                        // if remain budget is enough for this product
                        awardStatus.setGiven(false);
                        awardStatus.updateRemainBudge(selectedProduct);
                        throw new FinishedStepException(selectedProduct);
                    } else {
                        throw new FinishedStepException("Cannot select this product. The remain program's budget is " +
                                NumberFormatter.formatToVND(awardStatus.getRemainBudget()));
                    }
                }
        }
    }
}

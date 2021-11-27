package com.tackedev.vendingmachine.service;

import com.tackedev.vendingmachine.config.AwardConfig;
import com.tackedev.vendingmachine.dto.Cash;
import com.tackedev.vendingmachine.dto.Product;
import com.tackedev.vendingmachine.dto.ProductStorage;
import com.tackedev.vendingmachine.dto.AwardStatus;

import java.io.IOException;

/**
 * @author tackedev
 */
public class ProductSelectingService implements Service {

    private final Cash currentCash;
    private final ProductStorage productStorage;
    private final AwardStatus awardStatus;

    public ProductSelectingService() throws IOException {
        currentCash = Cash.getInstance();
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
                    if (currentCash.getAmount() >= selectedProduct.getPrice()) {
                        // update streak selected product
                        awardStatus.updateStreak(selectedProduct);

                        // check given free product
                        if (awardStatus.getStreak() == AwardConfig.getStreak()) {
                            double randomNum = Math.random() * 100;
                            if (randomNum <= AwardConfig.getRate()) {
                                // check still remain budget
                                if (awardStatus.isEnoughBudget()) {
                                    awardStatus.setGiven(true);
                                }
                            }

                            awardStatus.reset();
                        }

                        // update current cash
                        currentCash.decreaseAmount(selectedProduct.getPrice());
                        throw new FinishedStepException(selectedProduct);
                    } else {
                        throw new FinishedStepException("Not enough money!");
                    }
                }
        }
    }
}

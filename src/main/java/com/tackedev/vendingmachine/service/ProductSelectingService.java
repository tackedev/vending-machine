package com.tackedev.vendingmachine.service;

import com.tackedev.vendingmachine.dto.Award;
import com.tackedev.vendingmachine.dto.Cash;
import com.tackedev.vendingmachine.dto.Product;
import com.tackedev.vendingmachine.dto.ProductStorage;
import com.tackedev.vendingmachine.dto.StreakSelectedProduct;

import java.io.IOException;

/**
 * @author tackedev
 */
public class ProductSelectingService implements Service {

    private final Cash currentCash;
    private final ProductStorage productStorage;
    private final StreakSelectedProduct streakSelectedProduct;
    private final Award award;

    public ProductSelectingService() throws IOException {
        currentCash = Cash.getInstance();
        productStorage = ProductStorage.getInstance();
        streakSelectedProduct = StreakSelectedProduct.getInstance();
        award = Award.getInstance();
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
                        streakSelectedProduct.updateStreak(selectedProduct);

                        // check given free product
                        if (streakSelectedProduct.getStreak() == Award.getStreak()) {
                            double randomNum = Math.random() * 100;
                            if (randomNum <= Award.getRate()) {
                                // check still remain budget
                                if (streakSelectedProduct.isEnoughBudget()) {
                                    award.setGiven(true);
                                }
                            }

                            streakSelectedProduct.reset();
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

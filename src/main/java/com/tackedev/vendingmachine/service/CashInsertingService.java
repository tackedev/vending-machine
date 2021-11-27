package com.tackedev.vendingmachine.service;

import com.tackedev.vendingmachine.dto.Cash;
import com.tackedev.vendingmachine.dto.Money;

/**
 * @author tackedev
 */
public class CashInsertingService implements Service {

    private final Cash currentCash;

    public CashInsertingService() {
        currentCash = Cash.getInstance();
    }

    @Override
    public void execute(char input) throws CanceledRequestException, FinishedStepException {

        switch (input) {
            case '1':
                currentCash.increaseAmount(Money.TEN_THOUSAND_VND.getValue());
                break;
            case '2':
                currentCash.increaseAmount(Money.TWENTY_THOUSAND_VND.getValue());
                break;
            case '3':
                currentCash.increaseAmount(Money.FIFTY_THOUSAND_VND.getValue());
                break;
            case '4':
                currentCash.increaseAmount(Money.ONE_HUNDRED_THOUSAND_VND.getValue());
                break;
            case '5':
                currentCash.increaseAmount(Money.TWO_HUNDRED_THOUSAND_VND.getValue());
                break;
            case 'c': case 'C':
                int refundCash = currentCash.refund();
                throw new CanceledRequestException(refundCash);
            case 'n': case 'N':
                throw new FinishedStepException();
        }

    }

}

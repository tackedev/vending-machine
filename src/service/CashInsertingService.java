package service;

import dto.Money;
import dto.Wallet;

/**
 * @author tackedev
 */
public class CashInsertingService implements Service {

    @Override
    public void execute(char input) throws CanceledRequestException, FinishedStepException {

        switch (input) {
            case '1':
                Wallet.amount += Money.TEN_THOUSAND_VND.getValue();
                break;
            case '2':
                Wallet.amount += Money.TWENTY_THOUSAND_VND.getValue();
                break;
            case '3':
                Wallet.amount += Money.FIFTY_THOUSAND_VND.getValue();
                break;
            case '4':
                Wallet.amount += Money.ONE_HUNDRED_THOUSAND_VND.getValue();
                break;
            case '5':
                Wallet.amount += Money.TWO_HUNDRED_THOUSAND_VND.getValue();
                break;
            case 'c':
            case 'C':
                throw new CanceledRequestException();
            case 'n':
            case 'N':
                throw new FinishedStepException();
        }

    }

}

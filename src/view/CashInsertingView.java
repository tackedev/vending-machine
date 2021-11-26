package view;

import dto.Money;
import dto.Wallet;
import util.IOUtil;
import util.NumberFormatter;

import java.io.IOException;

/**
 * @author tackedev
 */
public class CashInsertingView implements View {

    @Override
    public void showMenu() throws IOException {
        IOUtil.clearConsole();
        System.out.println(
                "----- INSERT CASH -----\n" +
                "1: " + NumberFormatter.formatToVND(Money.TEN_THOUSAND_VND.getValue()) + " VND.\n" +
                "2: " + NumberFormatter.formatToVND(Money.TWENTY_THOUSAND_VND.getValue()) + " VND.\n" +
                "3: " + NumberFormatter.formatToVND(Money.FIFTY_THOUSAND_VND.getValue()) + " VND.\n" +
                "4: " + NumberFormatter.formatToVND(Money.ONE_HUNDRED_THOUSAND_VND.getValue()) + " VND.\n" +
                "5: " + NumberFormatter.formatToVND(Money.TWO_HUNDRED_THOUSAND_VND.getValue()) + " VND.\n" +
                "-----------------------\n" +
                "Current cash: " + NumberFormatter.formatToVND(Wallet.amount)
        );
    }

    @Override
    public void cancel() {
        System.out.println("Refund amount: " + NumberFormatter.formatToVND(Wallet.amount));
    }

    @Override
    public void showResult() {

    }
}

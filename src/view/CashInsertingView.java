package view;

import dto.Money;
import dto.Cash;
import service.CanceledRequestException;
import service.FinishedStepException;
import util.ConsoleUtil;
import util.NumberFormatter;

import java.io.IOException;

/**
 * @author tackedev
 */
public class CashInsertingView implements View {

    @Override
    public void showMenu() throws IOException {
        ConsoleUtil.clearConsole();
        System.out.println(
                "----- INSERT CASH -----\n" +
                "1: " + NumberFormatter.formatToVND(Money.TEN_THOUSAND_VND.getValue()) + ".\n" +
                "2: " + NumberFormatter.formatToVND(Money.TWENTY_THOUSAND_VND.getValue()) + ".\n" +
                "3: " + NumberFormatter.formatToVND(Money.FIFTY_THOUSAND_VND.getValue()) + ".\n" +
                "4: " + NumberFormatter.formatToVND(Money.ONE_HUNDRED_THOUSAND_VND.getValue()) + ".\n" +
                "5: " + NumberFormatter.formatToVND(Money.TWO_HUNDRED_THOUSAND_VND.getValue()) + ".\n" +
                "-----------------------\n" +
                "Current cash: " + NumberFormatter.formatToVND(Cash.getInstance().getAmount())
        );
    }

    @Override
    public void cancel(CanceledRequestException ex) throws IOException {
        ConsoleUtil.clearConsole();
        System.out.println("Refund cash: " + NumberFormatter.formatToVND(ex.getRefundCash()));
        System.out.print("Press any key to continue...");
        System.in.read();
    }

    @Override
    public void showResult(FinishedStepException ex) {
        throw new UnsupportedOperationException();
    }
}

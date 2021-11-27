package com.tackedev.vendingmachine.view;

import com.tackedev.vendingmachine.dto.Cash;
import com.tackedev.vendingmachine.dto.Money;
import com.tackedev.vendingmachine.service.CanceledRequestException;
import com.tackedev.vendingmachine.service.FinishedStepException;
import com.tackedev.vendingmachine.util.ConsoleUtil;
import com.tackedev.vendingmachine.util.NumberFormatter;

import java.io.IOException;

/**
 * @author tackedev
 */
public class CashInsertingView implements View {

    @Override
    public void showMenu() {
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

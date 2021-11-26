package view;

import dto.Wallet;
import util.IOUtil;

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
                "1: 10.000 VND.\n" +
                "2: 20.000 VND.\n" +
                "3: 40.000 VND.\n" +
                "4: 100.000 VND.\n" +
                "5: 200.000 VND."
        );
    }

    @Override
    public void cancel() {
        System.out.println("Refund amount: " + Wallet.amount);
    }

    @Override
    public void showResult() {

    }
}

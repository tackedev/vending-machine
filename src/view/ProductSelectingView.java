package view;

import dto.Cash;
import dto.Product;
import dto.ProductStorage;
import service.CanceledRequestException;
import service.FinishedStepException;
import util.ConsoleUtil;
import util.NumberFormatter;

import java.io.IOException;

/**
 * @author tackedev
 */
public class ProductSelectingView implements View {

    private final ProductStorage productStorage;
    private final Cash currentCash;

    public ProductSelectingView() throws IOException {
        productStorage = ProductStorage.getInstance();
        currentCash = Cash.getInstance();
    }

    @Override
    public void showMenu() throws IOException {
        ConsoleUtil.clearConsole();
        System.out.println("----- SELECT PRODUCT -----");

        for (int i = 0; i < productStorage.size(); i++) {
            System.out.printf("%2d: %-10s %s.\n", i+1, productStorage.get(i).getName(),
                    NumberFormatter.formatToVND(productStorage.get(i).getPrice()));
        }

        System.out.println("--------------------------");

        System.out.println("Current cash: " + NumberFormatter.formatToVND(currentCash.getAmount()));
    }

    @Override
    public void cancel(CanceledRequestException ex) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showResult(FinishedStepException ex) throws IOException {
        ConsoleUtil.clearConsole();
        System.out.println("----- RESULT -----");

        if (ex.getMessage() != null) {
            System.out.println(ex.getMessage());
        } else {
            Product selectedProduct = ex.getSelectedProduct();

            System.out.println("Your item: " + selectedProduct.getName());
            System.out.println("Your refund money: " + NumberFormatter.formatToVND((currentCash.getAmount())));
        }

        System.out.println("------------------");

        System.out.print("Press any key to continue...");
        System.in.read();
    }
}

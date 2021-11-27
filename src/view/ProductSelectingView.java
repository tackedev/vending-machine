package view;

import dto.Cart;
import dto.Cash;
import dto.Product;
import dto.ProductStorage;
import service.CanceledRequestException;
import service.FinishedStepException;
import util.ConsoleUtil;
import util.NumberFormatter;

import java.io.IOException;
import java.util.Map;

/**
 * @author tackedev
 */
public class ProductSelectingView implements View {

    private final ProductStorage productStorage;
    private final Cart currentCart;

    public ProductSelectingView() throws IOException {
        productStorage = ProductStorage.getInstance();
        currentCart = Cart.getInstance();
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

        System.out.print("Current cash: " + NumberFormatter.formatToVND(Cash.getInstance().getAmount()));
        System.out.print(" | ");
        System.out.println("Total: " + NumberFormatter.formatToVND(currentCart.getTotal()));
    }

    @Override
    public void cancel(CanceledRequestException ex) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showResult(FinishedStepException ex) throws IOException {
        System.out.println("----- RESULT -----");

        if (ex.getMessage() != null) {
            System.out.println(ex.getMessage());
        } else {
            Map<Product, Integer> buyedProducts = ex.getPayInfo().getBuyedProducts();

            System.out.println("Your items: ");
            System.out.println("  Item    Count   Total");

            for (var item : buyedProducts.entrySet()) {
                System.out.format("- %-10s %2d %s\n", item.getKey().getName(), item.getValue(),
                        NumberFormatter.formatToVND(item.getKey().getPrice() * item.getValue()));
            }
            System.out.println("Your refund money: " + NumberFormatter.formatToVND((ex.getPayInfo().getRefundAmount())));
        }

        System.out.println("------------------");

        System.out.print("Press any key to continue...");
        System.in.read();
    }
}

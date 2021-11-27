package com.tackedev.vendingmachine.view;

import com.tackedev.vendingmachine.dto.Award;
import com.tackedev.vendingmachine.dto.Cash;
import com.tackedev.vendingmachine.dto.Product;
import com.tackedev.vendingmachine.dto.ProductStorage;
import com.tackedev.vendingmachine.service.CanceledRequestException;
import com.tackedev.vendingmachine.service.FinishedStepException;
import com.tackedev.vendingmachine.util.ConsoleUtil;
import com.tackedev.vendingmachine.util.NumberFormatter;

import java.io.IOException;

/**
 * @author tackedev
 */
public class ProductSelectingView implements View {

    private final ProductStorage productStorage;
    private final Cash currentCash;
    private final Award award;

    public ProductSelectingView() throws IOException {
        productStorage = ProductStorage.getInstance();
        currentCash = Cash.getInstance();
        award = Award.getInstance();
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
        if (award.isGiven()) {
            System.out.println("You are given a free any product!");
        }
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

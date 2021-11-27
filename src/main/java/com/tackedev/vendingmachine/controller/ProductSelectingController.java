package com.tackedev.vendingmachine.controller;

import com.tackedev.vendingmachine.dto.ProductStorage;
import com.tackedev.vendingmachine.service.CanceledRequestException;
import com.tackedev.vendingmachine.service.FinishedStepException;
import com.tackedev.vendingmachine.service.Service;
import com.tackedev.vendingmachine.util.ConsoleUtil;
import com.tackedev.vendingmachine.view.View;

import java.io.IOException;

/**
 * @author tackedev
 */
public class ProductSelectingController implements Controller {

    private final Service service;
    private final View view;

    ProductStorage productStorage;

    public ProductSelectingController(Service service, View view) throws IOException {
        this.service = service;
        this.view = view;

        productStorage = ProductStorage.getInstance();
    }

    @Override
    public void process() throws IOException, FinishedStepException, CanceledRequestException {
        view.showMenu();

        char[] acceptedValues = new char[productStorage.size() + 2];
        acceptedValues[0] = 'C'; acceptedValues[1] = 'c';
        for (int i = 0; i < productStorage.size(); i++) {
            acceptedValues[2 + i] = Character.forDigit(i+1, 10);
        }
        char input = ConsoleUtil.getChar("Input the number (C=Cancel): ", acceptedValues);

        try {
            service.execute(input);
        } catch (FinishedStepException ex) {
            view.showResult(ex);
        }
    }
}

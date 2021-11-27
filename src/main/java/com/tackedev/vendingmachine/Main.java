package com.tackedev.vendingmachine;

import com.tackedev.vendingmachine.controller.CashInsertingController;
import com.tackedev.vendingmachine.controller.Controller;
import com.tackedev.vendingmachine.controller.ProductSelectingController;
import com.tackedev.vendingmachine.service.CanceledRequestException;
import com.tackedev.vendingmachine.service.CashInsertingService;
import com.tackedev.vendingmachine.service.FinishedStepException;
import com.tackedev.vendingmachine.service.FreeProductSelectingService;
import com.tackedev.vendingmachine.service.ProductSelectingService;
import com.tackedev.vendingmachine.service.Service;
import com.tackedev.vendingmachine.view.CashInsertingView;
import com.tackedev.vendingmachine.view.ProductSelectingView;
import com.tackedev.vendingmachine.view.View;

import java.io.IOException;

/**
 * @author tackedev
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // Init Select products function
        Service freeProductSelectingService = new FreeProductSelectingService();
        Service productSelectingService = new ProductSelectingService();
        View productSelectingView = new ProductSelectingView();
        Controller productSelectingController = new ProductSelectingController(
                productSelectingService,
                freeProductSelectingService,
                productSelectingView
        );

        // Init Insert cash function
        Service cashInsertingService = new CashInsertingService();
        View cashInsertingView = new CashInsertingView();
        Controller cashInsertingController = new CashInsertingController(
                cashInsertingService,
                cashInsertingView,
                productSelectingController
        );

        // Run application
        while (true) {
            try {
                cashInsertingController.process();
            } catch (FinishedStepException | CanceledRequestException ex) {
                ex.printStackTrace();
            }
        }
    }

}

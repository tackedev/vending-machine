package com.tackedev.vendingmachine.controller;

import com.tackedev.vendingmachine.service.CanceledRequestException;
import com.tackedev.vendingmachine.service.FinishedStepException;
import com.tackedev.vendingmachine.service.Service;
import com.tackedev.vendingmachine.util.ConsoleUtil;
import com.tackedev.vendingmachine.view.View;

import java.io.IOException;

/**
 * @author tackedev
 */
public class CashInsertingController implements Controller {

    private final Service service;
    private final View view;
    private final Controller nextController;

    public CashInsertingController(Service service, View view, Controller nextController) {
        this.service = service;
        this.view = view;
        this.nextController = nextController;
    }

    @Override
    public void process() throws IOException {
        view.showMenu();

        char input = ConsoleUtil.getChar("Input the number (C=Cancel | N=Next): ",
                '1', '2', '3', '4', '5', 'C', 'c', 'N', 'n');

        try {
            service.execute(input);
        } catch (CanceledRequestException ex) {
            view.cancel(ex);
        } catch (FinishedStepException ex) {
            while (true) {
                try {
                    nextController.process();
                } catch (CanceledRequestException | FinishedStepException exception) {
                    break;
                }
            }
        }
    }

}

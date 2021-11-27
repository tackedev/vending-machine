package com.tackedev.vendingmachine.view;

import com.tackedev.vendingmachine.service.CanceledRequestException;
import com.tackedev.vendingmachine.service.FinishedStepException;

import java.io.IOException;

/**
 * @author tackedev
 */
public interface View {

    void showMenu() throws IOException;

    void cancel(CanceledRequestException ex) throws IOException;

    void showResult(FinishedStepException ex) throws IOException;
}

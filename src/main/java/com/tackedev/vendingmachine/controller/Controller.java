package com.tackedev.vendingmachine.controller;

import com.tackedev.vendingmachine.service.CanceledRequestException;
import com.tackedev.vendingmachine.service.FinishedStepException;

import java.io.IOException;

/**
 * @author tackedev
 */
public interface Controller {

    void process() throws IOException, FinishedStepException, CanceledRequestException;

}

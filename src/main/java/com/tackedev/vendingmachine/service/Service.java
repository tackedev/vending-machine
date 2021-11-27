package com.tackedev.vendingmachine.service;

/**
 * @author tackedev
 */
public interface Service {

    void execute(char input) throws CanceledRequestException, FinishedStepException;

}

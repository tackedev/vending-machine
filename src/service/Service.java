package service;

import dto.Wallet;

/**
 * @author tackedev
 */
public interface Service {

    void execute(char input) throws CanceledRequestException, FinishedStepException;

}

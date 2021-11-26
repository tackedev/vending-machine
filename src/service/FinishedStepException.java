package service;

/**
 * @author tackedev
 */
public class FinishedStepException extends Exception {

    public FinishedStepException() {
    }

    public FinishedStepException(String message) {
        super(message);
    }
}

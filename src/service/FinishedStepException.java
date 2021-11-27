package service;

import dto.PayInfo;

/**
 * @author tackedev
 */
public class FinishedStepException extends Exception {

    private PayInfo payInfo;

    public FinishedStepException() {
    }

    public FinishedStepException(PayInfo payInfo) {
        this.payInfo = payInfo;
    }

    public FinishedStepException(String message) {
        super(message);
    }

    public PayInfo getPayInfo() {
        return payInfo;
    }
}

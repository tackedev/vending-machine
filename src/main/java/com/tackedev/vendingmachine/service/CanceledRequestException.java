package com.tackedev.vendingmachine.service;

/**
 * @author tackedev
 */
public class CanceledRequestException extends Exception {

    private int refundCash;

    public CanceledRequestException() {
    }

    public CanceledRequestException(int refundCash) {
        this.refundCash = refundCash;
    }

    public int getRefundCash() {
        return refundCash;
    }
}

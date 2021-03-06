package com.tackedev.vendingmachine.dto;

/**
 * @author tackedev
 */
public class Cash {

    private int amount;

    // singleton
    private static Cash instance;

    private Cash() {
    }

    public static Cash getInstance() {
        if (instance == null) {
            instance = new Cash();
        }
        return instance;
    }

    public void increaseAmount(int cash) {
        this.amount += cash;
    }

    public void decreaseAmount(int cash) {
        this.amount -= cash;
    }

    public int refund() {
        int result = this.amount;
        this.amount = 0;
        return result;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

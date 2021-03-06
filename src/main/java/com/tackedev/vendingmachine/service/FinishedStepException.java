package com.tackedev.vendingmachine.service;

import com.tackedev.vendingmachine.dto.Product;

/**
 * @author tackedev
 */
public class FinishedStepException extends Exception {

    private Product selectedProduct;

    public FinishedStepException() {
    }

    public FinishedStepException(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public FinishedStepException(String message) {
        super(message);
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }
}

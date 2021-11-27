package com.tackedev.vendingmachine.dto;

/**
 * @author tackedev
 */
public class StreakSelectedProduct {

    private Product oldProduct;
    private int streak;

    public StreakSelectedProduct() {
    }

    public StreakSelectedProduct(Product oldProduct, int streak) {
        this.oldProduct = oldProduct;
        this.streak = streak;
    }

    public Product getOldProduct() {
        return oldProduct;
    }

    public void setOldProduct(Product oldProduct) {
        this.oldProduct = oldProduct;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
}

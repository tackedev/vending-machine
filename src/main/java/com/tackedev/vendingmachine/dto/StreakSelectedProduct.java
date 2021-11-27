package com.tackedev.vendingmachine.dto;

/**
 * @author tackedev
 */
public class StreakSelectedProduct {

    private Product oldProduct;
    private int streak;

    private static StreakSelectedProduct instance;

    private StreakSelectedProduct() {
    }

    public static StreakSelectedProduct getInstance() {
        if (instance == null) {
            instance = new StreakSelectedProduct();
        }
        return instance;
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

    public void updateStreak(Product newProduct) {
        if (this.oldProduct == null || !this.oldProduct.equals(newProduct)) {
            this.oldProduct = newProduct;
            this.streak = 1;
        } else {
            this.streak++;
        }
    }

    public void reset() {
        this.oldProduct = null;
        this.streak = 0;
    }

}

package com.tackedev.vendingmachine.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author tackedev
 */
public class StreakSelectedProduct {

    private Product oldProduct;
    private int streak;
    private int remainBudget;
    private LocalDate lastRefillBudgetDate;

    private static StreakSelectedProduct instance;

    private StreakSelectedProduct() {
        this.remainBudget = Award.getBudget();
        this.lastRefillBudgetDate = LocalDate.now();
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

    public int getRemainBudget() {
        return remainBudget;
    }

    public void setRemainBudget(int remainBudget) {
        this.remainBudget = remainBudget;
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

    public void updateRemainBudge(Product product) {
        this.remainBudget -= product.getPrice();
    }

    public boolean isEnoughBudget() {
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.lastRefillBudgetDate)) {
            this.remainBudget = Award.getBudget();
            this.lastRefillBudgetDate = LocalDate.now();
        }
        return remainBudget > 0;
    }
}

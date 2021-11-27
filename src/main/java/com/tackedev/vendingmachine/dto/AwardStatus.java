package com.tackedev.vendingmachine.dto;

import com.tackedev.vendingmachine.config.AwardConfig;

import java.time.LocalDate;

/**
 * @author tackedev
 */
public class AwardStatus {

    private Product oldProduct;
    private int streak;
    private int remainBudget;
    private LocalDate lastRefillBudgetDate;
    private double currentRate;
    private boolean isGiven;

    // singleton
    private static AwardStatus instance;

    private AwardStatus() {
        this.remainBudget = AwardConfig.getBudget();
        this.lastRefillBudgetDate = LocalDate.now();
    }

    public static AwardStatus getInstance() {
        if (instance == null) {
            instance = new AwardStatus();
        }
        return instance;
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

            // increase win rate
            if (this.remainBudget > 0) {
                this.currentRate *= 1 + AwardConfig.getIncreasedRate();
            } else {
                this.currentRate = AwardConfig.getRate();
            }

            // refill budget
            this.remainBudget = AwardConfig.getBudget();
            this.lastRefillBudgetDate = LocalDate.now();
        }
        return remainBudget > 0;
    }

    // getter & setter

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

    public double getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }

    public boolean isGiven() {
        return isGiven;
    }

    public void setGiven(boolean given) {
        isGiven = given;
    }
}

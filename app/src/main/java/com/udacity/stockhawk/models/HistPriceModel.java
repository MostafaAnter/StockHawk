package com.udacity.stockhawk.models;

/**
 * Created by mostafa_anter on 12/15/16.
 */

public class HistPriceModel {
    private float history;
    private float price;

    public HistPriceModel(float history, float price) {
        this.history = history;
        this.price = price;
    }

    public float getHistory() {
        return history;
    }

    public void setHistory(float history) {
        this.history = history;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

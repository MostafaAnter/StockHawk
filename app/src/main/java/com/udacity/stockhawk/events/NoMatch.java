package com.udacity.stockhawk.events;

/**
 * Created by mostafa_anter on 12/15/16.
 */

public class NoMatch {
    private String message;
    private String symbol;

    public NoMatch(String message, String symbol) {
        this.message = message;
        this.symbol = symbol;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}

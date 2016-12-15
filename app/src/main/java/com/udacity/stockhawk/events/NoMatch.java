package com.udacity.stockhawk.events;

/**
 * Created by mostafa_anter on 12/15/16.
 */

public class NoMatch {
    private String message;

    public NoMatch(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.kwiatkowski.WymianaWalut.exceptions.SideFund;

public enum SideFundError {

    CURRENCY_ALREADY_EXIST("Fund with that currency already exist"),
    CURRENCY_DOESNT_EXIST("Fund with that currency doesn't exist");

    private String message;

    SideFundError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

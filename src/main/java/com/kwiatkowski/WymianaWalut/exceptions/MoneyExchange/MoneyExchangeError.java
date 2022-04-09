package com.kwiatkowski.WymianaWalut.exceptions.MoneyExchange;

public enum MoneyExchangeError {
    EXCHANGE_IS_NOT_ALLOWED("Exchange is not allowed"),
    NOT_ENOUGH_MONEY("Not enough money for exchange");

    private String message;

    MoneyExchangeError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

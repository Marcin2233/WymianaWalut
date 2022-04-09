package com.kwiatkowski.WymianaWalut.exceptions.MoneyExchange;

public class MoneyExchangeException  extends RuntimeException{

    private MoneyExchangeError moneyExchangeError;

    public MoneyExchangeException(MoneyExchangeError moneyExchangeError){
        this.moneyExchangeError = moneyExchangeError;
    }

    public MoneyExchangeError getMoneyExchangeError() {
        return moneyExchangeError;
    }
}

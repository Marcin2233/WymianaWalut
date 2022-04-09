package com.kwiatkowski.WymianaWalut.utils;

import org.springframework.stereotype.Component;

public enum CurrencyCode {
    PLN("PLN"),
    USD("USD"),
    CHF("CHF"),
    GBP("GBP");

    private String code;

    CurrencyCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

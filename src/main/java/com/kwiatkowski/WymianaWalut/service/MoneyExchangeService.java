package com.kwiatkowski.WymianaWalut.service;

import java.math.BigDecimal;

public interface MoneyExchangeService {

    void exchange(Long idUserAccount, String from, String to, BigDecimal amount);
}

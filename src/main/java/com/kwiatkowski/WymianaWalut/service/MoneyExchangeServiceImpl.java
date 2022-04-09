package com.kwiatkowski.WymianaWalut.service;

import com.kwiatkowski.WymianaWalut.exceptions.MoneyExchange.MoneyExchangeError;
import com.kwiatkowski.WymianaWalut.exceptions.MoneyExchange.MoneyExchangeException;
import com.kwiatkowski.WymianaWalut.exceptions.SideFund.SideFundError;
import com.kwiatkowski.WymianaWalut.exceptions.SideFund.SideFundException;
import com.kwiatkowski.WymianaWalut.model.SideFund;
import com.kwiatkowski.WymianaWalut.model.UserAccount;
import com.kwiatkowski.WymianaWalut.model.dto.NbpDto;
import com.kwiatkowski.WymianaWalut.utils.CurrencyCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class MoneyExchangeServiceImpl implements MoneyExchangeService{

    private final UserAccountService userAccountService;
    private final SideFundService sideFundService;
    private final NBPServiceClient nbpServiceClient;

    public MoneyExchangeServiceImpl(UserAccountService userAccountService, SideFundService sideFundService, NBPServiceClient nbpServiceClient) {
        this.userAccountService = userAccountService;
        this.sideFundService = sideFundService;
        this.nbpServiceClient = nbpServiceClient;
    }


    @Override
    @Transactional
    public void exchange(Long idUserAccount, String from, String to, BigDecimal amount) {

        if(from.equalsIgnoreCase(to) || !(CurrencyCode.USD.getCode().equalsIgnoreCase(from) || CurrencyCode.PLN.getCode().equalsIgnoreCase(from)) ||
                !(CurrencyCode.USD.getCode().equalsIgnoreCase(to) || CurrencyCode.PLN.getCode().equalsIgnoreCase(to)))
        {
            throw new MoneyExchangeException(MoneyExchangeError.EXCHANGE_IS_NOT_ALLOWED);
        }

        NbpDto exchangeRateUSD= nbpServiceClient.getExchangeRate(CurrencyCode.USD.getCode());
        BigDecimal mid = exchangeRateUSD.getRates().get(0).getMid();
        UserAccount userAccount = userAccountService.getUserAccountById(idUserAccount);
        Optional<SideFund> sideFundUSD = Optional.ofNullable(userAccount.getSideFunds().stream().filter(sideFund -> CurrencyCode.USD.equals(sideFund.getCurrencyCode())).findFirst()
                .orElseThrow(() -> new SideFundException(SideFundError.CURRENCY_DOESNT_EXIST)));
        SideFund fundUSD = sideFundUSD.get();


        // PLN -> USD   amount / mid = amountUSD
        if(CurrencyCode.PLN.getCode().equals(from)){
            validAmount(userAccount.getAmountPLN(), amount);
            BigDecimal amountUSD =  amount.divide(mid,2, RoundingMode.HALF_UP);
            fundUSD.setAmount( fundUSD.getAmount().add(amountUSD));
            userAccount.setAmountPLN( userAccount.getAmountPLN().subtract(amount));
        }
        else{  // USD -> PLN  mid * amount = amountPLN
            validAmount(fundUSD.getAmount(), amount);
            BigDecimal amountPLN = amount.multiply(mid).setScale(2, RoundingMode.HALF_UP);
            userAccount.setAmountPLN( userAccount.getAmountPLN().add(amountPLN));
            fundUSD.setAmount( fundUSD.getAmount().subtract(amount));
        }

        sideFundService.save(fundUSD);
        userAccountService.save(userAccount);
    }

    private void validAmount(BigDecimal amountFrom, BigDecimal amount) {
        if(amountFrom.compareTo(amount) < 0){
            throw new MoneyExchangeException(MoneyExchangeError.NOT_ENOUGH_MONEY);
        }
    }
}

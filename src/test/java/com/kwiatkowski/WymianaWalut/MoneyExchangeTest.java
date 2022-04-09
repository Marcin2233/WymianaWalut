package com.kwiatkowski.WymianaWalut;

import com.kwiatkowski.WymianaWalut.exceptions.MoneyExchange.MoneyExchangeException;
import com.kwiatkowski.WymianaWalut.model.dto.SideFundDto;
import com.kwiatkowski.WymianaWalut.model.dto.UserAccountDto;
import com.kwiatkowski.WymianaWalut.service.MoneyExchangeService;
import com.kwiatkowski.WymianaWalut.service.SideFundService;
import com.kwiatkowski.WymianaWalut.service.UserAccountService;
import com.kwiatkowski.WymianaWalut.utils.CurrencyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MoneyExchangeTest {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private SideFundService sideFundService;

    @Autowired
    private MoneyExchangeService  moneyExchangeService;

    @BeforeEach
    public void setup()
    {
        UserAccountDto userAccountDto =  UserAccountDto.builder()
                .name("Jacek")
                .surname("Testowy")
                .pesel("90082645518")
                .amountPLN(new BigDecimal(100.0))
                .build();

        userAccountService.addUserAccount(userAccountDto);

        SideFundDto sideFundDto = SideFundDto.builder()
                .amount(new BigDecimal(100.0))
                .currencyCode(CurrencyCode.USD)
                .build();

        sideFundService.addSideFund(sideFundDto, 0L);
    }

    @Test
    public void checkCantExchangeMoreThanYouHave()
    {
        //given
        boolean isException = false;

        //when
        try {
            moneyExchangeService.exchange(0L, CurrencyCode.PLN.getCode(), CurrencyCode.USD.getCode(), new BigDecimal(999.0));
        }
        catch(MoneyExchangeException e)
        {
            isException = true;
        }

        //then
        assertThat(isException).isTrue();
    }
}

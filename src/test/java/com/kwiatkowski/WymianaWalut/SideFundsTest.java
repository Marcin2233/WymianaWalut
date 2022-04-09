package com.kwiatkowski.WymianaWalut;

import com.kwiatkowski.WymianaWalut.exceptions.SideFund.SideFundException;
import com.kwiatkowski.WymianaWalut.exceptions.UserAccount.UserAccountException;
import com.kwiatkowski.WymianaWalut.model.SideFund;
import com.kwiatkowski.WymianaWalut.model.UserAccount;
import com.kwiatkowski.WymianaWalut.model.dto.SideFundDto;
import com.kwiatkowski.WymianaWalut.model.dto.UserAccountDto;
import com.kwiatkowski.WymianaWalut.service.SideFundService;
import com.kwiatkowski.WymianaWalut.service.UserAccountService;
import com.kwiatkowski.WymianaWalut.utils.CurrencyCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SideFundsTest {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private SideFundService sideFundService;

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
    }

    @Test
    public void checkCurrencyCodeCantRepeat()
    {
        //given
        boolean isException = false;

        SideFundDto sideFundDto = SideFundDto.builder()
                                .amount(new BigDecimal(100.0))
                                .currencyCode(CurrencyCode.USD)
                                .build();

        sideFundService.addSideFund(sideFundDto, 0L);

        //when
        SideFundDto sideFundDto2 = SideFundDto.builder()
                .amount(new BigDecimal(100.0))
                .currencyCode(CurrencyCode.USD)
                .build();

        try {
            sideFundService.addSideFund(sideFundDto2, 0L);
        }
        catch(SideFundException e)
        {
            isException = true;
        }

        //then
        assertThat(isException).isTrue();
    }
}

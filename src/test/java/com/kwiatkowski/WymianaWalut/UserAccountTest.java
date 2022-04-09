package com.kwiatkowski.WymianaWalut;

import com.kwiatkowski.WymianaWalut.exceptions.UserAccount.UserAccountException;
import com.kwiatkowski.WymianaWalut.model.UserAccount;
import com.kwiatkowski.WymianaWalut.model.dto.UserAccountDto;
import com.kwiatkowski.WymianaWalut.service.UserAccountService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserAccountTest {

    @Autowired
    private  UserAccountService userAccountService;

    @Test
    public void checkPeselCantRepeat()
    {
        //given
        boolean isException = false;

        UserAccountDto dto =  UserAccountDto.builder()
                .name("Jacek")
                .surname("Testowy")
                .pesel("90082645518")
                .amountPLN(new BigDecimal(100.0))
                .build();

        userAccountService.addUserAccount(dto);

        //when
        UserAccountDto dto2 =  UserAccountDto.builder()
                .name("Adam")
                .surname("Kowalski")
                .pesel("90082645518")
                .amountPLN(new BigDecimal(100.0))
                .build();
        try {
            userAccountService.addUserAccount(dto2);
        }
        catch(UserAccountException e)
        {
            isException = true;
        }

        //then
        assertThat(isException).isTrue();
    }
}

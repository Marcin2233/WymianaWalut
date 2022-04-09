package com.kwiatkowski.WymianaWalut.model.mappers;

import com.kwiatkowski.WymianaWalut.model.UserAccount;
import com.kwiatkowski.WymianaWalut.model.dto.SideFundDto;
import com.kwiatkowski.WymianaWalut.model.dto.UserAccountDto;
import com.kwiatkowski.WymianaWalut.service.SideFundService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAccountMapper {

    private final SideFundService sideFundService;

    public UserAccountMapper(SideFundService sideFundService) {
        this.sideFundService = sideFundService;
    }


    public UserAccount dtoToUserAccount(UserAccountDto userDto)
    {
        return  UserAccount.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .pesel(userDto.getPesel())
                .amountPLN(userDto.getAmountPLN())
                .build();

    }

    public UserAccountDto userAccountToDto(UserAccount user)
    {
        List<SideFundDto> sideFunds = sideFundService.getSideFunds(user.getId());

        return  UserAccountDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .pesel(user.getPesel())
                .amountPLN(user.getAmountPLN())
                .sideFunds(sideFunds)
                .build();

    }
}

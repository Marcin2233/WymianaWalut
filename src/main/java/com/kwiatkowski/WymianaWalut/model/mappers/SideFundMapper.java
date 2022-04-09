package com.kwiatkowski.WymianaWalut.model.mappers;

import com.kwiatkowski.WymianaWalut.model.SideFund;
import com.kwiatkowski.WymianaWalut.model.dto.SideFundDto;

public class SideFundMapper {

    public static SideFund DtoToSideFund(SideFundDto sideFundDto)
    {
        return SideFund.builder()
                .amount(sideFundDto.getAmount())
                .currencyCode(sideFundDto.getCurrencyCode())
                .build();
    }

    public static SideFundDto SideFundToDto(SideFund sideFund)
    {
        return SideFundDto.builder()
                .amount(sideFund.getAmount())
                .currencyCode(sideFund.getCurrencyCode())
                .build();
    }
}

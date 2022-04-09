package com.kwiatkowski.WymianaWalut.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class NbpDto {

    private String table;
    private String currency;
    private String code;
    private ArrayList<RatesDto> rates;
}

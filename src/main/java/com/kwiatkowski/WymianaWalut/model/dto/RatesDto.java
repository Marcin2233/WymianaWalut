package com.kwiatkowski.WymianaWalut.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class RatesDto {

    private String no;
    private Date effectiveDate;
    private BigDecimal mid;
}

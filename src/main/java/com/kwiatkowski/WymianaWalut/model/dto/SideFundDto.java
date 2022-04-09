package com.kwiatkowski.WymianaWalut.model.dto;

import com.kwiatkowski.WymianaWalut.utils.CurrencyCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@ApiModel
public class SideFundDto {

    @ApiModelProperty(example = "USD")
    @Enumerated(EnumType.STRING)
    @NotNull
    private CurrencyCode currencyCode;

    @ApiModelProperty(example = "50")
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=5, fraction=2)
    private BigDecimal amount;
}

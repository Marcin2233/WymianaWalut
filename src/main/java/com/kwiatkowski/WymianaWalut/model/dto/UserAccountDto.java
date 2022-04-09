package com.kwiatkowski.WymianaWalut.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@ApiModel
public class UserAccountDto {

    private Long id;

    @ApiModelProperty(example = "Marcin")
    @NotBlank
    private String name;

    @ApiModelProperty(example = "Kwiatkowski")
    @NotBlank
    private String surname;

    @ApiModelProperty(example = "91081414482")
    @NotBlank
    private String pesel;

    @ApiModelProperty(example = "100.0")
    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=5, fraction=2)
    private BigDecimal amountPLN;

    @ApiModelProperty(example = "[]", hidden = true)
    private List<SideFundDto> sideFunds;
}

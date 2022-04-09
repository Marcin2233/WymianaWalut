package com.kwiatkowski.WymianaWalut.model;

import com.kwiatkowski.WymianaWalut.utils.CurrencyCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "seqIdGenSideFund", initialValue = 0, allocationSize = 1)
@Getter
@Setter
@Builder
public class SideFund {

    public SideFund() {
    }

    public SideFund(Long id, CurrencyCode currencyCode, BigDecimal amount, UserAccount owner) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIdGenSideFund")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CurrencyCode currencyCode;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=5, fraction=2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_account", nullable = false)
    private UserAccount owner;

}

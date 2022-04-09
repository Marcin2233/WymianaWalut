package com.kwiatkowski.WymianaWalut.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@SequenceGenerator(name = "seqIdGenUserAccount", initialValue = 0, allocationSize = 1)
@Getter
@Setter
@Builder
public class UserAccount {

    public UserAccount() {
    }

    public UserAccount(Long id, String name, String surname, String pesel, BigDecimal amountPLN, List<SideFund> sideFunds) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.amountPLN = amountPLN;
        this.sideFunds = sideFunds;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIdGenUserAccount")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(name = "pesel", unique = true)
    private String pesel;

    @DecimalMin(value = "0.00", inclusive = false)
    @Digits(integer=5, fraction=2)
    private BigDecimal amountPLN;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<SideFund> sideFunds;
}

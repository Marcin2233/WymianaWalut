package com.kwiatkowski.WymianaWalut.repository;

import com.kwiatkowski.WymianaWalut.model.SideFund;
import com.kwiatkowski.WymianaWalut.utils.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SideFundRepository extends JpaRepository<SideFund, Long> {

    @Query("SELECT sf FROM SideFund sf where sf.owner.id = :idOwner")
    List<SideFund> findByOwner(@Param("idOwner")Long idOwner);

    @Query("SELECT sf FROM SideFund sf where sf.owner.id = :idOwner and sf.currencyCode = :currencyCode")
    SideFund existsByOwnerAndCurrencyCode(@Param("idOwner")Long idOwner, @Param("currencyCode") CurrencyCode currencyCode);
}

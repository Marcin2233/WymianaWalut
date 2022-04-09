package com.kwiatkowski.WymianaWalut.service;

import com.kwiatkowski.WymianaWalut.model.SideFund;
import com.kwiatkowski.WymianaWalut.model.dto.SideFundDto;

import java.util.List;

public interface SideFundService {

    SideFund addSideFund(SideFundDto sideFundDto, Long ownerId);
    List<SideFundDto> getSideFunds(Long idUser);
    void save(SideFund sideFund);
}

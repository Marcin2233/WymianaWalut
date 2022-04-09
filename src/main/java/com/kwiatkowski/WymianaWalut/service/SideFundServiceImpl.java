package com.kwiatkowski.WymianaWalut.service;

import com.kwiatkowski.WymianaWalut.exceptions.SideFund.SideFundError;
import com.kwiatkowski.WymianaWalut.exceptions.SideFund.SideFundException;
import com.kwiatkowski.WymianaWalut.model.SideFund;
import com.kwiatkowski.WymianaWalut.model.UserAccount;
import com.kwiatkowski.WymianaWalut.model.dto.SideFundDto;
import com.kwiatkowski.WymianaWalut.model.mappers.SideFundMapper;
import com.kwiatkowski.WymianaWalut.repository.SideFundRepository;
import com.kwiatkowski.WymianaWalut.utils.CurrencyCode;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SideFundServiceImpl implements SideFundService {

    private final SideFundRepository sideFundRepository;
    private final UserAccountService userAccountService;

    public SideFundServiceImpl(SideFundRepository sideFundRepository, @Lazy UserAccountService userAccountService) {
        this.sideFundRepository = sideFundRepository;
        this.userAccountService = userAccountService;
    }


    @Override
    public SideFund addSideFund(SideFundDto sideFundDto, Long ownerId) {
        UserAccount userAccount =  userAccountService.getUserAccountById(ownerId);
        validAddSideFunds(sideFundDto, ownerId);
        SideFund sideFund = SideFundMapper.DtoToSideFund(sideFundDto);

        sideFund.setOwner( userAccount);
        return sideFundRepository.save(sideFund);
    }

    @Override
    public List<SideFundDto> getSideFunds(Long ownerId) {
        return sideFundRepository.findByOwner(ownerId)
                .stream().map( sideFund -> SideFundMapper.SideFundToDto(sideFund)).collect(Collectors.toList());
    }

    @Override
    public void save(SideFund sideFund) {
        sideFundRepository.save(sideFund);
    }

    private void validAddSideFunds(SideFundDto sideFundDto, Long ownerId) {
        isCurrencyCodeAlreadyExists(sideFundDto, ownerId);
    }

    private void isCurrencyCodeAlreadyExists(SideFundDto sideFundDto, Long ownerId) {
        if(CurrencyCode.PLN.equals(sideFundDto.getCurrencyCode()) || sideFundRepository.existsByOwnerAndCurrencyCode(ownerId, sideFundDto.getCurrencyCode()) != null)
        {
            throw new SideFundException(SideFundError.CURRENCY_ALREADY_EXIST);
        }
    }
}

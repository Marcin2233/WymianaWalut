package com.kwiatkowski.WymianaWalut.service;

import com.kwiatkowski.WymianaWalut.exceptions.UserAccount.UserAccountError;
import com.kwiatkowski.WymianaWalut.exceptions.UserAccount.UserAccountException;
import com.kwiatkowski.WymianaWalut.model.UserAccount;
import com.kwiatkowski.WymianaWalut.model.dto.UserAccountDto;
import com.kwiatkowski.WymianaWalut.model.mappers.UserAccountMapper;
import com.kwiatkowski.WymianaWalut.repository.UserAccountRepository;
import com.kwiatkowski.WymianaWalut.utils.DateUtils;
import com.kwiatkowski.WymianaWalut.utils.PeselValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper uerAccountMapper;
    private PeselValidator peselValidator;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, SideFundService sideFundService, UserAccountMapper uerAccountMapper, PeselValidator peselValidator) {
        this.userAccountRepository = userAccountRepository;
        this.uerAccountMapper = uerAccountMapper;
        this.peselValidator = peselValidator;
    }

    @Override
    public UserAccount addUserAccount(UserAccountDto userAccountDto)
    {
        UserAccount userAccount = uerAccountMapper.dtoToUserAccount(userAccountDto);
        validAddUserAccount(userAccount);
        return  userAccountRepository.save(userAccount);
    }

    @Override
    public List<UserAccountDto> getUserAccounts() {
        return userAccountRepository.findAll().stream().map(userAccount -> uerAccountMapper.userAccountToDto(userAccount)).collect(Collectors.toList());
    }

    @Override
    public UserAccount getUserAccountById(Long idUserAccount) {
        Optional<UserAccount> userAccount = Optional.ofNullable(userAccountRepository.findById(idUserAccount)
                .orElseThrow(() -> new UserAccountException(UserAccountError.USER_DOESNT_EXISTS)));
        return userAccount.get();
    }

    @Override
    public void save(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    private void validAddUserAccount(UserAccount userAccount) {
        peselValidator.setPESEL(userAccount.getPesel());
        isPeselValid();
        isPeselAlreadyExists(userAccount.getPesel());
        isAdult(userAccount.getPesel());
    }

    private void isPeselValid() {
        if(!peselValidator.isValid())
        {
            throw new UserAccountException(UserAccountError.PESEL_IS_NOT_VALID);
        }
    }

    private void isAdult(String pesel) {
        if(!DateUtils.isAdult(peselValidator.getBirthday())) {
            throw new UserAccountException(UserAccountError.USER_IS_NOT_ADULT);
        }
    }

    private void isPeselAlreadyExists(String pesel) {
        if (userAccountRepository.existsByPesel(pesel)) {
            throw new UserAccountException(UserAccountError.PESEL_ALREADY_EXISTS);
        }
    }
}

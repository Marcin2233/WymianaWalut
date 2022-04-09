package com.kwiatkowski.WymianaWalut.service;

import com.kwiatkowski.WymianaWalut.model.UserAccount;
import com.kwiatkowski.WymianaWalut.model.dto.UserAccountDto;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {

    UserAccount addUserAccount(UserAccountDto userDto);

    List<UserAccountDto> getUserAccounts();

    UserAccount getUserAccountById(Long idUserAccount);

    void save(UserAccount userAccount);
}

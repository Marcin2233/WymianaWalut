package com.kwiatkowski.WymianaWalut.controller;

import com.kwiatkowski.WymianaWalut.model.dto.UserAccountDto;
import com.kwiatkowski.WymianaWalut.service.SideFundService;
import com.kwiatkowski.WymianaWalut.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService, SideFundService accountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping()
    public void addUserAccount(@RequestBody @Valid UserAccountDto userAccountDto)
    {
        userAccountService.addUserAccount(userAccountDto);
    }

    @GetMapping()
    public List<UserAccountDto> getUserAccounts()
    {
        return userAccountService.getUserAccounts();
    }

}

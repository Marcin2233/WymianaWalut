package com.kwiatkowski.WymianaWalut.controller;

import com.kwiatkowski.WymianaWalut.model.dto.SideFundDto;
import com.kwiatkowski.WymianaWalut.service.SideFundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funds")
public class SideFundController {

    private final SideFundService accountService;

    public SideFundController(SideFundService sideFundService) {
        this.accountService = sideFundService;
    }

    @PostMapping("/{ownerId}")
    public void addAccount(@RequestBody SideFundDto sideFundDto, @PathVariable Long ownerId)
    {
        accountService.addSideFund(sideFundDto, ownerId);
    }

    @GetMapping("/{ownerId}")
    public List<SideFundDto> getAccounts(@PathVariable Long ownerId)
    {
        return accountService.getSideFunds(ownerId);
    }
}

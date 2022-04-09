package com.kwiatkowski.WymianaWalut.controller;

import com.kwiatkowski.WymianaWalut.service.MoneyExchangeService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange")
public class MoneyExchangeController {

    private final MoneyExchangeService moneyExchangeService;

    public MoneyExchangeController(MoneyExchangeService moneyExchangeService) {
        this.moneyExchangeService = moneyExchangeService;
    }

    @PostMapping("/{idUserAccount}")
    public void exchange(@PathVariable Long idUserAccount,
                         @ApiParam(example = "PLN") @RequestParam String from,
                         @ApiParam(example = "USD") @RequestParam String to,
                         @ApiParam(example = "10")  @RequestParam BigDecimal amount)
    {
        moneyExchangeService.exchange(idUserAccount, from, to, amount);
    }
}

package com.kwiatkowski.WymianaWalut.service;

import com.kwiatkowski.WymianaWalut.model.dto.NbpDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "NBP", url = "http://api.nbp.pl/api/exchangerates/rates/a")
public interface NBPServiceClient {

    @GetMapping(value = "/{code}")
    NbpDto getExchangeRate(@PathVariable String code);

}

package com.kwiatkowski.WymianaWalut.exceptions.MoneyExchange;

import com.kwiatkowski.WymianaWalut.exceptions.ErrorInfo;
import com.kwiatkowski.WymianaWalut.exceptions.SideFund.SideFundError;
import com.kwiatkowski.WymianaWalut.exceptions.SideFund.SideFundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MoneyExchangeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handleException(MoneyExchangeException e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if(MoneyExchangeError.EXCHANGE_IS_NOT_ALLOWED.equals(e.getMoneyExchangeError())){
            httpStatus = HttpStatus.CONFLICT;
        }

        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getMoneyExchangeError().getMessage()));
    }
}

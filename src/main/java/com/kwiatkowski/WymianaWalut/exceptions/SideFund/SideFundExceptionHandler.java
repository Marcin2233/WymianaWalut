package com.kwiatkowski.WymianaWalut.exceptions.SideFund;

import com.kwiatkowski.WymianaWalut.exceptions.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SideFundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handleException(SideFundException e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if(SideFundError.CURRENCY_ALREADY_EXIST.equals(e.getSideFundError())){
            httpStatus = HttpStatus.CONFLICT;
        }

        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getSideFundError().getMessage()));
    }

}

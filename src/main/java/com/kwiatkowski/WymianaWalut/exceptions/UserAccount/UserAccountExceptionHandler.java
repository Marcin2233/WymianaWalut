package com.kwiatkowski.WymianaWalut.exceptions.UserAccount;

import com.kwiatkowski.WymianaWalut.exceptions.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserAccountExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handleException(UserAccountException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (UserAccountError.PESEL_ALREADY_EXISTS.equals(e.getUserAccountError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        else if (UserAccountError.USER_IS_NOT_ADULT.equals(e.getUserAccountError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        else if (UserAccountError.PESEL_IS_NOT_VALID.equals(e.getUserAccountError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        else if (UserAccountError.USER_DOESNT_EXISTS.equals(e.getUserAccountError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getUserAccountError().getMessage()));
    }
}

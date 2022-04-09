package com.kwiatkowski.WymianaWalut.exceptions.UserAccount;

public enum UserAccountError {

    USER_DOESNT_EXISTS("User doesnt exists"),
    PESEL_ALREADY_EXISTS("User with that PESEL already exists"),
    USER_IS_NOT_ADULT("User is not adult"),
    PESEL_IS_NOT_VALID("PESEL is not valid");

    private String message;

    UserAccountError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

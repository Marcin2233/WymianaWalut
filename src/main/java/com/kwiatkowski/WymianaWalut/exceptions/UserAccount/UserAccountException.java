package com.kwiatkowski.WymianaWalut.exceptions.UserAccount;

public class UserAccountException extends RuntimeException {

    private UserAccountError userError;

    public UserAccountException(UserAccountError userError) {
        this.userError = userError;
    }

    public UserAccountError getUserAccountError() {
        return userError;
    }
}

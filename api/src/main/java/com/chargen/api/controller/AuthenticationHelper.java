package com.chargen.api.controller;

import com.chargen.api.entity.Account;
import com.chargen.api.security.user.AccountDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthenticationHelper {

    protected AccountDetails getAccountDetails() {
        return (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected Account getAuthenticatedAccount() {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountDetails.getAccount();
    }
}

package com.yoramu.service;

import com.yoramu.model.Account;

public interface AccountService {
    Account createAccount(Account account);
    Account getLoggedInUser();
}

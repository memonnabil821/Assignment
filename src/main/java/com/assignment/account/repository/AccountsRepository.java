package com.assignment.account.repository;

import com.assignment.account.model.Account;

import java.util.List;

public interface AccountsRepository {

    public List<Account> getAccountsFromDB(String query);
}

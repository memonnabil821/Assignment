package com.assignment.account.service;

import com.assignment.account.model.Account;
import com.assignment.account.repository.AccountsRepositoryImpl;
import com.assignment.account.util.HashingNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class which contains business logic for retrieving Accounts info.
 */
@Service
public class AccountServiceImpl implements AccountService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private static final String ACCOUNT_QUERY = "SELECT * FROM account";

    @Autowired
    private AccountsRepositoryImpl accountsRepository;

    /**
     * Service method for retrieving Accounts info.
     * @return List
     */
    @Override
    public List<Account> getAccountDetailsService() {
        LOGGER.debug("getAccountDetailsService is called");
        List<Account> accounts = this.accountsRepository.getAccountsFromDB(ACCOUNT_QUERY);
        return HashingNumber.getAccountNumberHashed(accounts);
    }
}

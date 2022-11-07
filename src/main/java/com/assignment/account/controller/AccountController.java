package com.assignment.account.controller;

import com.assignment.account.model.Account;
import com.assignment.account.service.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for Accounts information retrieval.
 */
@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    /**
     * Endpoint for exposing Accounts information.
     * @return
     */
    @GetMapping("/accounts")
    public List<Account> getAccountDetails(){
        return this.accountService.getAccountDetailsService();
    }

}

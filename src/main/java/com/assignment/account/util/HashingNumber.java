package com.assignment.account.util;

import com.assignment.account.model.Account;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.stream.Collectors;

public class HashingNumber {

    private HashingNumber(){

    }

    public static List<Account> getAccountNumberHashed(List<Account> accountList){
        return accountList.stream().map(s -> {
            Account acc = new Account(s.getId(), s.getAccountType(), BCrypt.hashpw(s.getAccountNumber(),BCrypt.gensalt()));
            return acc;
        }).collect(Collectors.toList());
    }
}

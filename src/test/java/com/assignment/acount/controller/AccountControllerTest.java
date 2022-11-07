package com.assignment.acount.controller;

import com.assignment.account.controller.AccountController;
import com.assignment.account.model.Account;
import com.assignment.account.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {AccountController.class})
public class AccountControllerTest {

    @Mock
    AccountServiceImpl accountService;

    @InjectMocks
    AccountController accountController;

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAccountDetailsTest() throws Exception {
        Mockito.when(this.accountService.getAccountDetailsService()).thenReturn(this.createMockList());
        List<Account> accountList = accountController.getAccountDetails();
        assertEquals(accountList.get(0).getId(), 1);
    }

    private List<Account> createMockList(){
        List<Account> mocks = new ArrayList<>();
        Account acc = new Account(1,"current","12345");
        mocks.add(acc);
        return mocks;
    }
}

package com.assignment.acount.service;

import com.assignment.account.model.Account;
import com.assignment.account.repository.AccountsRepositoryImpl;
import com.assignment.account.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {AccountServiceImpl.class})
public class AccountServiceImplTest {

    @Mock
    AccountsRepositoryImpl accountsRepository;

    @InjectMocks
    AccountServiceImpl accountService;

    @Test
    public void getAccountDetailsServiceTest(){
        Mockito.when(accountsRepository.getAccountsFromDB(anyString())).thenReturn(this.createMockList());
        List<Account> accountList = accountService.getAccountDetailsService();
        assertEquals(accountList.get(0).getId(), 1);
    }

    private List<Account> createMockList(){
        List<Account> mocks = new ArrayList<>();
        Account acc = new Account(1,"current","12345");
        mocks.add(acc);
        return mocks;
    }
}

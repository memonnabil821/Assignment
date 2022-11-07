package com.assignment.acount.repository;

import com.assignment.account.model.Account;
import com.assignment.account.repository.AccountsRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {AccountsRepositoryImpl.class})
public class AccountRepositoryImplTest {

    @Mock
    JdbcTemplate template;

    @InjectMocks
    AccountsRepositoryImpl accountsRepository;

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAccountsFromDBTest(){
        Mockito.when(template.query(anyString(),any(RowMapper.class))).thenReturn(this.createMockList());
        List<Account> accountList = accountsRepository.getAccountsFromDB("Select query");
        assertEquals(accountList.get(0).getId(), 1);
    }

    private List<Account> createMockList(){
        List<Account> mocks = new ArrayList<>();
        Account acc = new Account(1,"current","12345");
        mocks.add(acc);
        return mocks;
    }
}

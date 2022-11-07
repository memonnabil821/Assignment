package com.assignment.search.repository;

import com.assignment.account.model.Account;
import com.assignment.search.model.Statement;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {SearchRepositoryImpl.class})
public class SearchRepositoryImplTest {

    @Mock
    JdbcTemplate template;

    @InjectMocks
    SearchRepositoryImpl searchRepository;

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void searchInDBTest() throws Exception {
        Mockito.when(template.query(anyString(),any(RowMapper.class))).thenReturn(this.createMockList());
        List<Statement> statements = searchRepository.searchInDB("Select * from statements");
        assertEquals(statements.get(0).getAccountId(), 1);
    }

    private List<Statement> createMockList(){
        List<Statement> mocks = new ArrayList<>();
        Statement statement = new Statement(1, "22.8.2022", "100");
        mocks.add(statement);
        return mocks;
    }
}

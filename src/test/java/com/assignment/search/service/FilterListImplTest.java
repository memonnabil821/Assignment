package com.assignment.search.service;

import com.assignment.search.model.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {FilterListImplTest.class})
public class FilterListImplTest {

    @InjectMocks
    FilterListImpl filterList;

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void filterByAmountTest(){
        List<Statement> statements = filterList.filterByAmount(this.createMockList(), "50", "100");
        Assertions.assertEquals(statements.get(0).getAccountId(), 1);
    }

    @Test
    public void filterByDateTest() throws ParseException {
        List<Statement> statements = filterList.filterByDate(this.createMockList(), "20.8.2022", "30.8.2022");
        Assertions.assertEquals(statements.get(0).getAccountId(), 1);
    }

    private List<Statement> createMockList(){
        List<Statement> mocks = new ArrayList<>();
        Statement statement = new Statement(1, "22.8.2022", "100");
        mocks.add(statement);
        return mocks;
    }
}

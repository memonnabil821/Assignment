package com.assignment.search.controller;

import com.assignment.search.model.Request;
import com.assignment.search.model.Statement;
import com.assignment.search.service.SearchServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {SearchControllerTest.class})
public class SearchControllerTest {

    @Mock
    SearchServiceImpl searchService;

    @Mock
    HttpServletResponse response;

    @Mock
    BindingResult result;

    @Mock
    User user;

    private static final String ACCOUNT_ID = "accountId";
    private static final String FROM_AMOUNT = "fromAmount";

    @InjectMocks
    SearchController searchController;

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void pingTest(){
        String ping = searchController.ping();
        Assertions.assertEquals(ping, "SUCCESS");
    }

    @Test
    public void getSearchResultTest1() throws ParseException {
        Mockito.when(searchService.search(Mockito.any(Request.class))).thenReturn(this.createMockList());
        List<Statement> statements = searchController.getSearchResult(this.createRequest1(), result, user, response);
        assertEquals(statements.get(0).getAccountId(), 1);
    }

    @Test
    public void getSearchResultTest2() throws ParseException {
        List<String> statements = searchController.getSearchResult(this.createRequest2(), this.createBindingResult1(), user, response);
        assertEquals(statements.get(0), "Account Id should be present in the request");
    }

    @Test
    public void getSearchResultTest3() throws ParseException {
        List<String> statements = searchController.getSearchResult(this.createRequest3(), this.createBindingResult2(), user, response);
        assertEquals(statements.get(0), "Incorrect input. Please check the input. Date should be in DD.MM.YYYY format only");
    }

    @Test
    public void getSearchResultTest4() throws ParseException {
        List<String> statements = searchController.getSearchResult(this.createRequest4(), result, this.createUser1(), response);
        assertEquals(statements.get(0), "User is not allowed to send parameters");
    }

    private Request createRequest1(){
        return new Request("1",null, null, null, null);
    }

    private Request createRequest2(){
        return new Request(null,null, null, null, null);
    }

    private Request createRequest3(){
        return new Request(null,null, null, "", null);
    }

    private Request createRequest4(){
        return new Request(null,null, null, "200", "800");
    }

    private User createUser1(){
        return new User("user", "user", AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    private BindingResult createBindingResult1(){
        BindingResult bindingResult = new BeanPropertyBindingResult(this.createRequest2(), "requestVO");
        FieldError fieldError = new FieldError(ACCOUNT_ID, ACCOUNT_ID, ACCOUNT_ID);
        bindingResult.addError(fieldError);
        return bindingResult;
    }

    private BindingResult createBindingResult2(){
        BindingResult bindingResult = new BeanPropertyBindingResult(this.createRequest3(), "requestVO");
        FieldError fieldError = new FieldError(FROM_AMOUNT, FROM_AMOUNT, FROM_AMOUNT);
        bindingResult.addError(fieldError);
        return bindingResult;
    }

    private List<Statement> createMockList(){
        List<Statement> mocks = new ArrayList<>();
        Statement statement = new Statement(1, "22.8.2022", "100");
        mocks.add(statement);
        return mocks;
    }
}

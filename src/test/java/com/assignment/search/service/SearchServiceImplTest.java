package com.assignment.search.service;

import com.assignment.search.model.Request;
import com.assignment.search.model.Statement;
import com.assignment.search.repository.SearchRepositoryImpl;
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

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.main.lazy-initialization=true",classes = {SearchServiceImplTest.class})
public class SearchServiceImplTest {

    @Mock
    SearchRepositoryImpl searchRepository;

    @Mock
    FilterListImpl filterList;

    @InjectMocks
    SearchServiceImpl searchService;

    private static final String DATE_PARSE_ERROR = "Error in Parsing by Date";
    private static final String SELECT_DATE_RANGE = "Please select proper date range";
    private static final String SELECT_AMOUNT_RANGE = "Please select proper amount range";
    private static final String DATE_20_8_2022= "20.8.2022";

    @BeforeEach
    protected void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void searchTest1() throws Exception {
        Mockito.when(searchRepository.searchInDB(Mockito.anyString())).thenReturn(this.createMockList());
        List<String> statements = searchService.search(this.createRequest1());
        assertEquals(statements.get(0), "Records not found");
    }

    @Test
    public void searchTest2() throws Exception{
        Mockito.when(searchRepository.searchInDB(Mockito.anyString())).thenReturn(this.createMockList());
        Mockito.when(filterList.filterByAmount(Mockito.anyList(), Mockito.anyString(), Mockito.anyString())).thenReturn(this.createMockList());
        List<Statement> statements = searchService.search(this.createRequest2());
        assertEquals(statements.get(0).getAccountId(), 1);
    }

    @Test
    public void searchTest3() throws Exception{
        Mockito.when(searchRepository.searchInDB(Mockito.anyString())).thenReturn(this.createMockList());
        Mockito.when(filterList.filterByDate(Mockito.anyList(), Mockito.anyString(), Mockito.anyString())).thenReturn(this.createMockList());
        List<Statement> statements = searchService.search(this.createRequest3());
        assertEquals(statements.get(0).getAccountId(), 1);
    }

    @Test
    public void searchTest4() throws Exception{
        Mockito.when(searchRepository.searchInDB(Mockito.anyString())).thenThrow(new SQLException());
        Throwable exception = Assertions.assertThrows(Exception.class,()->{searchService.search(this.createRequest1());});
        Assertions.assertEquals(exception.getMessage(), "Error in getting data from DB");
    }

    @Test
    public void searchTest5() throws Exception {
        List<String> statements = searchService.search(this.createRequest4());
        assertEquals(statements.get(0), SELECT_DATE_RANGE);
    }

    @Test
    public void searchTest6() throws Exception {
        List<String> statements = searchService.search(this.createRequest5());
        assertEquals(statements.get(0), SELECT_DATE_RANGE);
    }

    @Test
    public void searchTest7() throws Exception {
        List<String> statements = searchService.search(this.createRequest6());
        assertEquals(statements.get(0), SELECT_AMOUNT_RANGE);
    }
    @Test
    public void searchTest8() throws Exception {
        List<String> statements = searchService.search(this.createRequest7());
        assertEquals(statements.get(0), SELECT_AMOUNT_RANGE);
    }

    @Test
    public void searchTest9() throws Exception {
        Mockito.when(searchRepository.searchInDB(Mockito.anyString())).thenReturn(this.createMockList());
        Mockito.when(filterList.filterByDate(Mockito.anyList(), Mockito.anyString(), Mockito.anyString())).thenThrow(new ParseException(DATE_PARSE_ERROR,0));
        Throwable exception = Assertions.assertThrows(Exception.class,()->{searchService.search(this.createRequest1());});
        Assertions.assertEquals(exception.getMessage(), DATE_PARSE_ERROR);
    }

    @Test
    public void searchTest10() throws Exception {
        Mockito.when(searchRepository.searchInDB(Mockito.anyString())).thenReturn(this.createMockList());
        Mockito.when(filterList.filterByDate(Mockito.anyList(), Mockito.anyString(), Mockito.anyString())).thenThrow(new ParseException(DATE_PARSE_ERROR,0));
        Throwable exception = Assertions.assertThrows(Exception.class,()->{searchService.search(this.createRequest3());});
        Assertions.assertEquals(exception.getMessage(), DATE_PARSE_ERROR);
    }

    @Test
    public void searchTest11() throws Exception {
        List<String> statements = searchService.search(this.createRequest8());
        assertEquals(statements.get(0), SELECT_DATE_RANGE);
    }

    @Test
    public void searchTest12() throws Exception {
        List<String> statements = searchService.search(this.createRequest9());
        assertEquals(statements.get(0), SELECT_DATE_RANGE);
    }

    @Test
    public void searchTest13() throws Exception {
        List<String> statements = searchService.search(this.createRequest10());
        assertEquals(statements.get(0), SELECT_AMOUNT_RANGE);
    }
    @Test
    public void searchTest14() throws Exception {
        List<String> statements = searchService.search(this.createRequest11());
        assertEquals(statements.get(0), SELECT_AMOUNT_RANGE);
    }

    private Request createRequest1(){
        return new Request("1",null, null, null, null);
    }
    private Request createRequest2(){
        return new Request("1",null, null, "50", "150");
    }
    private Request createRequest3(){
        return new Request("1",DATE_20_8_2022, "30.8.2022", null, null);
    }
    private Request createRequest4(){
        return new Request("1","", null, null, null);
    }
    private Request createRequest5(){
        return new Request("1",null, "", null, null);
    }
    private Request createRequest6(){
        return new Request("1",null, null, "", null);
    }
    private Request createRequest7(){
        return new Request("1",null, null, null, "");
    }
    private Request createRequest8(){
        return new Request("1",DATE_20_8_2022, null, null, null);
    }
    private Request createRequest9(){
        return new Request("1",null, DATE_20_8_2022, null, null);
    }
    private Request createRequest10(){
        return new Request("1",null, null, "50", null);
    }
    private Request createRequest11(){
        return new Request("1",null, null, null, "150");
    }

    private List<Statement> createMockList(){
        List<Statement> mocks = new ArrayList<>();
        Statement statement = new Statement(1, "22.8.2022", "100");
        mocks.add(statement);
        return mocks;
    }
}

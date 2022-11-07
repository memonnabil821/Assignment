package com.assignment.search.service;

import com.assignment.search.model.Request;
import com.assignment.search.model.Statement;
import com.assignment.search.repository.SearchRepositoryImpl;
import com.assignment.search.util.InputValidatorImpl;
import com.assignment.search.util.SearchConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Service class for all business logic
 */
@Service
public class SearchServiceImpl implements SearchService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private SearchRepositoryImpl searchRepository;

    @Autowired
    private FilterListImpl filterList;

    /**
     * Main service class method to perform logic.
     * @param requestVO
     * @return List
     */
    @Override
    public List search(Request requestVO) {
        LOGGER.debug("Search service is called");
        List<Statement> statementList;
        List<String> criteriaList = InputValidatorImpl.validateInput(requestVO);
        if(criteriaList.contains(SearchConstants.AMOUNT_ERROR)){
            return Arrays.asList(SearchConstants.AMOUNT_ERROR);
        } else if(criteriaList.contains(SearchConstants.DATE_ERROR)){
            return Arrays.asList(SearchConstants.DATE_ERROR);
        }
        try {
            statementList = this.searchRepository.searchInDB(this.generateQuery(requestVO));
        } catch (Exception e) {
            LOGGER.error("Error in getting data from DB: ", e);
            throw new RuntimeException("Error in getting data from DB");
        }
        if(criteriaList.contains(SearchConstants.NO_CRITERIA)){
            requestVO.setToDate(LocalDate.now().format(DateTimeFormatter.ofPattern(SearchConstants.DD_MM_YYYY)).toString());
            requestVO.setFromDate(LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern(SearchConstants.DD_MM_YYYY)).toString());
            try {
                statementList = this.filterList.filterByDate(statementList, LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern(SearchConstants.DD_MM_YYYY)).toString(),
                        LocalDate.now().format(DateTimeFormatter.ofPattern(SearchConstants.DD_MM_YYYY)).toString());
            } catch (ParseException e){
                LOGGER.error("Error in Parsing by Date: ", e);
                throw new RuntimeException("Error in Parsing by Date");
            }
        } else{
            if(criteriaList.contains(SearchConstants.AMOUNT)){
                statementList = this.filterList.filterByAmount(statementList, requestVO.getFromAmount(), requestVO.getToAmount());
            }
            if(criteriaList.contains((SearchConstants.DATE))){
                try {
                    statementList = this.filterList.filterByDate(statementList, requestVO.getFromDate(), requestVO.getToDate());
                } catch (ParseException e){
                    LOGGER.error("Error in Parsing by Date: ", e);
                    throw new RuntimeException("Error in Parsing by Date");
                }
            }
        }
        if(statementList.isEmpty()){
            return Arrays.asList("Records not found");
        }
        return statementList;
    }

    /**
     * Returns query to execute
     * @param requestVO
     * @return String
     */
    private String generateQuery(Request requestVO){
        String query = "SELECT * from statement WHERE account_id="+requestVO.getAccountId();
        return  query;
    }
}

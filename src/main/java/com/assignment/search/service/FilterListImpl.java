package com.assignment.search.service;

import com.assignment.search.model.Request;
import com.assignment.search.model.Statement;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class to filter the result
 */
@Service
public class FilterListImpl implements FilterList{

    /**
     * Method to filter the result by amount.
     * @param accountList
     * @param fromAmount
     * @param toAmount
     * @return List<Statement>
     */
    @Override
    public List<Statement> filterByAmount(List<Statement> accountList, String fromAmount, String toAmount) {
        return accountList.stream().filter(s -> (Float.parseFloat(fromAmount) <= Float.parseFloat(s.getAmount()))
                && (Float.parseFloat(s.getAmount()) <= Float.parseFloat(toAmount))).collect(Collectors.toList());
    }

    /**
     * Method to filter the result by date.
     * @param accountList
     * @param fromDate
     * @param toDate
     * @return List<Statement>
     * @throws ParseException
     */
    @Override
    public List<Statement> filterByDate(List<Statement> accountList, String fromDate, String toDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return accountList.stream().filter(s -> {
            try {
                return !(formatter.parse(s.getDateField()).before(formatter.parse(fromDate))
                        || formatter.parse(s.getDateField()).after(formatter.parse(toDate)));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}

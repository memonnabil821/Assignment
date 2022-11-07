package com.assignment.search.service;

import com.assignment.search.model.Request;
import com.assignment.search.model.Statement;

import java.text.ParseException;
import java.util.List;

public interface FilterList {

    abstract List<Statement> filterByAmount(List<Statement> accountList, String fromAmount, String toAmount);

    abstract List<Statement> filterByDate(List<Statement> accountList, String fromDate, String toDate) throws ParseException;

}

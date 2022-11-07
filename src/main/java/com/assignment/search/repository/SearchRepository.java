package com.assignment.search.repository;

import com.assignment.search.model.Statement;

import java.sql.SQLException;
import java.util.List;

public interface SearchRepository {

    public List<Statement> searchInDB(String query) throws SQLException;
}

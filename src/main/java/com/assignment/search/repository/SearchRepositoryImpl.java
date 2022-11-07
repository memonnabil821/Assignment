package com.assignment.search.repository;

import com.assignment.search.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository class to perform database operation.
 */
@Repository
public class SearchRepositoryImpl implements SearchRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchRepositoryImpl.class);

    @Autowired
    private JdbcTemplate template;

    /**
     * Method to make the database call
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<Statement> searchInDB(String query) throws SQLException{
        LOGGER.debug("SearchInDB method is called");
        List<Statement> statementList = this.template.query(query, new RowMapper<Statement>() {
            @Override
            public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Statement((int)Float.parseFloat(rs.getString("account_id")), rs.getString("datefield"),rs.getString("amount"));
            }
        });
        return statementList;
    }
}

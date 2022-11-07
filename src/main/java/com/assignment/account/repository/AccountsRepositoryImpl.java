package com.assignment.account.repository;

import com.assignment.account.model.Account;
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
 * Repository class to execute database call for retrieving Accounts info.
 */
@Repository
public class AccountsRepositoryImpl implements AccountsRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsRepositoryImpl.class);

    @Autowired
    private JdbcTemplate template;

    /**
     * Method for making database call for Accounts info.
     * @param query
     * @return List
     */
    @Override
    public List<Account> getAccountsFromDB(String query) {
        LOGGER.debug("getAccountsFromDB is called");
        List<Account> accountList = this.template.query(query, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Account(rs.getInt("ID"),rs.getString("account_type"), rs.getString("account_number"));
            }
        });
        LOGGER.debug("getAccountsFromDB completed");
        return accountList;
    }
}

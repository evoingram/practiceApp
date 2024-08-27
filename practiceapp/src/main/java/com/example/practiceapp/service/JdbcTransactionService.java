package com.example.practiceapp.service;

import com.example.practiceapp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class JdbcTransactionService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transaction> getTransactionsUsingJdbc() {
        String sql = "SELECT * FROM transactions";
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }

    private static class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getLong("id"));
            transaction.setCategory(rs.getString("category"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDate(rs.getDate("date").toLocalDate());
            // Assuming you have a User object with the transaction
            // transaction.setUser(new User(rs.getLong("user_id")));
            return transaction;
        }
    }
}

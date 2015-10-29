package com.suhorukov.dubovik.jdbcproject;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestBook implements GuestBookController, AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(GuestBook.class);
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;

    GuestBook(Connection connection) {

        try {
            this.connection = connection;
            statement = connection.createStatement();
            statement.execute("create TABLE posts(ID identity not null primary key, postDate date, postMessage varchar(255))");
            LOGGER.info("table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRecord(String message) {
        try {
            ps = connection.prepareStatement("insert into posts values(default, ?, ?)");
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setString(2, message);
            ps.executeUpdate();
            LOGGER.info("message added: " + message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> getRecords() {
        List<Record> record = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery("SELECT * from posts ORDER BY ID")) {
            while (rs.next()) {
                LOGGER.info("loaded line: " + rs.getInt("id"));
                record.add(new Record(rs.getInt("id"), rs.getDate("postDate"), rs.getString("postMessage")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    @Override
    public void close() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (ps != null)
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}

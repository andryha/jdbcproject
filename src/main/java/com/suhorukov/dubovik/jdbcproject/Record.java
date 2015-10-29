package com.suhorukov.dubovik.jdbcproject;

import java.sql.Date;

public class Record {
    private int id;
    private Date postDate;
    private String postMessage;


    public Record(int id, Date postDate, String postMessage) {
        this.id = id;
        this.postDate = postDate;
        this.postMessage = postMessage;
    }

    public int getId() {
        return id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getPostMessage() {
        return postMessage;
    }
}

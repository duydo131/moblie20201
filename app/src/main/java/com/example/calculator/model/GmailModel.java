package com.example.calculator.model;

import java.util.Date;

public class GmailModel {
    private String username;
    private String message;
    private Date date;
    private boolean favorite;

    public GmailModel(String username, String message, Date date, boolean favorite) {
        this.username = username;
        this.message = message;
        this.date = date;
        this.favorite = favorite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

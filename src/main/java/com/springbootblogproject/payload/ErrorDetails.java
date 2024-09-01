package com.springbootblogproject.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(String details, String message, Date timeStamp) {
        this.details = details;
        this.message = message;
        this.timestamp = timeStamp;
    }

    public ErrorDetails(Date date, String message, String description) {
    }

    public Date getTimeStamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }


}

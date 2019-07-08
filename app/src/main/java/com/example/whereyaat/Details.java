package com.example.whereyaat;

public class Details {

    private String name;
    private String message;
    private String messageId;

    public Details()
    {

    }

    public Details(String messageId, String name, String message) {
        this.name = name;
        this.message = message;
        this.messageId = messageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}

package com.example.whereyaat;

public class Person {
    private String personName;
    private String message;

    public Person(String personName, String message) {
        this.personName = personName;
        this.message = message;
    }

    public Person() {

    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

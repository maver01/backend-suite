package com.mycompany.app;

// Define the MyData class
public class MyData {
    private String message;

    // Getter and setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "message='" + message + '\'' +
                '}';
    }
}
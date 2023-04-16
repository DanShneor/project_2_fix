package com.example.project_2_fix.Exceptions;

public class LogInException extends Exception{
    public LogInException() {
        super("the user is not exist or incorrect");
    }
}

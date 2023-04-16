package com.example.project_2_fix.Exceptions;

public class CustomerExistException extends Exception{
    public CustomerExistException() {
        super("the customer email already exist cannot approve that operation");
    }
}


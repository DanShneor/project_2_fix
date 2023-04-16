package com.example.project_2_fix.Exceptions;

public class CompanyExistException extends Exception{
    public CompanyExistException() {
        super("the company name or email already exist cannot approve that operation");
    }
}

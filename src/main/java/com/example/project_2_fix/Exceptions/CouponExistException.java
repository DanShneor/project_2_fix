package com.example.project_2_fix.Exceptions;

public class CouponExistException extends Exception {
    public CouponExistException(){
        super("the coupon title is already taken cannot continue the operation");
    }

}

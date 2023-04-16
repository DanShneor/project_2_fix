package com.example.project_2_fix.Exceptions;

public class CompanyUpdateCouponException extends Exception{
    public CompanyUpdateCouponException() {
        super("this company does not own this coupon , cannot affirm this request");
    }
}

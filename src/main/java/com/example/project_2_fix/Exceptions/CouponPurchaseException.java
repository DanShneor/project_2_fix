package com.example.project_2_fix.Exceptions;

public class CouponPurchaseException extends Exception {
    public CouponPurchaseException() {
        super("you already have that coupon , cant buy it again");
    }
}

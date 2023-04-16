package com.example.project_2_fix.Exceptions;

public class CouponPurchaseAmountException extends Exception {
    public CouponPurchaseAmountException() {
        super("coupon's amount empty, the coupon is not available");
    }
}

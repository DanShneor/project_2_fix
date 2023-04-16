package com.example.project_2_fix.Exceptions;

public class CouponPurchaseDateException extends Exception{
    public CouponPurchaseDateException() {
        super("the coupon value expired");
    }
}

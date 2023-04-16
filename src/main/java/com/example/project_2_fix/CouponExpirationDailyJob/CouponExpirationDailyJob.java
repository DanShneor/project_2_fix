package com.example.project_2_fix.CouponExpirationDailyJob;

import com.example.project_2_fix.beans.Coupon;
import com.example.project_2_fix.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponExpirationDailyJob implements Runnable {

    private CouponRepository couponRepository;
    private boolean quit;

    public CouponExpirationDailyJob(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public void run() {
        while (!quit) {

            List<Coupon> coupons = couponRepository.findAll();
            for (Coupon c : coupons) {
                if (c.getEndDate().before(new Date(System.currentTimeMillis()))) {
                    couponRepository.deleteCustomerCouponsPurchase(c.getId());
                    couponRepository.deleteById(c.getId());
                }
            }
            try {
                Thread.sleep(858 * 100000);
            } catch (InterruptedException e) {
            }
        }
    }
    
    public synchronized void stop() throws InterruptedException {
        this.quit = true;
        Thread.sleep(1000);
    }
}

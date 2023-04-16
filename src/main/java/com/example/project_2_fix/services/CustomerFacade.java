package com.example.project_2_fix.services;

import com.example.project_2_fix.Exceptions.*;
import com.example.project_2_fix.beans.Category;
import com.example.project_2_fix.beans.Coupon;
import com.example.project_2_fix.beans.Customer;
import com.example.project_2_fix.repositories.CompanyRepository;
import com.example.project_2_fix.repositories.CouponRepository;
import com.example.project_2_fix.repositories.CustomerRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class CustomerFacade extends ClientFacade {
    private int customerId;

    public CustomerFacade(CompanyRepository companyRepository, CouponRepository couponRepository, CustomerRepository customerRepository) {
        super(companyRepository, couponRepository, customerRepository);
    }

    @Override
    public boolean logIn(String email, String password) throws LogInException {
        Customer customer = customerRepository.findByEmailAndPassword(email, password);
        if (customer.getId() > 0) {
            customerId = customer.getId();
            return true;
        } else {
            throw new LogInException();
        }
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public void purchaseCoupon(Coupon coupon) throws CouponPurchaseException, CouponPurchaseAmountException, CouponPurchaseDateException, NotFoundException {
        boolean there = false;
        Set<Coupon> couponSet = getAllCustomerCoupons();
        for (Coupon c : couponSet) {
            if (c.getId() == coupon.getId()) {
                there = true;
                break;
            }
        }
        if (there) {
            throw new CouponPurchaseException();
        }
        if (coupon.getAmount() <= 0)
            throw new CouponPurchaseAmountException();
        if (coupon.getEndDate().before(new Date(System.currentTimeMillis())))
            throw new CouponPurchaseDateException();
        else {
            coupon.setAmount(coupon.getAmount() - 1);
            couponRepository.save(coupon);
            Customer customer = customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
            customer.getCoupons().add(coupon);
            customerRepository.save(customer);
        }
    }

    public Set<Coupon> getAllCustomerCoupons() {
        return customerRepository.findById(customerId).get().getCoupons();
    }

    public Set<Coupon> getAllCustomerCouponsByMaxPrice(double price) {
        Set<Coupon> coupons = getAllCustomerCoupons();
        return coupons.stream().filter(coupon -> coupon.getPrice() <= price).collect(Collectors.toSet());
    }

    public Set<Coupon> getAllCustomerCouponsByCategory(Category category) {
        Set<Coupon> coupons = getAllCustomerCoupons();
        return coupons.stream().filter(coupon -> coupon.getCategory().equals(category)).collect(Collectors.toSet());
    }

    public Customer getCustomerDetails() throws NotFoundException {
        return customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
    }


}

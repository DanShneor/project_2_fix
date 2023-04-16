package com.example.project_2_fix.services;

import com.example.project_2_fix.Exceptions.LogInException;
import com.example.project_2_fix.repositories.CompanyRepository;
import com.example.project_2_fix.repositories.CouponRepository;
import com.example.project_2_fix.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientFacade {
    protected CompanyRepository companyRepository;
    protected CouponRepository couponRepository;
    protected CustomerRepository customerRepository;

    public ClientFacade(CompanyRepository companyRepository, CouponRepository couponRepository, CustomerRepository customerRepository) {
        this.companyRepository = companyRepository;
        this.couponRepository = couponRepository;
        this.customerRepository = customerRepository;
    }

    public abstract boolean logIn(String email, String password) throws LogInException;
}

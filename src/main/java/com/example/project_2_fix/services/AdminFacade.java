package com.example.project_2_fix.services;

import com.example.project_2_fix.Exceptions.CompanyExistException;
import com.example.project_2_fix.Exceptions.CustomerExistException;
import com.example.project_2_fix.Exceptions.LogInException;
import com.example.project_2_fix.Exceptions.NotFoundException;
import com.example.project_2_fix.beans.Company;
import com.example.project_2_fix.beans.Coupon;
import com.example.project_2_fix.beans.Customer;
import com.example.project_2_fix.repositories.CompanyRepository;
import com.example.project_2_fix.repositories.CouponRepository;
import com.example.project_2_fix.repositories.CustomerRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Scope("prototype")
public class AdminFacade extends ClientFacade {


    public AdminFacade(CompanyRepository companyRepository, CouponRepository couponRepository,
                       CustomerRepository customerRepository) {
        super(companyRepository, couponRepository, customerRepository);
    }

    public boolean logIn(String email, String password) throws LogInException {
        if (email.equals("admin@admin.com") && password.equals("admin"))
            return true;
        else
            throw new LogInException();
    }

    public void addCompany(Company company) throws CompanyExistException {
        if (companyRepository.existsById(company.getId())) {
            throw new CompanyExistException();
        } else {
            companyRepository.save(company);
        }
    }

        public void updateCompany (Company company) throws NotFoundException {
            if (companyRepository.existsById(company.getId()))
                companyRepository.save(company);
            else
                throw new NotFoundException();
        }

        public Company getOneCompany ( int companyId) throws NotFoundException {
            return companyRepository.findById(companyId).orElseThrow(NotFoundException::new);
        }

        public List<Company> getAllCompanies () {
            return companyRepository.findAll();
        }

        public void addCustomer (Customer customer) throws CustomerExistException {
            if (customerRepository.existsById(customer.getId()))
                throw new CustomerExistException();
            else
                customerRepository.save(customer);
        }

        public void updateCustomer (Customer customer) throws NotFoundException {
            if (customerRepository.existsById(customer.getId()))
                customerRepository.save(customer);
            else
                throw new NotFoundException();
        }

        public Customer getOneCustomer ( int customerId) throws NotFoundException {
            return customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
        }

        public List<Customer> getAllCustomers () {
            return customerRepository.findAll();
        }
        public void deleteCompany ( int companyId){
            companyRepository.deleteCompanyCouponsPurchaseByCompany(companyId);
            companyRepository.deleteById(companyId);
        }
        public void deleteCustomer ( int customerId){
            Set<Coupon> coupons = customerRepository.findById(customerId).get().getCoupons();
            for (Coupon c : coupons) {
                c.setAmount(c.getAmount() - 1);
                couponRepository.save(c);
            }
            customerRepository.deleteCustomerCouponsPurchase(customerId);
            customerRepository.deleteById(customerId);
        }


    }

package com.example.project_2_fix.services;

import com.example.project_2_fix.Exceptions.CompanyUpdateCouponException;
import com.example.project_2_fix.Exceptions.CouponExistException;
import com.example.project_2_fix.Exceptions.LogInException;
import com.example.project_2_fix.Exceptions.NotFoundException;
import com.example.project_2_fix.beans.Company;
import com.example.project_2_fix.beans.Coupon;
import com.example.project_2_fix.repositories.CompanyRepository;
import com.example.project_2_fix.repositories.CouponRepository;
import com.example.project_2_fix.repositories.CustomerRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Scope("prototype")
public class CompanyFacade extends ClientFacade {
    private int companyId;

    public CompanyFacade(CompanyRepository companyRepository, CouponRepository couponRepository, CustomerRepository customerRepository) {
        super(companyRepository, couponRepository, customerRepository);
    }


    @Override
    public boolean logIn(String email, String password) throws LogInException {
        Company comp = companyRepository.findByEmailAndPassword(email, password);
        if (comp.getId()>0) {
            companyId = comp.getId();
            return true;
        } else
            throw new LogInException();
    }


    public void addCoupon(Coupon coupon) throws CouponExistException {
        if (!couponRepository.existsById(coupon.getId()))
            couponRepository.save(coupon);

        else
            throw new CouponExistException();
    }

    public void updateCoupon(Coupon coupon) throws NotFoundException, CompanyUpdateCouponException {
        if (coupon.getCompany().getId() == companyId) {
            if (couponRepository.existsById(coupon.getId()))
                couponRepository.save(coupon);
            else
                throw new NotFoundException();
        } else
            throw new CompanyUpdateCouponException();
    }

    public Coupon getOneCoupon(int couponId) throws NotFoundException {
        return couponRepository.findById(couponId).orElseThrow(NotFoundException::new);
    }

    public List<Coupon> getAllCoupon() {
        return couponRepository.findAll();
    }

    public Company getCompanyDetails() throws NotFoundException {
        return companyRepository.findById(companyId).orElseThrow(NotFoundException::new);
    }

    public void deleteCoupon(int couponId) throws NotFoundException {
        companyRepository.deleteCompanyCouponsPurchase(couponId);
        Company companyTemp = getCompanyDetails();
        Set<Coupon> coupons = companyTemp.getCoupons();
        coupons.remove(couponRepository.findById(couponId).orElseThrow(NotFoundException::new));
        companyRepository.save(companyTemp);
        couponRepository.deleteById(couponId);
    }
}

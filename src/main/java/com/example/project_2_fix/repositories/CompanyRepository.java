package com.example.project_2_fix.repositories;

import com.example.project_2_fix.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company,Integer> {
//    default int findIdByEmailAndPassword(String email,String password){
//        return 0;
//    }
    Company findByEmailAndPassword(String email,String password);

    @Modifying
    @Query(value = "delete from customers_coupons where coupons_id=?1",nativeQuery = true)
    void deleteCompanyCouponsPurchase(int couponId);
    @Modifying
    @Query(value = "delete from customers_coupons where coupons_id in (select id from coupons where company_id=?1)",nativeQuery = true)
    void deleteCompanyCouponsPurchaseByCompany(int companyId);
}

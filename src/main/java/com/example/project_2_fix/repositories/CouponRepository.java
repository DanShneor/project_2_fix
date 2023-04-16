package com.example.project_2_fix.repositories;

import com.example.project_2_fix.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    @Modifying
    @Query(value = "delete from customers_coupons where coupons_id=?1",nativeQuery = true)
    void deleteCustomerCouponsPurchase(int couponId);
}

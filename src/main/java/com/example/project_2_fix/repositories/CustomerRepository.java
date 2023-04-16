package com.example.project_2_fix.repositories;

import com.example.project_2_fix.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
//   default int findIdByEmailAndPassword(String email, String password){
//       return 0;
//   }

    Customer findByEmailAndPassword(String email,String password);

    @Modifying
    @Query(value = "delete from customers_coupons where customer_id=?1",nativeQuery = true)
    void deleteCustomerCouponsPurchase(int customerId);
}

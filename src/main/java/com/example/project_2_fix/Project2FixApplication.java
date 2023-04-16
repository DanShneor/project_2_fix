package com.example.project_2_fix;

import com.example.project_2_fix.CouponExpirationDailyJob.CouponExpirationDailyJob;
import com.example.project_2_fix.Exceptions.LogInException;
import com.example.project_2_fix.LoginManager.ClientType;
import com.example.project_2_fix.LoginManager.LoginManager;
import com.example.project_2_fix.beans.Category;
import com.example.project_2_fix.beans.Company;
import com.example.project_2_fix.beans.Coupon;
import com.example.project_2_fix.services.AdminFacade;
import com.example.project_2_fix.services.CompanyFacade;
import com.example.project_2_fix.services.CustomerFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class Project2FixApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Project2FixApplication.class, args);
		LoginManager loginManager = ctx.getBean(LoginManager.class);
		CouponExpirationDailyJob job = ctx.getBean(CouponExpirationDailyJob.class);
		Thread t1 = new Thread(job);
		try {
			t1.start();
			AdminFacade admin = (AdminFacade) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
//			System.out.println(admin.getAllCompanies());
//			System.out.println(admin.getAllCustomers());
//			System.out.println(admin.getOneCompany(1));
//			System.out.println(admin.getOneCustomer(1));
//			admin.deleteCustomer(1);
//			Company comp = new Company("test5","test5@","test5");
//			admin.addCompany(comp);
//			admin.deleteCompany(6);
//			Company comp = admin.getOneCompany(3);
//			comp.setEmail("test3@");
//			admin.updateCompany(comp);
			CompanyFacade company = (CompanyFacade) loginManager.login("test4@gmail","test4",ClientType.Company);
//			System.out.println(company.getAllCoupon());
//			System.out.println(company.getCompanyDetails());
//			System.out.println(company.getOneCoupon(18));
//			Company comp = company.getCompanyDetails();
//			Coupon coupon = new Coupon(comp, Category.food,"meal","good meal",Date.valueOf("2023-08-08"),Date.valueOf("2023-09-09"),
//					40,29.90,"image");
//			company.addCoupon(coupon);
//			company.deleteCoupon(18);
//			Coupon coupon = company.getOneCoupon(19);
//			coupon.setCategory(Category.extremeActivities);
//			company.updateCoupon(coupon);
			CustomerFacade customer = (CustomerFacade) loginManager.login("test3@gmail","test3",ClientType.Customer);
//			System.out.println(customer.getAllCustomerCoupons());
//			Coupon coupon = company.getOneCoupon(17);
//			customer.purchaseCoupon(coupon);
//			System.out.println(customer.getAllCustomerCouponsByCategory(Category.extremeActivities));
//			System.out.println(customer.getAllCustomerCouponsByMaxPrice(25));
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
				job.stop();
				t1.interrupt();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}

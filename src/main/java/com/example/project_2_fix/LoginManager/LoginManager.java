package com.example.project_2_fix.LoginManager;

import com.example.project_2_fix.Exceptions.LogInException;
import com.example.project_2_fix.beans.Company;
import com.example.project_2_fix.repositories.CompanyRepository;
import com.example.project_2_fix.repositories.CouponRepository;
import com.example.project_2_fix.repositories.CustomerRepository;
import com.example.project_2_fix.services.AdminFacade;
import com.example.project_2_fix.services.ClientFacade;
import com.example.project_2_fix.services.CompanyFacade;
import com.example.project_2_fix.services.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

@Service
public class LoginManager {
    private ApplicationContext ctx;

    public LoginManager(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws LogInException {
        if(clientType.equals(ClientType.Administrator)){
            AdminFacade admin = ctx.getBean(AdminFacade.class);
            if (admin.logIn(email, password))
                return admin;
        }
        if (clientType.equals(ClientType.Company)){
            CompanyFacade company =ctx.getBean(CompanyFacade.class);
            if (company.logIn(email, password))
                return company;
        }
        if (clientType.equals(ClientType.Customer)){
            CustomerFacade customer = ctx.getBean(CustomerFacade.class);
            if (customer.logIn(email, password))
                return customer;
        }
        throw new LogInException();
    }
}

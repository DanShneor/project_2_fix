package com.example.project_2_fix.beans;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name , email, password;
    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER , cascade = CascadeType.REMOVE)
    private Set<Coupon> coupons;

    public Company() {
    }

    public Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Company(String name, String email, String password, Set<Coupon> coupons) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = coupons;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Coupon> getCoupons() {
        return coupons;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}';
    }
}

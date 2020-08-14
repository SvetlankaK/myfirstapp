package com.svetakvetko.domain;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class User {
    @Size(min = 5, max = 15)
    @NotEmpty
    private String userLogin;//TODO primary key in db

    @Size(min = 5, max = 15)
    @NotEmpty
    private String password;

    private List<Role> role;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String surname;

    private double salary;

    @NotEmpty
    private String dateOfBirth;


    private long userId;


    public User() {

    }

    public User(long userId, String userLogin, String password, List<Role> role) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.password = password;
        this.role = new ArrayList<>();
        if (role != null) {
            this.role.addAll(role);
        }
    }

    public User(long userId, String userLogin, String password, List<Role> role, String email, String name, String surname, double salary, String dateOfBirth) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.password = password;
        this.role = new ArrayList<>();
        if (role != null) {
            this.role.addAll(role);
        }
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
    }

    public void setAll(String password, List<Role> role, String email, String name, String surname, Double salary, String dateOfBirth) {
        this.role = new ArrayList<>();
        if (role != null) {
            this.role.addAll(role);
        }
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.dateOfBirth = dateOfBirth;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}

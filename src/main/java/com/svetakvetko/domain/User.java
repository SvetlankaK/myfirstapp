package com.svetakvetko.domain;


import com.svetakvetko.validation.AuthorizationGroup;
import com.svetakvetko.validation.EditInfoGroup;
import com.svetakvetko.validation.RegistrationGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class User {

    @Size(min = 5, max = 15, message = "{user.login.size}")
    @NotEmpty(message = "{user.login.empty}", groups = {AuthorizationGroup.class, RegistrationGroup.class, EditInfoGroup.class})
    private String userLogin;//TODO primary key in db

    @Size(min = 5, max = 15, message = "{user.password.size}")
    @NotEmpty(message = "{user.password.empty}", groups = {AuthorizationGroup.class, RegistrationGroup.class, EditInfoGroup.class})
    private String password;

    private List<Role> role;

    @NotEmpty(message = "{user.email.empty}", groups = {RegistrationGroup.class, EditInfoGroup.class})
    @Email
    private String email;

    @NotEmpty(message = "{user.name.empty}", groups = {RegistrationGroup.class, EditInfoGroup.class})
    @Size(min = 3, max = 15, message = "{user.name.size}")
    private String name;

    @NotEmpty(message = "{user.surname.empty}", groups = {RegistrationGroup.class, EditInfoGroup.class})
    @Size(min = 3, max = 15, message = "{user.surname.size}")
    private String surname;

    @NotNull(message = "{user.salary.empty}", groups = EditInfoGroup.class)
    private double salary;

    @NotEmpty(message = "{user.birthday.empty}", groups = {RegistrationGroup.class, EditInfoGroup.class})
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

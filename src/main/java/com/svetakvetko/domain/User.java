package com.svetakvetko.domain;


import com.svetakvetko.validation.AuthorizationGroup;
import com.svetakvetko.validation.EditInfoGroup;
import com.svetakvetko.validation.EditPasswordGroup;
import com.svetakvetko.validation.RegistrationGroup;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class User implements UserDetails {

    @Size(min = 5, max = 15, groups = {RegistrationGroup.class, EditInfoGroup.class})
    @NotEmpty(groups = {AuthorizationGroup.class, RegistrationGroup.class, EditInfoGroup.class})
    private String userLogin;

    @Size(min = 5, max = 15, groups = {RegistrationGroup.class, EditInfoGroup.class})
    @NotEmpty(groups = {AuthorizationGroup.class, RegistrationGroup.class, EditPasswordGroup.class})
    private String password;

    private List<Role> role;

    @NotEmpty(groups = {RegistrationGroup.class, EditInfoGroup.class})
    @Email
    private String email;

    @NotEmpty(groups = {RegistrationGroup.class, EditInfoGroup.class})
    @Size(min = 3, max = 15, groups = {RegistrationGroup.class, EditInfoGroup.class})
    private String name;

    @NotEmpty(groups = {RegistrationGroup.class, EditInfoGroup.class})
    @Size(min = 3, max = 15, groups = {RegistrationGroup.class, EditInfoGroup.class})
    private String surname;

    @NotNull(groups = {RegistrationGroup.class, EditInfoGroup.class})
    private double salary;

    @NotEmpty(groups = {RegistrationGroup.class, EditInfoGroup.class})
    private String dateOfBirth;


    private long userId;


    public User() {

    }

    public User(String userLogin, String password, List<Role> role) {

        this.userLogin = userLogin;
        this.password = password;
        this.role = new ArrayList<>();
        if (role != null) {
            this.role.addAll(role);
        }
    }

    public User(String userLogin, String password, List<Role> role, String email, String name, String surname, double salary, String dateOfBirth) {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

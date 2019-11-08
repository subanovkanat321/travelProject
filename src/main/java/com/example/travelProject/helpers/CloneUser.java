package com.example.travelProject.helpers;

import org.springframework.context.annotation.Bean;

public class CloneUser {
    private String name;
    private String lastName;
    private String mobilePhone;
    private String email;

    @Bean
    public CloneUser cloneUser() {
        return new CloneUser();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.example.travelProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "user1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    private String mobilePhone;

    @Column(name = "email", unique = true)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @JsonIgnore
    @Column(name = "active")
    private int active;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public static class Builder {
        private String name;
        private String lastName;
        private String mobilePhone;
        private String email;
        private String password;
        private int active;
        private Set<Role> roles;

        public Builder(String email, String password, int active, Set<Role> roles) {
            this.email = email;
            this.password = password;
            this.active = active;
            this.roles = roles;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withLastame(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder withPhone(String phone) {
            this.mobilePhone = phone;
            return this;
        }
        public User build() {
            User user = new User();
            user.name = this.name;
            user.lastName = this.lastName;
            user.mobilePhone = this.mobilePhone;
            user.email = this.email;
            user.password = this.password;
            user.active = this.active;
            user.roles = this.roles;
            return user;
        }
    }
}

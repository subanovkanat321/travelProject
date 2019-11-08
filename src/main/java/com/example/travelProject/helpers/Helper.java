package com.example.travelProject.helpers;

import com.example.travelProject.model.User;
import com.example.travelProject.repository.UserRep;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class Helper {
    private final UserRep userRep;

    public Helper(UserRep userRep) {
        this.userRep = userRep;
    }

    public User getUser() {
        return userRep.findByEmail(getUserName());
    }

    private String getUserName() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public CloneUser getCloneUser(User user) {
        CloneUser cloneUser = new CloneUser();
        cloneUser.setEmail(user.getEmail());
        cloneUser.setName(user.getName());
        cloneUser.setLastName(user.getLastName());
        cloneUser.setMobilePhone(user.getMobilePhone());
        return cloneUser;
    }
}

package com.example.travelProject.helpers;

import com.example.travelProject.model.User;
import com.example.travelProject.repository.CloneUserRep;
import com.example.travelProject.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class Helper {
    @Autowired
    private UserRep userRep;

    @Autowired
    private CloneUserRep cloneUserRep;

    public Helper() {
    }

    public User getUser() {
        return userRep.findByEmail(getUserName());
    }

    public String getUserName() {
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
        cloneUserRep.save(cloneUser);
        return cloneUser;
    }
}

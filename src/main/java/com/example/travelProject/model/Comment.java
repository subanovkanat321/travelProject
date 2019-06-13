package com.example.travelProject.model;

import com.example.travelProject.helpers.CloneUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment implements Comparable<Comment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Integer likes;
    private Integer dislikes;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    private LocalDateTime time;

    @ManyToOne
    private CloneUser user1;

    @ManyToMany
    @JsonIgnore
    private List<User> users;
    @ManyToMany
    @JsonIgnore
    private List<UserPutMark> userChecks;

    @Override
    public int compareTo(Comment o) {
        return getTime().compareTo(o.getTime());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CloneUser getUser1() {
        return user1;
    }

    public void setUser1(CloneUser user1) {
        this.user1 = user1;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UserPutMark> getUserChecks() {
        return userChecks;
    }

    public void setUserChecks(List<UserPutMark> userChecks) {
        this.userChecks = userChecks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

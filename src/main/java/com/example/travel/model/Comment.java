package com.example.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Comment implements Comparable<Comment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Integer likes;
    private Integer dislikes;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime time;

    @ManyToMany
    @JsonIgnore
    private List<User> users;
    @ManyToMany
    @JsonIgnore
    private List<UserPutMark> userChecks;

    public Comment() {
    }

    @Override
    public int compareTo(Comment o) {
        return getTime().compareTo(o.getTime());
    }
}

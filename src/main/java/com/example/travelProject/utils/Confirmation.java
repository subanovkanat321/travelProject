package com.example.travelProject.utils;

public class Confirmation {
    private Long id;
    private Integer code;

    public Confirmation(Long id, Integer code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

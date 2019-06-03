package com.example.travelProject.utils;

public class ToPay {
    Long userId;
    Integer sum;
    Integer confirmCode;

    public ToPay() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(Integer confirmCode) {
        this.confirmCode = confirmCode;
    }
}

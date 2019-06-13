package com.example.travelProject.service;

import com.example.travelProject.helpers.CloneUser;
import com.example.travelProject.model.CheckList;
import com.example.travelProject.model.Comment;
import com.example.travelProject.model.Payment;

import java.util.List;

public interface FilterService {
    List<CheckList> getPaidChecklists();

    List<CheckList> getNotPaidChecklists();

    List<Payment> getConfirmPayments();

    List<Payment> getUnConfirmPayments();

    List<Comment> getComments();

    CloneUser getMe();
}

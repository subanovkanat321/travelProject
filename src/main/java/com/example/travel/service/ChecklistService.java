package com.example.travel.service;

import com.example.travel.model.CheckList;

import java.util.List;

public interface ChecklistService {
    CheckList pay(Integer confirmationCode, Integer sum);

    List<CheckList> getPaidChecklists();

    List<CheckList> getNotPaidChecklists();


}

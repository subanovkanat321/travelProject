package com.example.travel.service.impl;

import com.example.travel.enums.CheckListStatus;
import com.example.travel.enums.PaymentStatus;
import com.example.travel.helpers.CurrentUser;
import com.example.travel.model.CheckList;
import com.example.travel.model.Payment;
import com.example.travel.repository.ChecklistRepository;
import com.example.travel.repository.PaymentRepository;
import com.example.travel.service.ChecklistService;
import com.example.travel.service.CrudService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChecklistServiceImpl implements CrudService<CheckList>, ChecklistService {
    private final ChecklistRepository checklistRep;

    private final PaymentRepository paymentRepository;

    private final CurrentUser currentUser;

    public ChecklistServiceImpl(ChecklistRepository checklistRep,
                                PaymentRepository paymentRepository,
                                CurrentUser currentUser) {
        this.checklistRep = checklistRep;
        this.paymentRepository = paymentRepository;
        this.currentUser = currentUser;
    }


    @Override
    public CheckList toPay(Integer confirmationCode, Integer sum) {
        List<CheckList> checkLists = checklistRep.findAll();
        List<Payment> payments = paymentRepository.findAll();
        boolean paid = false;
        boolean paidAllSum = false;
        Integer s = 0;
        for (Payment payment : payments) {
            if (payment.getClient().getId().equals(currentUser.getUser().getId())) {
                if (payment.getConfirmationCode().equals(confirmationCode)) {
                    if (sum > payment.getForPayment()) {
                        break;
                    }

                    payment.setTime(LocalDateTime.now());
                    payment.setForPayment(payment.getForPayment() - sum);
                    s = payment.getForPayment();
                    if (s > 0) {
                        payment.setStatus(PaymentStatus.AWAITING_CONFIRMATION);
                    } else {
                        payment.setStatus(PaymentStatus.OK);
                        paidAllSum = true;
                    }
                    paymentRepository.save(payment);
                    paid = true;
                    break;
                }
            }
        }

        if (paid) {
            for (CheckList checkList : checkLists) {
                if (checkList.getUser().getId().equals(currentUser.getUser().getId())) {
                    if (paidAllSum) {
                        checkList.setStatus(CheckListStatus.Paid);
                    }
                    checkList.setForPayment(s);
                    checklistRep.save(checkList);
                }
                return checkList;
            }
        }
        return null;
    }

    @Override
    public List<CheckList> getPaidChecklists() {
        List<CheckList> checkLists = checklistRep.findChecklistsByUser(currentUser.getUser());
        List<CheckList> paidChecklists = new ArrayList<>();
        for (CheckList checkList : checkLists) {
            if (checkList.getStatus().equals(CheckListStatus.Paid)) {
                paidChecklists.add(checkList);
            }
        }
        return paidChecklists;
    }


    @Override
    public List<CheckList> getNotPaidChecklists() {
        List<CheckList> checkLists = checklistRep.findChecklistsByUser(currentUser.getUser());
        List<CheckList> notPaidChecklists = new ArrayList<>();
        for (CheckList checkList : checkLists) {
            if (checkList.getStatus().equals(CheckListStatus.NotPaid)) {
                notPaidChecklists.add(checkList);
            }
        }
        return notPaidChecklists;
    }


    @Override
    public List<CheckList> getAll() {
        return checklistRep.findAll();
    }

    @Override
    public CheckList findById(Long id) {
        return checklistRep.findById(id).get();
    }

    @Override
    public CheckList save(CheckList checkList) {
        return checklistRep.save(checkList);
    }

    @Override
    public CheckList update(CheckList checkList) {
        return checklistRep.save(checkList);
    }

    @Override
    public void deleteById(Long id) {
        checklistRep.deleteById(id);
    }

    @Override
    public void deleteAll() {
        checklistRep.deleteAll();
    }
}

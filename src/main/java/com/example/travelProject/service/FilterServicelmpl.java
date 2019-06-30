package com.example.travelProject.service;

import com.example.travelProject.enums.CheckListStatus;
import com.example.travelProject.enums.PaymentStatus;
import com.example.travelProject.helpers.CloneUser;
import com.example.travelProject.helpers.Helper;
import com.example.travelProject.model.CheckList;
import com.example.travelProject.model.Comment;
import com.example.travelProject.model.Payment;
import com.example.travelProject.model.User;
import com.example.travelProject.repository.ChecklistRep;
import com.example.travelProject.repository.CommentRep;
import com.example.travelProject.repository.PaymentRep;
import com.example.travelProject.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FilterServicelmpl implements FilterService {
    @Autowired
    private UserRep userRep;
    @Autowired
    private CommentRep commentRep;
    @Autowired
    private ChecklistRep checklistRep;
    @Autowired
    private PaymentRep paymentRep;
    @Autowired
    private Helper helper;

    @Override
    public CloneUser getMe() {
        User user = helper.getUser();
        return helper.getCloneUser(user);
    }

    @Override
    public List<CheckList> getPaidChecklists() {
        List<CheckList> checkLists = checklistRep.findChecklistsByUser(helper.getUser());
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
        List<CheckList> checkLists = checklistRep.findChecklistsByUser(helper.getUser());
        List<CheckList> notPaidChecklists = new ArrayList<>();
        for (CheckList checkList : checkLists) {
            if (checkList.getStatus().equals(CheckListStatus.NotPaid)) {
                notPaidChecklists.add(checkList);
            }
        }
        return notPaidChecklists;
    }

    @Override
    public List<Payment> getConfirmPayments() {
        List<Payment> payments = paymentRep.findPaymentsByClient(helper.getUser());
        List<Payment> okPayments = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getStatus().equals(PaymentStatus.OK)) {
                okPayments.add(payment);
            }
        }
        return okPayments;
    }

    @Override
    public List<Payment> getUnConfirmPayments() {
        List<Payment> payments = paymentRep.findPaymentsByClient(helper.getUser());
        List<Payment> waitingPayments = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getStatus().equals(PaymentStatus.AWAITING_CONFIRMATION)) {
                waitingPayments.add(payment);
            }
        }
        return waitingPayments;
    }

    @Override
    public List<Comment> getComments() {
        List<Comment> comments = commentRep.findCommentsByUser(helper.getUser());
        Collections.sort(comments);
        Collections.reverse(comments);
        return comments;
    }

}

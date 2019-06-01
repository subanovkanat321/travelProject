package com.example.travelProject.service;

import com.example.travelProject.model.CheckList;
import com.example.travelProject.repository.ChecklistRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecklistService implements CrudService<CheckList>{
    @Autowired
    private ChecklistRep checklistRep;

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

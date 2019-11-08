package com.example.travelProject.service;

import com.example.travelProject.model.Category;
import com.example.travelProject.repository.CategoryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CrudService<Category> {
    private final CategoryRep categoryRep;

    public CategoryService(CategoryRep categoryRep) {
        this.categoryRep = categoryRep;
    }

    @Override
    public List<Category> getAll() {
        return categoryRep.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRep.findById(id).get();
    }

    @Override
    public Category save(Category category) {
        return categoryRep.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryRep.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRep.deleteById(id);
    }

    @Override
    public void deleteAll() {
        categoryRep.deleteAll();
    }
}

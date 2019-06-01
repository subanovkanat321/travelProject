package com.example.travelProject.service;

import com.example.travelProject.model.Tour;
import com.example.travelProject.repository.TourRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService implements CrudService<Tour>{
    @Autowired
    private TourRep tourRep;

    @Override
    public List<Tour> getAll() {
        return tourRep.findAll();
    }

    @Override
    public Tour findById(Long id) {
        return tourRep.findById(id).get();
    }

    @Override
    public Tour save(Tour tour) {
        return tourRep.save(tour);
    }

    @Override
    public Tour update(Tour tour) {
        return tourRep.save(tour);
    }

    @Override
    public void deleteById(Long id) {
        tourRep.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tourRep.deleteAll();
    }
}

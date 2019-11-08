package com.example.travelProject.service;

import com.example.travelProject.model.Price;
import com.example.travelProject.repository.PriceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceService implements CrudService<Price> {
    private final PriceRep priceRep;

    public PriceService(PriceRep priceRep) {
        this.priceRep = priceRep;
    }

    @Override
    public List<Price> getAll() {
        return priceRep.findAll();
    }

    @Override
    public Price findById(Long id) {
        return priceRep.findById(id).get();
    }

    @Override
    public Price save(Price price) {
        return priceRep.save(price);
    }

    @Override
    public Price update(Price price) {
        return priceRep.save(price);
    }

    @Override
    public void deleteById(Long id) {
        priceRep.deleteById(id);
    }

    @Override
    public void deleteAll() {
        priceRep.deleteAll();
    }
}

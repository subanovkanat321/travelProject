package com.example.travel.service.impl;

import com.example.travel.model.Price;
import com.example.travel.repository.PriceRepository;
import com.example.travel.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceServiceImpl implements CrudService<Price> {
    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getAll() {
        return priceRepository.findAll();
    }

    @Override
    public Price findById(Long id) {
        return priceRepository.findById(id).get();
    }

    @Override
    public Price save(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public Price update(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public void deleteById(Long id) {
        priceRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        priceRepository.deleteAll();
    }
}

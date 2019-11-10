package com.example.travel.service.impl;

import com.example.travel.model.Kindness;
import com.example.travel.repository.KindnessRepository;
import com.example.travel.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindnessServiceImpl implements CrudService<Kindness> {
    private final KindnessRepository kindnessRepository;

    public KindnessServiceImpl(KindnessRepository kindnessRepository) {
        this.kindnessRepository = kindnessRepository;
    }

    @Override
    public List<Kindness> getAll() {
        return kindnessRepository.findAll();
    }

    @Override
    public Kindness findById(Long id) {
        return kindnessRepository.findById(id).get();
    }

    @Override
    public Kindness save(Kindness kindness) {
        return kindnessRepository.save(kindness);
    }

    @Override
    public Kindness update(Kindness kindness) {
        return kindnessRepository.save(kindness);
    }

    @Override
    public void deleteById(Long id) {
        kindnessRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        kindnessRepository.deleteAll();
    }
}

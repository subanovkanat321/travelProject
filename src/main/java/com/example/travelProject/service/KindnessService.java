package com.example.travelProject.service;

import com.example.travelProject.model.Kindness;
import com.example.travelProject.repository.KindnessRep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindnessService implements CrudService<Kindness> {
    private final KindnessRep kindnessRep;

    public KindnessService(KindnessRep kindnessRep) {
        this.kindnessRep = kindnessRep;
    }

    @Override
    public List<Kindness> getAll() {
        return kindnessRep.findAll();
    }

    @Override
    public Kindness findById(Long id) {
        return kindnessRep.findById(id).get();
    }

    @Override
    public Kindness save(Kindness kindness) {
        return kindnessRep.save(kindness);
    }

    @Override
    public Kindness update(Kindness kindness) {
        return kindnessRep.save(kindness);
    }

    @Override
    public void deleteById(Long id) {
        kindnessRep.deleteById(id);
    }

    @Override
    public void deleteAll() {
        kindnessRep.deleteAll();
    }
}

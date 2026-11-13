package org.baeldung.service;

import org.baeldung.persistence.dao.pfe.RdvRepository;
import org.baeldung.persistence.model.pfe.RDV;
import org.baeldung.service.pfe.RdvServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RdvService implements RdvServiceInterface {

    @Autowired
    private RdvRepository rdvRepository;
    @Override
    public List<RDV> findAll() {
        return rdvRepository.findAll();
    }

    @Override
    public RDV findById(Long id) {
        return rdvRepository.findById(id).get();
    }

    @Override
    public RDV save(RDV rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public RDV update(RDV rdv) {
        return rdvRepository.save(rdv);
    }

    @Override
    public RDV delete(RDV rdv) {
        rdvRepository.delete(rdv);
        return rdv;
    }

    @Override
    public RDV Add(RDV rdv) {
        return rdvRepository.save(rdv);
    }
}

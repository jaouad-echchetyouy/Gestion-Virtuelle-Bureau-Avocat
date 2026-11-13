package org.baeldung.service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import org.baeldung.persistence.dao.pfe.CalendrieSeanceRepository;
import org.baeldung.persistence.dao.pfe.DossierRepository;
import org.baeldung.persistence.dao.pfe.TribunalRepository;
import org.baeldung.persistence.model.pfe.CalendrieSeance;
import org.baeldung.persistence.model.pfe.Dossier;
import org.baeldung.persistence.model.pfe.Tribunal;
import org.baeldung.service.pfe.DossierServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DossierService implements DossierServiceInterface {
	@Autowired
	private DossierRepository dossierRepository;

	@Autowired
	private TribunalRepository tribunalRepository;

	@Autowired
	private CalendrieSeanceRepository calendrieSeanceRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Tribunal> findAllnom() {
		return tribunalRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dossier> findAll() {
		return dossierRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dossier> findBynumeroDossier(String md) {
		return dossierRepository.findBynumeroDossierContaining(md);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dossier> getAllDossier() {
		return dossierRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Dossier getDossierById(Long id) {
		return dossierRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Dossier non trouvé"));
	}

	@Override
	public void deleteDossier(Long id) {
		dossierRepository.deleteById(id);
	}

	@Override
	public Dossier addDossier(Dossier dossierAj) {
		return dossierRepository.save(dossierAj);
	}

	@Override
	public CalendrieSeance addCalendrieSeance(CalendrieSeance dossierAjou) {
		return calendrieSeanceRepository.save(dossierAjou);
	}
}
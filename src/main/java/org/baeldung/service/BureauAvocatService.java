package org.baeldung.service;

import java.util.List;

import org.baeldung.persistence.dao.pfe.AvocatRepository;
import org.baeldung.persistence.dao.pfe.BarreauRepository;
import org.baeldung.persistence.dao.pfe.BureauAvocatRepository;
import org.baeldung.persistence.dao.pfe.VilleRepository;
import org.baeldung.persistence.model.pfe.Barreau;
import org.baeldung.persistence.model.pfe.BureauAvocat;
import org.baeldung.persistence.model.pfe.Ville;
import org.baeldung.service.pfe.BureauAvocatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BureauAvocatService implements BureauAvocatServiceInterface {
	@Autowired
	private BureauAvocatRepository bureauAvocatRepository;
	@Autowired
	private BarreauRepository barreauRepository;
	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private AvocatRepository avocatRepository;

	/*
	 * public void saveinfoToDB(MultipartFile file) { BureauAvocat b=new
	 * BureauAvocat(); String
	 * fileName=StringUtils.cleanPath(file.getOriginalFilename());
	 * if(fileName.contains("..")) { System.out.println("not a valid file"); } try {
	 * b.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
	 *
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 *
	 *
	 * bureauAvocatRepository.save(b); }
	 *
	 *
	 */


	@Override
	public BureauAvocat addBureauAvocat(BureauAvocat bureauAvocat) {
		return bureauAvocatRepository.save(bureauAvocat);
	}

	@Override
	public List<BureauAvocat> findAll() {
		return bureauAvocatRepository.findAll();
	}

	@Override
	public BureauAvocat findById(Long id) {
		return bureauAvocatRepository.findById(id).get();
	}
	@Override
	public List<BureauAvocat> findByNom(String mc) {
		return bureauAvocatRepository.findByNomContaining(mc);
	}







	@Override
	public List<Barreau> findAllBarreaux() {
		return barreauRepository.findAll();
	}

	@Override
	public List<Ville> findAllVilles() {
		return villeRepository.findAll();
	}



	/*
	 * @Override public List<Avocat> findByName() {
	 *
	 * return avocatRepository.findAll(); }
	 */
}

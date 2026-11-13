package org.baeldung.service;


import java.io.IOException;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

import org.baeldung.persistence.dao.pfe.BarreauRepository;
import org.baeldung.persistence.dao.pfe.DossierRepository;
import org.baeldung.persistence.dao.pfe.SecretaireRepository;
import org.baeldung.persistence.dao.pfe.VilleRepository;
import org.baeldung.persistence.model.pfe.Barreau;
import org.baeldung.persistence.model.pfe.Dossier;
import org.baeldung.persistence.model.pfe.Secretaire;
import org.baeldung.persistence.model.pfe.Ville;
import org.baeldung.service.pfe.SecretaireServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class SecretaireService  implements SecretaireServiceInterface {

	@Autowired
	private SecretaireRepository secretaireRepository;
	@Autowired
	private BarreauRepository barreauRepository;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private DossierRepository dossierRepository;

	/*@Override
	public Secretaire addSecretaire(Secretaire secretaire) {

		return secretaireRepository.save(secretaire);
	}*/

    @Override
    public void saveSecretairetoDB(MultipartFile file,
    		String firstName,
    		String lastName,
    		String email,
    		String tel,
    		String telfixe,
    		String adresse,
    		Date DateCreation,
    		String password,
    		String CarteNational) {

    	Secretaire secretaire = new Secretaire();
    	String fileName=StringUtils.cleanPath(file.getOriginalFilename());
    	if(fileName.contains("..")) {
    		System.out.println("not a valid file");
    	}
    	try {
			secretaire.setImguser(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {

			e.printStackTrace();
		}
    	secretaire.setFirstName(firstName);
    	secretaire.setLastName(lastName);
    	secretaire.setEmail(email);
    	secretaire.setTel(tel);
    	secretaire.setTelfixe(telfixe);
    	secretaire.setAdresse(adresse);
    	secretaire.setDateCreation(DateCreation);
    	secretaire.setPassword(password);
    	secretaire.setCarteNational(CarteNational);

    	secretaireRepository.save(secretaire);
    }

	@Override
	public List<Secretaire> findAll() {
		return secretaireRepository.findAll();
	}


	@Override
	public Secretaire findById(Long id) {
		return secretaireRepository.findById(id).get();
	}

	@Override
	public List<Secretaire> findByfirstName(String mt) {
		// TODO Auto-generated method stub
		return secretaireRepository.findByfirstNameContaining(mt);
	}
	@Override
	public List<Barreau> findBynomBarreau(String mv) {
		// TODO Auto-generated method stub
		return barreauRepository.findBynomBarreauContaining(mv);
	}
	@Override
	public List<Barreau> findAllBarreaux() {
		return barreauRepository.findAll();
	}


	@Override
	public List<Ville> findAllVilles() {
		return villeRepository.findAll();
	}


	@Override
	public List<Dossier> findAllDossier(){
		return dossierRepository.findAll() ;
	}




	@Override
	public void deleteSecretaire(Long id) {
		// TODO Auto-generated method stub
		secretaireRepository.deleteById(id);
	}

	@Override
	public Secretaire addSecretaire(Secretaire secretaire) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Secretaire findByEmail(String email) {
		return secretaireRepository.findByEmail(email);
	}

}

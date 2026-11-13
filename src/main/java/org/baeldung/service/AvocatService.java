
package org.baeldung.service;
import java.io.IOException;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

import org.baeldung.persistence.dao.pfe.AvocatRepository;
import org.baeldung.persistence.dao.pfe.BarreauRepository;
import org.baeldung.persistence.dao.pfe.ClientRepository;
import org.baeldung.persistence.dao.pfe.VilleRepository;
import org.baeldung.persistence.model.pfe.Avocat;
import org.baeldung.persistence.model.pfe.Barreau;
import org.baeldung.persistence.model.pfe.Client;
import org.baeldung.persistence.model.pfe.Ville;
import org.baeldung.service.pfe.AvocatServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AvocatService implements AvocatServiceInterface {
	@Autowired
	private AvocatRepository avocatRepository;
	@Autowired
	private BarreauRepository barreauRepository;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private ClientRepository clientRepository;

	/*
	 * @Override public Avocat addAvocat(Avocat avocatAj) { return
	 * avocatRepository.save(avocatAj); }
	 */

	  @Override
	    public void saveAvocattoDB(MultipartFile file,
	    		String firstName,
	    		String lastName,
	    		String email,
	    		String tel,
	    		String telfixe,
	    		String adresse,
	    		Date DateCreation,
	    		String password,
	    		Barreau idBarreau,
	    		String CarteNational) {

	    	    Avocat avocat = new Avocat();
	    	   String fileName=StringUtils.cleanPath(file.getOriginalFilename());
	    	  if(fileName.contains("..")) {
	    		System.out.println("not a valid file");
	    	   }
	    	   try {
				avocat.setImguser(Base64.getEncoder().encodeToString(file.getBytes()));
			   } catch (IOException e) {

				e.printStackTrace();
			}
	    	avocat.setFirstName(firstName);
	    	avocat.setLastName(lastName);
	    	avocat.setEmail(email);
	    	avocat.setTel(tel);
	    	avocat.setTelfixe(telfixe);
	    	avocat.setAdresse(adresse);
	    	avocat.setDateCreation(DateCreation);
	    	avocat.setPassword(password);

	    	System.err.println(idBarreau.getNomBarreau());
	    	avocat.setBarreau(idBarreau);
	    	avocat.setCarteNational(CarteNational);
	        avocatRepository.save(avocat);
	      }

	//return liste of avocat
	@Override
	public List<Avocat> findAll() {
		return avocatRepository.findAll();
	}

	@Override
	public Avocat findById(Long id) {
		return avocatRepository.findById(id).get();
	}

	@Override
	public List<Avocat> findByfirstName(String mt) {

		return avocatRepository.findByfirstNameContaining(mt);
	}
	@Override
	public List<Barreau> findBynomBarreau(String mv) {
		// TODO Auto-generated method stub
		return barreauRepository.findBynomBarreauContaining(mv);
	}
	public Barreau findBarreauById(Long id) {
		return barreauRepository.findById(id).orElse(null);
	}



	@Override
	public List<Barreau> findAllBarreaux() {
		return barreauRepository.findAll();
	}
	@Override
	public List<Client> findAllClient() {
		return clientRepository.findAll();
	}

	@Override
	public List<Ville> findAllVilles() {
		return villeRepository.findAll();
	}

	@Override
	public void deleteAvocat(Long id) {
		  avocatRepository.deleteById(id);

	}


	@Override
	public Avocat addAvocat(Avocat avocatAj) {
		// TODO Auto-generated method stub
		return null;
	}

}
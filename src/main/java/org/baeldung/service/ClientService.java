package org.baeldung.service;

import java.io.IOException;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

import org.baeldung.persistence.dao.pfe.ClientRepository;
import org.baeldung.persistence.dao.pfe.DossierRepository;
import org.baeldung.service.pfe.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.baeldung.persistence.model.pfe.Client;
import org.baeldung.persistence.model.pfe.Dossier;
@Service
public class ClientService  implements ClientServiceInterface {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private DossierRepository dossierRepository;

	/*
	 * @Override public Client addClient(Client client) {
	 *
	 * return clientRepository.save(client); }
	 */
	  @Override
	    public void saveClienttoDB(MultipartFile file,
	    		String firstName,
	    		String lastName,
	    		String email,
	    		String tel,
	    		String telfixe,
	    		String adresse,
	    		Date DateCreation,
	    		String password,
	    		String CarteNational){

		  Client client=new Client();
		  String fileNames=StringUtils.cleanPath(file.getOriginalFilename());
	    	if(fileNames.contains("..")) {
	    		System.out.println("not a valid file");
	    	}
	    	try {
				client.setImguser(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {

				e.printStackTrace();
			}
	    	client.setFirstName(firstName);
	    	client.setLastName(lastName);
	    	client.setEmail(email);
	    	client.setTel(tel);
	    	client.setTelfixe(telfixe);
	    	client.setAdresse(adresse);
	    	client.setDateCreation(DateCreation);
	    	client.setPassword(password);
	    	client.setCarteNational(CarteNational);

	    	clientRepository.save(client);
	  }


	  public Client findByEmail(String firstName){
		  return clientRepository.findByEmail(firstName);
	  }

//	  public Client getRDV(){
//		  return clientRepository.getRDV();
//	  }
	@Override
	public List<Client> findAll() {

		return clientRepository.findAll();
	}

	@Override
	public Client findById(Long id) {

		return clientRepository.findById(id).get();
	}

	@Override
	public List<Client> findByfirstName(String cl) {

		return clientRepository.findByfirstNameContaining(cl);
	}

	@Override
	public List<Dossier> findAllDossier() {

		return dossierRepository.findAll() ;
	}


}

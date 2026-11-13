package org.baeldung.service;

/*import org.baeldung.persistence.dao.pfe.AccuseRepository;
*/import org.baeldung.persistence.dao.pfe.DossierRepository;
/*import org.baeldung.persistence.dao.pfe.TemoinsRepository;
import org.baeldung.persistence.dao.pfe.VictimeRepository;
import org.baeldung.persistence.model.pfe.Accuse;*/
/*import org.baeldung.persistence.model.pfe.Temoin;
import org.baeldung.persistence.model.pfe.Victime;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChercherService {
	@Autowired
	DossierRepository dossierRepository;
	
	/*
	 * @Autowired VictimeRepository victimeRepository;
	 * 
	 * @Autowired AccuseRepository accuseRepository;
	 * 
	 * @Autowired TemoinsRepository temoinRepository;
	 * 
	 * public Dossier getDossierById(String id) { return
	 * dossierRepository.findById(id).get(); }
	 * 
	 * 
	 * public Victime getVictimeByCin(String cin) { return
	 * victimeRepository.getVictimeByCin(cin); }
	 * 
	 * public List<Victime> getVictimesByKeyword(String keyword){ return
	 * victimeRepository.getVictimesByKeyword(keyword); }
	 * 
	 * 
	 * public Accuse getAccuseByCin(String cin) { return
	 * accuseRepository.getAccuseByCin(cin); }
	 * 
	 * public List<Accuse> getAccusesByKeyword(String keyword){ return
	 * accuseRepository.getAccusesByKeyword(keyword); }
	 * 
	 * 
	 * public Temoin getTemoinByCin(String cin) { return
	 * temoinRepository.getTemoinByCinTemoin(cin); }
	 * 
	 * public List<Temoin> getTemoinsByKeyword(String keyword){ return
	 * temoinRepository.getTemoinsByKeyword(keyword); }
	 */
}

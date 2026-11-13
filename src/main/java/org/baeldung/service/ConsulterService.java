package org.baeldung.service;

/*import org.baeldung.persistence.dao.pfe.AccuseRepository;
import org.baeldung.persistence.dao.pfe.DossierAccuseRepository;*/
import org.baeldung.persistence.dao.pfe.DossierRepository;
/*import org.baeldung.persistence.dao.pfe.DossierVictimeRepository;
import org.baeldung.persistence.dao.pfe.DroitsPersonneDecedeeRepository;
import org.baeldung.persistence.dao.pfe.IndemniteRepository;
import org.baeldung.persistence.dao.pfe.TemoignerRepository;
import org.baeldung.persistence.dao.pfe.TemoinsRepository;
import org.baeldung.persistence.dao.pfe.UserDossierRepository;
import org.baeldung.persistence.dao.pfe.VictimeRepository;
import org.baeldung.persistence.model.pfe.Accuse;*/
        import org.baeldung.persistence.model.pfe.Dossier;
/*import org.baeldung.persistence.model.pfe.DossierAccuse;
import org.baeldung.persistence.model.pfe.DossierVictime;
import org.baeldung.persistence.model.pfe.DroitsPersonneDecedee;
import org.baeldung.persistence.model.pfe.Indemnite;
import org.baeldung.persistence.model.pfe.OfficierCivil;
import org.baeldung.persistence.model.pfe.Temoigner;
import org.baeldung.persistence.model.pfe.Temoin;
import org.baeldung.persistence.model.pfe.Victime;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsulterService {
	@Autowired
	DossierRepository dossierRepository;
	
	/*
	 * @Autowired VictimeRepository victimeRepository;
	 * 
	 * @Autowired DroitsPersonneDecedeeRepository droitsPersonneDecedeeRepository;
	 * 
	 * @Autowired AccuseRepository accuseRepository;
	 * 
	 * @Autowired TemoinsRepository temoinsRepository;
	 * 
	 * @Autowired IndemniteRepository indemniteRepository;
	 * 
	 * @Autowired DossierVictimeRepository dossierVictimeRepository;
	 * 
	 * @Autowired DossierAccuseRepository dossierAccuseRepository;
	 * 
	 * @Autowired TemoignerRepository temoignerRepository;
	 * 
	 * @Autowired UserDossierRepository userDossierRepository;
	 * 
	 * /////////// public List<Dossier> getAllDossiers() { return
	 * dossierRepository.findAll(); }
	 * 
	 * public Dossier getDossierById(String id) { return
	 * dossierRepository.findById(id).get(); }
	 */
    
    public void ajouterDossier(Dossier dossier) {
		dossierRepository.save(dossier);  
	}
    
	/*
	 * public void deleteDossier(String numeroDossier) {
	 * dossierRepository.deleteById(numeroDossier); }
	 * 
	 * /////////// public Victime getVictimeById(Long id) { return
	 * victimeRepository.getVictimeByIdVictime(id); }
	 * 
	 * public void ajouterVictime(Victime victime) {
	 * victimeRepository.save(victime); }
	 * 
	 * public List<Avocat> getAvocatVictime(Long id) { return
	 * victimeRepository.getAvocatByVictime(id); }
	 * 
	 * public void deleteVictimeById(Long id) { victimeRepository.deleteById(id); }
	 * 
	 * public List<Victime> getVictimes(String numeroDossier){ return
	 * victimeRepository.getVictimeByNumeroDossier(numeroDossier); }
	 * 
	 * 
	 * public List<DroitsPersonneDecedee> getDawisByIdVictime(Long idVictime){
	 * return
	 * droitsPersonneDecedeeRepository.getDroitsPersonneDecedeeByVictime(idVictime);
	 * }
	 * 
	 * public List<Indemnite> getIndemnitesByIdVictime(Long idVictime){ return
	 * indemniteRepository.getIndemnitesByIdVictime(idVictime); }
	 * 
	 * //////// public Accuse getAccuseById(Long id) { return
	 * accuseRepository.getAccuseByIdAccuse(id); }
	 * 
	 * public void ajouterAccuse(Accuse accuse) { accuseRepository.save(accuse); }
	 * 
	 * public List<Avocat> getAvocatAccuse(Long id) { return
	 * accuseRepository.getAvocatByAccuse(id); }
	 * 
	 * public void deleteAccuseById(Long id) { accuseRepository.deleteById(id); }
	 * 
	 * public List<Accuse> getAccuses(String numeroDossier){ return
	 * accuseRepository.getAccuseByNumeroDossier(numeroDossier); }
	 * 
	 * public OfficierCivil getOfficierCivil(Long idAccuse) { return
	 * accuseRepository.getOfficierCivil(idAccuse); }
	 * 
	 * public String getAccuseAssurance(Long idAccuse) { return
	 * accuseRepository.getAccuseAssurance(idAccuse); }
	 * 
	 * public String getAccuseTohma(Long idAccuse, String numeroDossier) { return
	 * accuseRepository.getAccuseTohma(idAccuse, numeroDossier); }
	 * 
	 * public String getOfficierCivilAssurance(String cniOfficierCivil) { return
	 * accuseRepository.getOfficierCivilAssurance(cniOfficierCivil); }
	 * 
	 * 
	 * /////// public Temoin getTemoinByCin(String cin) { return
	 * temoinsRepository.getTemoinByCinTemoin(cin); }
	 * 
	 * public void ajouterTemoin(Temoin temoin) { temoinsRepository.save(temoin); }
	 * 
	 * public void deleteTemoinByCin(String cin) {
	 * temoinsRepository.deleteById(cin); }
	 * 
	 * public List<Temoin> getTemoins(String numeroDossier){ return
	 * temoinsRepository.getTemoinByNumeroDossier(numeroDossier); }
	 * 
	 * 
	 * //////// public void deleteDossierVictime(Long idVictime, String
	 * numeroDossier) { dossierVictimeRepository.delete(new
	 * DossierVictime(idVictime, numeroDossier)); }
	 * 
	 * public void deleteDossierAccuse(Long idAccuse, String numeroDossier) {
	 * dossierAccuseRepository.delete(dossierAccuseRepository.
	 * findByIdAccuseAndNumeroDossier(idAccuse, numeroDossier)); }
	 * 
	 * public void deleteTemoigner(String cinTemoin, String numeroDossier) {
	 * temoignerRepository.delete(new Temoigner(cinTemoin, numeroDossier)); }
	 * 
	 * 
	 * public List<Dossier> getAllDossiersByUser(Long idUser) { return
	 * userDossierRepository.getDossierByIdUser(idUser); }
	 */
}

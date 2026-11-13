package org.baeldung.service;

/*import org.baeldung.persistence.dao.pfe.AccuseRepository;
import org.baeldung.persistence.dao.pfe.AssuranceRepository;
import org.baeldung.persistence.dao.pfe.AvocatAccuseRepository;*/
/*import org.baeldung.persistence.dao.pfe.AvocatVictimeRepository;
*/
/*import org.baeldung.persistence.dao.pfe.CourAppelRepository;
import org.baeldung.persistence.dao.pfe.DossierAccuseRepository;*/
/*import org.baeldung.persistence.dao.pfe.DossierRepository;
import org.baeldung.persistence.dao.pfe.DossierVictimeRepository;
import org.baeldung.persistence.dao.pfe.DroitsPersonneDecedeeRepository;
import org.baeldung.persistence.dao.pfe.IndemniteRepository;
import org.baeldung.persistence.dao.pfe.JugeRepository;
import org.baeldung.persistence.dao.pfe.OfficierCivilRepository;
import org.baeldung.persistence.dao.pfe.TemoignerRepository;
import org.baeldung.persistence.dao.pfe.TemoinsRepository;
import org.baeldung.persistence.dao.pfe.TohmaRepository;
import org.baeldung.persistence.dao.pfe.UserDossierRepository;
import org.baeldung.persistence.dao.pfe.VictimeRepository;
import org.baeldung.persistence.model.pfe.Accuse;
import org.baeldung.persistence.model.pfe.Assurance;*/
/*import org.baeldung.persistence.model.pfe.AvocatAccuse;
import org.baeldung.persistence.model.pfe.AvocatVictime;*/
/*import org.baeldung.persistence.model.pfe.CourAppel;
*/
/*import org.baeldung.persistence.model.pfe.DossierAccuse;
import org.baeldung.persistence.model.pfe.DossierVictime;
import org.baeldung.persistence.model.pfe.DroitsPersonneDecedee;
import org.baeldung.persistence.model.pfe.Indemnite;*/
/*import org.baeldung.persistence.model.pfe.OfficierCivil;
import org.baeldung.persistence.model.pfe.PremiereInstance;
import org.baeldung.persistence.model.pfe.Temoigner;
import org.baeldung.persistence.model.pfe.Temoin;
import org.baeldung.persistence.model.pfe.Tohma;
import org.baeldung.persistence.model.pfe.UserDossier;
import org.baeldung.persistence.model.pfe.Victime;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AjouterService {
	/*
	 * @Autowired private CourAppelRepository courAppelRepository;
	 */
	/*
	 * @Autowired private DossierRepository dossierRepository;
	 */
	/*
	 * @Autowired private VictimeRepository victimeRepository;
	 */
	/*
	 * @Autowired private DroitsPersonneDecedeeRepository dawiRepository;
	 * 
	 * @Autowired private AvocatRepository avocatRepository;
	 * 
	 * @Autowired private AccuseRepository accuseRepository;
	 * 
	 * @Autowired private OfficierCivilRepository officierCivilRepository;
	 * 
	 * @Autowired private TemoinsRepository temoinsRepository;
	 */
	/*
	 * @Autowired private DossierVictimeRepository dossierVictimeRepository;
	 * 
	 * @Autowired private DossierAccuseRepository dossierAccuseRepository;
	 * 
	 * @Autowired private IndemniteRepository indemniteRepository;
	 * 
	 * @Autowired private TemoignerRepository temoignerRepository;
	 * 
	 * @Autowired private AssuranceRepository assuranceRepository;
	 * 
	 * @Autowired private BarreauRepository barreauRepository;
	 * 
	 * @Autowired private TohmaRepository tohmaRepository;
	 * 
	 * @Autowired private AvocatVictimeRepository avocatVictimeRepository;
	 * 
	 * @Autowired private AvocatAccuseRepository avocatAccuseRepository;
	 * 
	 * @Autowired private JugeRepository jugeRepository;
	 * 
	 * @Autowired private UserDossierRepository userDossierRepository;
	 * 
	 * 
	 * public CourAppel ajouterCourAppel(String nom) { CourAppel courAppel = new
	 * CourAppel(nom); return courAppelRepository.save(courAppel); }
	 * 
	 * public CourAppel ajouterPremiereInstance(String nom, Long idCourAppel) {
	 * CourAppel courAppel = courAppelRepository.findById(idCourAppel).get();
	 * PremiereInstance premiereInstance = new PremiereInstance(nom);
	 * courAppel.getPremiereInstances().add(premiereInstance); return
	 * courAppelRepository.save(courAppel); }
	 * 
	 * public List<CourAppel> getAllCourAppels() { return
	 * courAppelRepository.findAll(); }
	 * 
	 * public CourAppel getCourAppelById(Long id) { return
	 * courAppelRepository.findById(id).get(); }
	 * 
	 * public CourAppel save(CourAppel courAppel) { return
	 * courAppelRepository.save(courAppel); }
	 * 
	 * 
	 * public void ajouterDossier(Dossier dossier) {
	 * dossierRepository.save(dossier); }
	 * 
	 * public List<Dossier> getDaySeances(){ return
	 * dossierRepository.getDaySeances(); }
	 * 
	 * public Victime consulterVictime(Long idVictime) { return
	 * victimeRepository.findById(idVictime).get(); }
	 * 
	 * public Victime consulterVictime(String cinVictime) { return
	 * victimeRepository.getVictimeByCin(cinVictime); }
	 * 
	 * public void ajouterVictime(Victime victime) {
	 * victimeRepository.save(victime); }
	 * 
	 * public Accuse consulterAccuse(Long idAccuse) { return
	 * accuseRepository.findById(idAccuse).get(); }
	 * 
	 * public void ajouterAccuse(Accuse accuse) { accuseRepository.save(accuse);
	 * 
	 * }
	 * 
	 * public Dossier getDossierByNumero(String numeroDossier) { return
	 * dossierRepository.findById(numeroDossier).get(); }
	 * 
	 * public List<Victime> listeVictimes(){ List<Victime> victimes; victimes =
	 * victimeRepository.findAll(); return victimes; }
	 * 
	 * public void ajouterDawi(DroitsPersonneDecedee droitsPersonneDecedee) {
	 * dawiRepository.save(droitsPersonneDecedee); }
	 * 
	 * 
	 * 
	 * public void ajouterOfficierCivil(OfficierCivil officierCivil) {
	 * officierCivilRepository.save(officierCivil); }
	 * 
	 * public void ajouterTemoin(Temoin temoin) { temoinsRepository.save(temoin); }
	 * 
	 * public Avocat consulterAvocat(String cinAvocat) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * public void ajouterAvocat(Avocat avocat) { avocatRepository.save(avocat);
	 * 
	 * }
	 * 
	 * public void ajouterDossierVictime(DossierVictime dossierVictime) {
	 * dossierVictimeRepository.save(dossierVictime); }
	 * 
	 * public DroitsPersonneDecedee consulterDawi(Long idDawi) { return
	 * dawiRepository.getDroitsPersonneDecedeeByIdDawi(idDawi); }
	 * 
	 * public DroitsPersonneDecedee consulterDawi(String cinDawi) { return
	 * dawiRepository.getDroitsPersonneDecedeeByCin(cinDawi); }
	 * 
	 * public void ajouterIndemnite(Indemnite indemnite) {
	 * indemniteRepository.save(indemnite); }
	 * 
	 * 
	 * public List<Assurance> listeAssurances(){ return
	 * assuranceRepository.findAll(); }
	 * 
	 * public List<Barreau> listeBarreaux(){ return barreauRepository.findAll(); }
	 * 
	 * public List<Tohma> listeCharges(){ return tohmaRepository.findAll(); }
	 * 
	 * public Accuse consulterAccuse(String cinAccuse) { return
	 * accuseRepository.getAccuseByCin(cinAccuse); }
	 * 
	 * public void ajouterDossierAccuse(DossierAccuse dossierAccuse) {
	 * dossierAccuseRepository.save(dossierAccuse); }
	 * 
	 * public Temoin consulterTemoin(String cinTemoin) { return
	 * temoinsRepository.getTemoinByCinTemoin(cinTemoin); }
	 * 
	 * public void ajouterTemoigner(Temoigner temoigner) {
	 * temoignerRepository.save(temoigner); }
	 * 
	 * public List<DroitsPersonneDecedee> listeDawis() { return
	 * dawiRepository.findAll(); }
	 * 
	 * public List<Avocat> listeAvocats() { return avocatRepository.findAll(); }
	 * 
	 * public List<OfficierCivil> listeOfficierCivils() { return
	 * officierCivilRepository.findAll(); }
	 * 
	 * public List<Temoin> listeTemoins() { return temoinsRepository.findAll(); }
	 * 
	 * public List<Accuse> listeAccuses() { return accuseRepository.findAll(); }
	 * 
	 * 
	 * 
	 * public List<Juge> listeJuges(Long idTribunal, Long typeTribunal){ return
	 * jugeRepository.findByIdTribunalAndTypeTribunal(idTribunal, typeTribunal); }
	 * 
	 * public void ajouterAvocatVictime(AvocatVictime avocatVictime) {
	 * avocatVictimeRepository.save(avocatVictime); }
	 * 
	 * public void ajouterAvocatAccuse(AvocatAccuse avocatAccuse) {
	 * avocatAccuseRepository.save(avocatAccuse); }
	 * 
	 * public void ajouterUserDossier(UserDossier userDossier) {
	 * userDossierRepository.save(userDossier); }
	 */
	
}

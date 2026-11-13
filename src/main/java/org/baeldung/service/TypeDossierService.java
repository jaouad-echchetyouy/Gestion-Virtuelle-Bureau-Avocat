package org.baeldung.service;

import java.util.List;

import org.baeldung.persistence.dao.pfe.TypeDossierRepository;
import org.baeldung.persistence.model.pfe.TypeDossier;
import org.baeldung.service.pfe.TypeDossierServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TypeDossierService implements TypeDossierServiceInterface {
	@Autowired
	TypeDossierRepository typeDossierRepository;
	@Override
	public List<TypeDossier> findAllTypeDossier() {
		// TODO Auto-generated method stub
		return typeDossierRepository.findAll();
	}



	@Override
	public List<TypeDossier> findAll() {
		// TODO Auto-generated method stub
		return  typeDossierRepository.findAll() ;
	}

	@Override
	public List<TypeDossier> getAllTypeDossier() {
		// TODO Auto-generated method stub
		return typeDossierRepository.findAll();
	}



}

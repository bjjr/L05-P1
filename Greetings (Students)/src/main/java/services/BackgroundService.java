/* BackgroundService.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BackgroundRepository;
import domain.Background;

@Service
@Transactional
public class BackgroundService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private BackgroundRepository backgroundRepository;	
	
	// Supporting services ----------------------------------------------------
	
	// Constructors -----------------------------------------------------------

	public BackgroundService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public Collection<Background> findAll() {
		Collection<Background> result;
		
		result = backgroundRepository.findAll();
		
		return result;
	}
	
	// Other business methods -------------------------------------------------
	
}
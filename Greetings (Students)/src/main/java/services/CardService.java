/* CardService.java
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
import org.springframework.util.Assert;

import repositories.CardRepository;
import security.LoginService;
import security.UserAccount;
import domain.Card;
import domain.Customer;

@Service
@Transactional
public class CardService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CardRepository cardRepository;	
	
	// Supporting services ----------------------------------------------------
	
	@Autowired
	private CustomerService customerService;

	
	// Constructors -----------------------------------------------------------

	public CardService() {
		super();
	}
	
	// Simple CRUD methods ----------------------------------------------------
	
	public Card create() {
		Card result;
		Customer customer;
		
		customer = customerService.findByPrincipal();
				
		result = new Card();		
		result.setCustomer(customer);
		
		return result;
	}
	
	public Collection<Card> findByPrincipal() {
		Collection<Card> result;
		UserAccount userAccount;
		
		userAccount = LoginService.getPrincipal();
		result = cardRepository.findByUserAccount(userAccount);		
		
		return result;
	}
	
	public Card findOne(int cardId) {
		Card result;

		result = cardRepository.findOne(cardId);
		
		return result;
	}

	public Card findOneToEdit(int cardId) {
		Card result;

		result = findOne(cardId);
		checkPrincipal(result);

		return result;
	}
	
	public void save(Card card) {
		checkPrincipal(card);		
		cardRepository.save(card);
	}	
	
	public void delete(Card card) {
		checkPrincipal(card);
		cardRepository.delete(card);
	}

	// Other business methods -------------------------------------------------
	
	protected void checkPrincipal(Card card) {
		Customer customer;
		
		customer = customerService.findByPrincipal();
		Assert.isTrue(card.getCustomer().equals(customer));			
	}
	
}
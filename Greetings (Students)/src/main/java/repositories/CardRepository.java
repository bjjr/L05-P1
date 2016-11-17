/* CardRepository.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;
import domain.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	
	@Query("select c from Card c where c.customer.userAccount = ?1")
	Collection<Card> findByUserAccount(UserAccount userAccount);	

}

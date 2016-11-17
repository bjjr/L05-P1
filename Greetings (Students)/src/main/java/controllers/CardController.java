/* CardCustomerController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CardService;
import domain.Card;

@Controller
@RequestMapping("/card")
public class CardController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private CardService cardService;
		
	// Constructors -----------------------------------------------------------
	
	public CardController() {
		super();
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int cardId) {
		ModelAndView result;
		Card card;
		
		card = cardService.findOne(cardId);
		result = new ModelAndView("card/display");
		result.addObject("card", card);				
		
		return result;
	}

}
/* CardCustomerController.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BackgroundService;
import services.CardService;
import controllers.AbstractController;
import domain.Background;
import domain.Card;

@Controller
@RequestMapping("/card/customer")
public class CardCustomerController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private CardService cardService;
	@Autowired
	private BackgroundService backgroundService;	
	
	// Constructors -----------------------------------------------------------
	
	public CardCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Card> cards;
		
		cards = cardService.findByPrincipal();
		result = new ModelAndView("card/list");
		result.addObject("cards", cards);				
		
		return result;
	}
	
	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Card card;

		card = cardService.create();
		result = createEditModelAndView(card);

		return result;
	}

	// Edition ----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int cardId) {
		ModelAndView result;
		Card card;

		card = cardService.findOneToEdit(cardId);
		result = createEditModelAndView(card);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Card card, BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(card);
		} else {
			try {				
				cardService.save(card);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {				
				result = createEditModelAndView(card, "card.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Card card, BindingResult binding) {
		ModelAndView result;

		try {			
			cardService.delete(card);			
			result = new ModelAndView("redirect:list.do");						
		} catch (Throwable oops) {	
			result = createEditModelAndView(card, "card.commit.error");
		}

		return result;
	}
	
	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Card card) {
		ModelAndView result;

		result = createEditModelAndView(card, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Card card, String message) {
		ModelAndView result;
		Collection<Background> backgrounds;
		
		backgrounds = backgroundService.findAll();
				
		result = new ModelAndView("card/edit");
		result.addObject("card", card);
		result.addObject("backgrounds", backgrounds);
		result.addObject("message", message);
		
		return result;
	}

}
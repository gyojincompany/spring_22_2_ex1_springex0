package com.javagyojin.spring_22_1_ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javagyojin.spring_22_1_ex.dao.TicketDao;
import com.javagyojin.spring_22_1_ex.dto.TicketDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private TicketDao dao;
	
	@Autowired	
	public void setDao(TicketDao dao) {
		this.dao = dao;
	}


	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/buy_ticket", method = RequestMethod.GET)
	public String buy_ticket() {
		return "buy_ticket";
	}
	
	
	@RequestMapping(value = "/buy_ticket_card", method = RequestMethod.GET)
	public String buy_ticket_card(TicketDto ticketDto, Model model) {
		System.out.println("buy_ticket_card ½ÇÇà");		
		
		dao.buyTicket(ticketDto);
		
		model.addAttribute("ticketInfo",ticketDto);
		
		return "buy_ticket_end";
	}
	
}

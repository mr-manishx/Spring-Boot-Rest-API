package com.mrsit.rest;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrsit.bindings.Passenger;
import com.mrsit.bindings.Tickets;
import com.mrsit.service.TicketService;

@RestController
public class TicketRestController {
	
	public TicketService tickService;
	
	public TicketRestController(TicketService tickService) {
		this.tickService = tickService;
	}
	
	@PostMapping("/ticket")
	public ResponseEntity<Tickets> bookTickets(Passenger p){
		Tickets tick = tickService.bookTicket(p);
		return new ResponseEntity<>(tick, HttpStatus.CREATED);
	}
	
	@GetMapping("/all-tickets")
	public ResponseEntity<Collection<Tickets>> getAllTickets(){
		Collection<Tickets> ticks = tickService.getAllTickets();
		return new ResponseEntity<>(ticks, HttpStatus.OK);
	}
}

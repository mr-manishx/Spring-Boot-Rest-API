package com.mrsit.service;

import java.util.Collection;

import com.mrsit.bindings.Passenger;
import com.mrsit.bindings.Tickets;

public interface TicketService {
	
	public Tickets bookTicket(Passenger p);
	
	public Collection<Tickets> getAllTickets();

}

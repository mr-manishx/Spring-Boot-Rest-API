package com.mrsit.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mrsit.bindings.Passenger;
import com.mrsit.bindings.Tickets;

@Service
public class TicketsServiceImp implements TicketService {
	
	Map<Integer, Tickets> map = new HashMap<>();
	
	@Override
	public Tickets bookTicket(Passenger p) {
		
		Tickets t = new Tickets();
		Random r = new Random();
		int id = r.nextInt(10)+3;
		t.setTicketNum(id);
		
		BeanUtils.copyProperties(p, t);
		t.setStatus("CONFIRMED");
		
		map.put(id, t);
		
		return t;
	}

	@Override
	public Collection<Tickets> getAllTickets() {
		// TODO Auto-generated method stub
		return map.values();
	}

}

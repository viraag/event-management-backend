package com.vg.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vg.eventmanagement.repositories.EventRepository;
import com.vg.eventmanagement.requests.AddEvent;
import com.vg.eventmanagement.requests.EventResponse;
import com.vg.eventmanagement.services.EventService;

@RestController
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventService eventService;

	public EventController(EventRepository repository) {
		this.eventRepository = repository;
	}

	@GetMapping("/events")
	public List<EventResponse> getAllEvents() {
	   
		return eventService.getAllEventsService();
		
		
	}
	
	@GetMapping("/events/{id}")
	public EventResponse getEvents(@PathVariable Long id) {
		
		return eventService.getEventService(id);
		
	}

	@PostMapping("/saveEvents")
	public String newEvent(@RequestBody AddEvent addEvent) {

		eventService.saveEventService(addEvent);
		return "Success";
	}
	
	@DeleteMapping("/removeEvent/{id}")
	public String deleteEvent(@PathVariable Long id)
	{
		return eventService.deleteEventService(id);
	}
	
	@PutMapping("/updateEvent/{id}")
	public String updateEvent(@PathVariable Long id, @RequestBody AddEvent addEvent)
	{
		return eventService.updateEvent(id, addEvent);
	}
	

}

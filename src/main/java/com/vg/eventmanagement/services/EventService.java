package com.vg.eventmanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.vg.eventmanagement.common.NotFoundException;
import com.vg.eventmanagement.models.Event;
import com.vg.eventmanagement.models.Organizer;
import com.vg.eventmanagement.repositories.EventRepository;
import com.vg.eventmanagement.repositories.OrganizerRepository;
import com.vg.eventmanagement.requests.AddEvent;
import com.vg.eventmanagement.requests.EventResponse;

@Service
public class EventService {

	@Autowired
	OrganizerRepository organizerRepository;

	@Autowired
	EventRepository eventRepository;

	public Event saveEventService(AddEvent addEvent) {
		Event event = new Event();
		Organizer organizer = organizerRepository.findById(addEvent.getOrganizerId()).get();
		event.setEventName(addEvent.getEventName());
		event.setDescription(addEvent.getDescription());
		event.setStartTime(addEvent.getStartTime());
		event.setEndTime(addEvent.getEndTime());
		event.setZoneId(addEvent.getZoneId());
		event.setStarted(addEvent.getStarted());
		event.setOrganizer(organizer);
		eventRepository.save(event);
		return event;
	}

	public EventResponse getEventService(Long id) {
		EventResponse eventResponse = new EventResponse();
		Event events = eventRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Event not found with id: " + id));

		eventResponse.setEventId(events.getEventId());
		eventResponse.setEventName(events.getEventName());
		eventResponse.setDescription(events.getDescription());
		eventResponse.setStartTime(events.getStartTime());
		eventResponse.setEndTime(events.getEndTime());
		eventResponse.setZoneId(events.getZoneId());
		eventResponse.setStarted(events.getStarted());
		return eventResponse;
	}

	public List<EventResponse> getAllEventsService() {

		List<EventResponse> eventResponseList = new ArrayList<>();
		List<Event> events = eventRepository.findAll();
		events.forEach(eves -> {
			EventResponse eventResponse = new EventResponse();
			eventResponse.setEventId(eves.getEventId());
			eventResponse.setEventName(eves.getEventName());
			eventResponse.setDescription(eves.getDescription());
			eventResponse.setStartTime(eves.getStartTime());
			eventResponse.setEndTime(eves.getEndTime());
			eventResponse.setZoneId(eves.getZoneId());
			eventResponse.setStarted(eves.getStarted());
			eventResponseList.add(eventResponse);

		});

		return eventResponseList;

	}
	
	public String deleteEventService(Long id)
	{
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
		eventRepository.delete(event);
		
		return "Event deleted";
	}
	
	public String updateEvent(@PathVariable Long id,  @RequestBody AddEvent addEvent)
	{
		String msg="";
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Event not found with id: " + id));
		
		event.setEventName(addEvent.getEventName());
		event.setDescription(addEvent.getDescription());
		event.setStartTime(addEvent.getStartTime());
		event.setEndTime(addEvent.getEndTime());
		event.setZoneId(addEvent.getZoneId());
		event.setStarted(addEvent.getStarted());
		eventRepository.save(event);
		msg="Event details has been updated";
		
		return msg;
	}

}

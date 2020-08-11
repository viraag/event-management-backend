package com.vg.eventmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vg.eventmanagement.models.Event;
import com.vg.eventmanagement.models.Participant;
import com.vg.eventmanagement.repositories.EventRepository;
import com.vg.eventmanagement.repositories.ParticipantRepository;
import com.vg.eventmanagement.requests.ParticipantResponse;

@Service
public class ParticipantService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	ParticipantRepository participantRepository;
	
	public Participant saveParticipant(ParticipantResponse participantResponse)
	{
		Participant participant=new Participant();
		Event event=eventRepository.findById(participantResponse.getEventId()).get();
	
		//participant.setId(participantResponse.getId());
		participant.setName(participantResponse.getName());
		participant.setEmail(participantResponse.getEmail());
		participant.setCheckedIn(participantResponse.getCheckedIn());
		participant.setEvent(event);	
		
		participantRepository.save(participant);
		
		return participant;
		
	}

}

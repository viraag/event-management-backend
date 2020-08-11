package com.vg.eventmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vg.eventmanagement.models.Participant;
import com.vg.eventmanagement.repositories.ParticipantRepository;
import com.vg.eventmanagement.requests.ParticipantResponse;
import com.vg.eventmanagement.services.ParticipantService;

@RestController
public class ParticipantController {
	
	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private ParticipantService participantService;

	public ParticipantController(ParticipantRepository participantRepository) {
		this.participantRepository = participantRepository;
	}
	
	@GetMapping("/participants")
	List<ParticipantResponse> getAllParticipants() {
		
		List<ParticipantResponse> participantResponseList=new ArrayList<>();
		List<Participant> participants=participantRepository.findAll();
		participants.forEach(part ->{
			ParticipantResponse participantResponse = new ParticipantResponse();
			participantResponse.setId(part.getId());
			participantResponse.setName(part.getName());
			participantResponse.setEmail(part.getEmail());
			participantResponse.setCheckedIn(part.getCheckedIn());
			
			participantResponseList.add(participantResponse);
			
			
		});
	   
		return participantResponseList;
		
	}
	
	
	@PostMapping("/saveParticipant")
	String newParticipant(@RequestBody ParticipantResponse participantResponse) {

			participantService.saveParticipant(participantResponse);
			return "Success";
		}
	

}



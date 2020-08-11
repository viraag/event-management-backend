package com.vg.eventmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vg.eventmanagement.common.NotFoundException;
import com.vg.eventmanagement.models.Organizer;
import com.vg.eventmanagement.repositories.OrganizerRepository;
import com.vg.eventmanagement.requests.OrganizerResponse;

@RestController
public class OrganizerController {
	@Autowired
	private OrganizerRepository organizerRepository;

	public OrganizerController(OrganizerRepository repos) {
		this.organizerRepository = repos;
	}

	@GetMapping("/organizers")
	List<OrganizerResponse> getAllOrganizers() {
		
		List<OrganizerResponse> organizerResponses=new ArrayList<>();
		List<Organizer> organizers=organizerRepository.findAll();
		organizers.forEach(orgs ->{
			OrganizerResponse organizerResponse = new OrganizerResponse();
			organizerResponse.setOrganizerName(orgs.getOrganizerName());
			organizerResponse.setId(orgs.getOrganizerId());
			organizerResponses.add(organizerResponse);
			
			
		});
	   
		return organizerResponses;
		
	}

	@GetMapping("/organizers/{id}")
	public OrganizerResponse getOrganizer(@PathVariable Long id) {

		OrganizerResponse organizerResponse = new OrganizerResponse();
		Organizer organizer = organizerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Organizer not found with id: " + id));
		
		organizerResponse.setOrganizerName(organizer.getOrganizerName());
        organizerResponse.setId(organizer.getOrganizerId());
		return organizerResponse;
	}

	@PostMapping("/saveOrganizers")
	Organizer newOrganizer(@RequestBody Organizer newOrganizer) {
		return organizerRepository.save(newOrganizer);
	}
	
	@DeleteMapping("/removeOrganizer/{id}")
	public String removeOrganizer(@PathVariable Long id)
	{
		Organizer organizer = organizerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Organizer not found with id: " + id));
		organizerRepository.delete(organizer);
		
		return "Deleted";
	}
	
	@PutMapping("/updateOrganizer/{id}")
	public String updateOrganizer(@PathVariable Long id,  @RequestBody OrganizerResponse organizerResponse)
	{
		String msg="";
		Organizer organizer = organizerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Organizer not found with id: " + id));
		
		if(organizer.getOrganizerName().equals(organizerResponse.getOrganizerName()))
		{
			msg="No Changes have been made";
		}
		else {
		organizer.setOrganizerName(organizerResponse.getOrganizerName());
		organizerRepository.save(organizer);
		msg="Orgaizer name has been updated";
		}
		return msg;
	}

}

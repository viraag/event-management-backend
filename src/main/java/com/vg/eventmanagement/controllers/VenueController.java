package com.vg.eventmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vg.eventmanagement.common.NotFoundException;
import com.vg.eventmanagement.models.Venue;
import com.vg.eventmanagement.repositories.VenueRepository;
import com.vg.eventmanagement.requests.VenueResponse;

@RestController
public class VenueController {
	
	@Autowired
	private VenueRepository venueRepository;
	
	public VenueController(VenueRepository repository)
	{
		this.venueRepository=repository;
	}


	@GetMapping("/venues")
	List<VenueResponse> getAllVenues() {
		
		List<VenueResponse> venuesResponsesList=new ArrayList<>();
		List<Venue> venues=venueRepository.findAll();
		venues.forEach(vens ->{
			VenueResponse venueResponse = new VenueResponse();
			venueResponse.setName(vens.getName());
			venueResponse.setCity(vens.getCity());
			venueResponse.setCountry(vens.getCountry());
			venueResponse.setId(vens.getId());
			venueResponse.setPostalCode(vens.getPostalCode());
			venueResponse.setState(vens.getState());
			venueResponse.setStreetAddress(vens.getStreetAddress());
			venueResponse.setStreetAddress2(vens.getStreetAddress2());
			venuesResponsesList.add(venueResponse);
		});
	   
		return venuesResponsesList;
		
	}
	
	@GetMapping("/venues/{id}")
	public VenueResponse getVenue(@PathVariable Long id) {

		VenueResponse venueResponse = new VenueResponse();
		Venue venue = venueRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Venue not found with id: " + id));
		
		venueResponse.setName(venue.getName());
		venueResponse.setCity(venue.getCity());
		venueResponse.setCountry(venue.getCountry());
		venueResponse.setId(venue.getId());
		venueResponse.setPostalCode(venue.getPostalCode());
		venueResponse.setState(venue.getState());
		venueResponse.setStreetAddress(venue.getStreetAddress());
		venueResponse.setStreetAddress2(venue.getStreetAddress2());
		return venueResponse;
	}
	
	@PostMapping("/saveVenues")
	Venue newVenue(@RequestBody Venue newVenue) {
		return venueRepository.save(newVenue);
	}

}

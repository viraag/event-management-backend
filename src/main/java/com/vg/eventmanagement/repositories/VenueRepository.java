package com.vg.eventmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vg.eventmanagement.models.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

}

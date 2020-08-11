package com.vg.eventmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vg.eventmanagement.models.Organizer;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

}

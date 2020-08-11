package com.vg.eventmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vg.eventmanagement.models.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}

package com.vg.eventmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vg.eventmanagement.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

}

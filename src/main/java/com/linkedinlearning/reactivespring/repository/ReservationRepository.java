package com.linkedinlearning.reactivespring.repository;

import com.linkedinlearning.reactivespring.model.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {
}

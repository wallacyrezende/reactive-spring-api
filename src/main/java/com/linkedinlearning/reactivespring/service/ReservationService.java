package com.linkedinlearning.reactivespring.service;

import com.linkedinlearning.reactivespring.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {

  Mono<Reservation> getReservation(String id);

  Mono<Reservation> createReservation(Reservation reservationMono);

  Mono<Reservation> updateReservation(Reservation reservationMono);

  Mono<Void> deleteReservation(String id);

  Flux<Reservation> listAllReservation();
}

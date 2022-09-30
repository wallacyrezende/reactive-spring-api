package com.linkedinlearning.reactivespring.service;

import com.linkedinlearning.reactivespring.model.Reservation;
import com.linkedinlearning.reactivespring.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

  private final ReservationRepository reservationRepository;

  @Override
  public Mono<Reservation> getReservation(String id) {
    return reservationRepository.findById(id)
        .switchIfEmpty(monoResponseStatusNotFoundException(Reservation.class));
  }

  @Override
  @Transactional
  public Mono<Reservation> createReservation(Reservation reservationMono) {
    return reservationRepository.save(reservationMono);
  }

  @Override
  public Mono<Reservation> updateReservation(Reservation reservationMono) {
    return getReservation(reservationMono.getId())
        .flatMap(reservation -> reservationRepository.save(reservationMono));
  }

  @Override
  public Mono<Void> deleteReservation(String id) {
    return getReservation(id)
        .flatMap(reservationRepository::delete)
        .then();
  }

  @Override
  public Flux<Reservation> listAllReservation() {
    return reservationRepository.findAll();
  }

  private <T> Mono<T> monoResponseStatusNotFoundException(Object obj) {
    return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%s not found", obj.getClass().getSimpleName())));
  }
}

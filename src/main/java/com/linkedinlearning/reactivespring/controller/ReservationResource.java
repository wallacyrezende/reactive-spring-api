package com.linkedinlearning.reactivespring.controller;

import com.linkedinlearning.reactivespring.model.Reservation;
import com.linkedinlearning.reactivespring.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ReservationResource.ROOM_V_1_RESERVATION)
@RequiredArgsConstructor
@CrossOrigin
public class ReservationResource {

  public static final String ROOM_V_1_RESERVATION = "/room/v1/reservation";

  private final ReservationService reservationService;

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Reservation> getReservationById(@PathVariable String id) {
    return reservationService.getReservation(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Reservation> createReservation(@RequestBody Reservation reservation) {
    return reservationService.createReservation(reservation);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Reservation> updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
    return reservationService.updateReservation(reservation.withId(id));
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteReservation(@PathVariable String id) {
    return reservationService.deleteReservation(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<Reservation> getAllReservations() {
    return reservationService.listAllReservation();
  }
}

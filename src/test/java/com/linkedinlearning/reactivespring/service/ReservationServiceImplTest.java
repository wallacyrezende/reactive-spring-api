package com.linkedinlearning.reactivespring.service;

import com.linkedinlearning.reactivespring.model.Reservation;
import com.linkedinlearning.reactivespring.repository.ReservationRepository;
import com.linkedinlearning.reactivespring.util.ReservationCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class ReservationServiceImplTest {

  ReservationService service;
  ReservationRepository repository;

  final Reservation reservation = ReservationCreator.createValidReservation();

  @BeforeEach
  void setUp() {
    this.repository = Mockito.mock(ReservationRepository.class);
    service = new ReservationServiceImpl(repository);
  }

  @Test
  void getReservation() {
    Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Mono.just(reservation));

    StepVerifier.create(service.getReservation("123"))
        .expectSubscription()
        .expectNext(reservation)
        .verifyComplete();
  }

  @Test
  void createReservation() {
    Mockito.when(repository.save(Mockito.any())).thenReturn(Mono.just(reservation));

    StepVerifier.create(service.createReservation(reservation))
        .expectSubscription()
        .expectNext(reservation)
        .verifyComplete();
  }

  @Test
  void updateReservation() {
    Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Mono.just(reservation));
    Mockito.when(repository.save(Mockito.any())).thenReturn(Mono.just(ReservationCreator.createValidUpdateReservation()));

    StepVerifier.create(service.updateReservation(ReservationCreator.createValidUpdateReservation()))
        .expectSubscription()
        .expectNext(ReservationCreator.createValidUpdateReservation())
        .verifyComplete();
  }

  @Test
  void deleteReservation() {
    Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Mono.just(reservation));
    Mockito.when(repository.delete(Mockito.any())).thenReturn(Mono.empty());

    StepVerifier.create(service.deleteReservation("123"))
        .expectSubscription()
        .verifyComplete();
  }

  @Test
  void listAllReservation() {
    Mockito.when(repository.findAll()).thenReturn(Flux.just(reservation));

    StepVerifier.create(service.listAllReservation())
        .expectSubscription()
        .expectNext(reservation)
        .verifyComplete();
  }
}
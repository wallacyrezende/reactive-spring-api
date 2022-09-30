package com.linkedinlearning.reactivespring.controller;

import static com.linkedinlearning.reactivespring.controller.ReservationResource.ROOM_V_1_RESERVATION;

import com.linkedinlearning.reactivespring.model.Reservation;
import com.linkedinlearning.reactivespring.util.ReservationCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@DisplayName("Reservation tests")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
class ReservationResourceTest {

  @Autowired
  WebTestClient client;
  final Reservation reservation = ReservationCreator.createValidReservation();

  @Test
  void createReservation() {
    client
        .post()
        .uri(ROOM_V_1_RESERVATION)
        .body(Mono.just(reservation), Reservation.class)
        .exchange()
        .expectStatus().isCreated()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(Reservation.class);
  }

  @Test
  void getAllReservations() {
    client
        .get()
        .uri(ROOM_V_1_RESERVATION)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Reservation.class);
  }
}
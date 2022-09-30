package com.linkedinlearning.reactivespring.util;

import com.linkedinlearning.reactivespring.model.Reservation;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservationCreator {

  public static Reservation createValidReservation() {
    return Reservation.builder()
        .roomNumber(123l)
        .checkIn(LocalDate.now())
        .checkOut( LocalDate.now().plus(10,ChronoUnit.DAYS))
        .price(150)
        .build();
  }

  public static Reservation createValidUpdateReservation() {
    return Reservation.builder()
        .id("1")
        .roomNumber(123l)
        .checkIn(LocalDate.now())
        .checkOut( LocalDate.now().plus(10,ChronoUnit.DAYS))
        .price(250)
        .build();
  }

}

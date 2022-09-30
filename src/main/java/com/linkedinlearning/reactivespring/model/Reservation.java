package com.linkedinlearning.reactivespring.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reservation {

  @NonNull
  private Long roomNumber;

  @NonNull
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate checkIn;

  @NonNull
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate checkOut;

  @NonNull
  private Integer price;

  @Id
  private String id;
}


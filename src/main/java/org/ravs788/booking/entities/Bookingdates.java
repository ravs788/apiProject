package org.ravs788.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder(setterPrefix = "set")
@AllArgsConstructor
public class Bookingdates {
  private String checkin;
  private String checkout;
}

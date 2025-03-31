package org.ravs788.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.ravs788.booking.entities.Bookingdates;

@Slf4j
@Data
@NoArgsConstructor
@Builder(setterPrefix = "set")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class BookingBody {
  private String firstname;
  private String lastname;
  private long totalprice;
  private boolean depositpaid;
  private Bookingdates bookingdates;
  private String additionalneeds;

  public static BookingBody getInstance() {
    Bookingdates bookingdates =
        Bookingdates.builder().setCheckin("2018-01-01").setCheckout("2019-01-01").build();

    BookingBody bookingBody =
        BookingBody.builder()
            .setFirstname("Jim")
            .setLastname("Brown")
            .setTotalprice(111)
            .setDepositpaid(true)
            .setAdditionalneeds("Breakfast")
            .setBookingdates(bookingdates)
            .build();

    log.info("Booking body: {}", bookingBody);
    return bookingBody;
  }
}

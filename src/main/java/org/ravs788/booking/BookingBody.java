package org.ravs788.booking;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.ravs788.booking.entities.Bookingdates;

@Slf4j
@Data
public class BookingBody {
  private String firstname;
  private String lastname;
  private long totalprice;
  private boolean depositpaid;
  private Bookingdates bookingdates;
  private String additionalneeds;

  public static BookingBody getInstance() {
    Bookingdates bookingdates = new Bookingdates();
    bookingdates.setCheckin("2018-01-01");
    bookingdates.setCheckout("2019-01-01");

    BookingBody bookingBody = new BookingBody();
    bookingBody.setFirstname("Jim");
    bookingBody.setLastname("Brown");
    bookingBody.setTotalprice(111);
    bookingBody.setDepositpaid(true);
    bookingBody.setAdditionalneeds("Breakfast");
    bookingBody.setBookingdates(bookingdates);

    log.info("Booking body: {}", bookingBody);
    return bookingBody;
  }
}

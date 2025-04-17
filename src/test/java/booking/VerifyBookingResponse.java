package booking;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.ravs788.booking.Booking;
import org.ravs788.booking.BookingResponse;

public class VerifyBookingResponse {
  private Response response;
  private SoftAssertions softAssertions;

  private VerifyBookingResponse(Response response) {
    this.response = response;
    this.softAssertions = new SoftAssertions();
  }

  public static VerifyBookingResponse assertThat(Response response) {
    return new VerifyBookingResponse(response);
  }

  public VerifyBookingResponse hasBookingId(Long bookingId) {
    BookingResponse bookingResponse =
        response.then().extract().response().as(BookingResponse.class);

    softAssertions
        .assertThat(bookingResponse.getBookingid())
        .describedAs("bookingId")
        .isEqualTo(bookingId);

    return this;
  }

  public VerifyBookingResponse hasBooking(Booking booking) {
    BookingResponse bookingResponse =
        response.then().extract().response().as(BookingResponse.class);

    softAssertions
        .assertThat(bookingResponse.getBooking())
        .describedAs("booking")
        .isEqualTo(booking);

    return this;
  }

  public void assertAll() {
    softAssertions.assertAll();
  }
}

package booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.ravs788.booking.BookingAPI;
import org.ravs788.booking.BookingBody;
import setup.TestSetup;

public class BookingTests extends TestSetup {
  @Test
  void assertAUserCanCreateANewBooking() {
    // Arrange
    BookingBody bookingBody = BookingBody.getInstance();

    // Act
    Response response = BookingAPI.newBooking(bookingBody);

    // Assert
    assertEquals(200, response.getStatusCode());
  }

  @Test
  void assertAUserCanCreateANewBookingWithOnlyMandatoryFields() {
    // Arrange
    BookingBody bookingBody = BookingBody.getInstance();
    bookingBody.setAdditionalneeds(null).setTotalprice(0);

    // Act
    Response response = BookingAPI.newBooking(bookingBody);

    // Assert
    assertEquals(200, response.getStatusCode());
  }
}

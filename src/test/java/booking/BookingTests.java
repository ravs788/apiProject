package booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.ravs788.authtoken.AuthAPI;
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
  void assertAUserCanCreateAndUpdateABooking() {
    // Arrange
    BookingBody bookingBody = BookingBody.getInstance();

    // Act
    Response response = BookingAPI.newBooking(bookingBody);
    String bookingId = response.getBody().jsonPath().getString("bookingid");

    // Assert
    assertEquals(200, response.getStatusCode());

    // Arrange
    bookingBody.setFirstname("James");
    bookingBody.setTotalprice(120);
    Response response1 = BookingAPI.updateBooking(bookingBody, bookingId);

    // Assert
    assertEquals(200, response1.getStatusCode());
  }

  @Test
  void assertAUserCanCreateANewBookingWithOnlyMandatoryFields() {
    // Arrange
    BookingBody bookingBody = BookingBody.getInstance();
    bookingBody.setAdditionalneeds(null);

    // Act
    Response response = BookingAPI.newBooking(bookingBody);

    // Assert
    assertEquals(200, response.getStatusCode());
  }

  @Test
  void testToken() {
    AuthAPI.getToken();
  }
}

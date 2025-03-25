package booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.ravs788.booking.BookingAPI;
import org.ravs788.booking.BookingBody;

public class BookingTests {
  @Test
  void assertAUserCanCreateANewBooking() {
    // Arrange
    BookingBody bookingBody = BookingBody.getInstance();

    // Act
    Response response = BookingAPI.newBooking(bookingBody);

    // Assert
    assertEquals(200, response.getStatusCode());
  }
}

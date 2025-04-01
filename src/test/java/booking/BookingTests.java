package booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ravs788.booking.BookingAPI;
import org.ravs788.booking.BookingBody;
import setup.TestSetup;

@Slf4j
public class BookingTests extends TestSetup {
  public String bookingId;
  public BookingBody bookingBody;

  // Setup: Create Booking
  @BeforeEach
  public void setup() {
    bookingBody = BookingBody.getInstance();

    // Act
    Response response = BookingAPI.newBooking(bookingBody);

    // Assert
    assertEquals(200, response.getStatusCode());

    // Set bookingId
    bookingId = response.getBody().jsonPath().getString("bookingid");
  }

  // TearDown: Delete the Booking
  @AfterEach
  public void tearDown() {
    Response response = BookingAPI.deleteBooking(bookingId);
    assertEquals(201, response.getStatusCode());
  }

  @Test
  void assertAUserCanFetchABooking() {
    // Act
    Response response = BookingAPI.getBooking(bookingId);
    String firstName = response.getBody().jsonPath().getString("firstname");

    // Assert
    assertEquals(200, response.getStatusCode());
    assertEquals("Jim", firstName);
  }

  @Test
  void assertAUserCanUpdateAnExistingBooking() {
    bookingBody.setLastname("Black");
    // Act
    Response response = BookingAPI.updateBooking(bookingBody, bookingId);
    String lastName = response.getBody().jsonPath().getString("lastname");

    // Assert
    assertEquals(200, response.getStatusCode());
    assertEquals("Black", lastName);
  }

  @Test
  void assertAUserCanPartiallyUpdateAnExistingBooking() {
    BookingBody partialBookingBody =
        BookingBody.builder().setFirstname("Ravi").setLastname("Shankar").build();

    log.info("partialBookingBody : {}", partialBookingBody);
    // Act
    Response response = BookingAPI.patchBooking(partialBookingBody, bookingId);
    String firstName = response.getBody().jsonPath().getString("firstname");
    String lastName = response.getBody().jsonPath().getString("lastname");

    // Assert
    assertEquals(200, response.getStatusCode());
    assertEquals("Ravi", firstName);
    assertEquals("Shankar", lastName);
  }

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

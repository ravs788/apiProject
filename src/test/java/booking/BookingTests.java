package booking;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import assertion.VerifyResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ravs788.booking.Booking;
import org.ravs788.booking.BookingAPI;
import setup.TestSetup;

@Slf4j
public class BookingTests extends TestSetup {
  public Long bookingId;
  public Booking booking;

  // Setup: Create Booking
  @BeforeEach
  public void setup() {
    booking = Booking.getInstance();

    // Act
    Response response = BookingAPI.newBooking(booking);

    // Assert
    VerifyResponse.assertThat(response).statusCodeIs(SC_OK).containsValue("Breakfast").assertAll();

    // Set bookingId
    bookingId = response.getBody().jsonPath().getLong("bookingid");

    VerifyBookingResponse.assertThat(response)
        .hasBookingId(bookingId)
        .hasBooking(booking)
        .assertAll();
  }

  // TearDown: Delete the Booking
  @AfterEach
  public void tearDown() {
    Response response = BookingAPI.deleteBooking(bookingId);
    VerifyResponse.assertThat(response).statusCodeIs(SC_CREATED).assertAll();
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
    booking.setLastname("Black");
    // Act
    Response response = BookingAPI.updateBooking(booking, bookingId);
    String lastName = response.getBody().jsonPath().getString("lastname");

    // Assert
    assertEquals(200, response.getStatusCode());
    assertEquals("Black", lastName);
  }

  @Test
  void assertAUserCanPartiallyUpdateAnExistingBooking() {
    Booking partialBooking = Booking.builder().setFirstname("Ravi").setLastname("Shankar").build();

    log.info("partialBooking : {}", partialBooking);
    // Act
    Response response = BookingAPI.patchBooking(partialBooking, bookingId);
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
    Booking booking = Booking.getInstance();

    // Act
    Response response = BookingAPI.newBooking(booking);

    // Assert
    assertEquals(200, response.getStatusCode());
  }
}

package org.ravs788.booking;

import static io.restassured.RestAssured.given;

import com.typesafe.config.Config;
import io.restassured.response.Response;
import org.ravs788.authtoken.AuthAPI;
import org.ravs788.basespec.BaseSpec;
import org.ravs788.config.TestEnvFactory;

public class BookingAPI {
  private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();

  public static Response newBooking(Booking booking) {
    return given()
        .spec(BaseSpec.get().build())
        .body(booking)
        .log()
        .all()
        .when()
        .post(CONFIG.getString("BOOKING_ENDPOINT"))
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response getBooking(Long bookingId) {
    return given()
        .spec(BaseSpec.get().build())
        .log()
        .all()
        .when()
        .get(CONFIG.getString("BOOKING_ID_ENDPOINT"), bookingId)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response updateBooking(Booking booking, Long bookingId) {
    return given()
        .spec(BaseSpec.get(AuthAPI.getToken()).build())
        .body(booking)
        .log()
        .all()
        .when()
        .put(CONFIG.getString("BOOKING_ID_ENDPOINT"), bookingId)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response patchBooking(Booking booking, Long bookingId) {
    return given()
        .spec(BaseSpec.get(AuthAPI.getToken()).build())
        .body(booking)
        .log()
        .all()
        .when()
        .patch(CONFIG.getString("BOOKING_ID_ENDPOINT"), bookingId)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public static Response deleteBooking(Long bookingId) {
    return given()
        .spec(BaseSpec.get(AuthAPI.getToken()).build())
        .log()
        .all()
        .when()
        .delete(CONFIG.getString("BOOKING_ID_ENDPOINT"), bookingId)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }
}

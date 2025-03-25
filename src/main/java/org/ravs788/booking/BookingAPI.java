package org.ravs788.booking;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class BookingAPI {
  public static Response newBooking(BookingBody bookingBody) {
    return given()
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .baseUri("https://restful-booker.herokuapp.com")
        .body(bookingBody)
        .log()
        .all()
        .when()
        .post("/booking")
        .then()
        .log()
        .all()
        .extract()
        .response();
  }
}

package booking;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class BasicRestAssuredTests {
  @Test
  public void assertThatUserCanCreateABooking() {
    String jsonBody =
        "{\n"
            + "  \"firstname\": \"Jim\",\n"
            + "  \"lastname\": \"Brown\",\n"
            + "  \"totalprice\": 111,\n"
            + "  \"depositpaid\": true,\n"
            + "  \"bookingdates\": {\n"
            + "    \"checkin\": \"2018-01-01\",\n"
            + "    \"checkout\": \"2019-01-01\"\n"
            + "  },\n"
            + "  \"additionalneeds\": \"Breakfast\"\n"
            + "}";

    given()
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .baseUri("https://restful-booker.herokuapp.com")
        .body(jsonBody)
        .when()
        .post("/booking")
        .then()
        .statusCode(200)
        .body(
            "booking.firstname",
            equalTo("Jim"),
            "booking.lastname",
            equalTo("Brown"),
            "booking.totalprice",
            equalTo(111),
            "booking.depositpaid",
            equalTo(true),
            "booking.additionalneeds",
            equalTo("Breakfast"),
            "booking.bookingdates.checkin",
            equalTo("2018-01-01"),
            "booking.bookingdates.checkout",
            equalTo("2019-01-01"))
        .extract()
        .response()
        .prettyPrint();
    //                .body("lotto.lottoId", equalTo(5),
    //                        "lotto.winners.winnerId", hasItems(23, 54));

  }

  @Test
  public void assertThatUserCanCreateABookingmandatorydetailsonly() {
    String jsonBody =
        "{\n"
            + "  \"firstname\": \"Jim\",\n"
            + "  \"lastname\": \"Brown\",\n"
            + "  \"totalprice\": 111,\n"
            + "  \"depositpaid\": true,\n"
            + "  \"bookingdates\": {\n"
            + "    \"checkin\": \"2018-01-01\",\n"
            + "    \"checkout\": \"2019-01-01\"\n"
            + "  }\n"
            + "}";

    given()
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .baseUri("https://restful-booker.herokuapp.com")
        .body(jsonBody)
        .when()
        .post("/booking")
        .then()
        .statusCode(200)
        .body(
            "booking.firstname",
            equalTo("Jim"),
            "booking.lastname",
            equalTo("Brown"),
            "booking.totalprice",
            equalTo(111),
            "booking.depositpaid",
            equalTo(true),
            "booking.bookingdates.checkin",
            equalTo("2018-01-01"),
            "booking.bookingdates.checkout",
            equalTo("2019-01-01"))
        .extract()
        .response()
        .prettyPrint();
    //                .body("lotto.lottoId", equalTo(5),
    //                        "lotto.winners.winnerId", hasItems(23, 54));

  }

  @Test
  public void assertThatUserCannotCreateABookingMissingMandatorydetails() {
    String jsonBody =
        "{\n"
            + "  \"firstname\": \"Jim\",\n"
            + "  \"lastname\": \"Brown\",\n"
            + "  \"depositpaid\": true,\n"
            + "  \"bookingdates\": {\n"
            + "    \"checkin\": \"2018-01-01\",\n"
            + "    \"checkout\": \"2019-01-01\"\n"
            + "  },\n"
            + "  \"additionalneeds\": \"Breakfast\"\n"
            + "}";

    given()
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .baseUri("https://restful-booker.herokuapp.com")
        .body(jsonBody)
        .when()
        .post("/booking")
        .then()
        .statusCode(500)
        .extract()
        .response()
        .prettyPrint();
    //                .body("lotto.lottoId", equalTo(5),
    //                        "lotto.winners.winnerId", hasItems(23, 54));

  }
}

package org.ravs788.authtoken;

import static io.restassured.RestAssured.given;

import com.typesafe.config.Config;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.ravs788.config.TestEnvFactory;

@Slf4j
public class AuthAPI {
  private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();

  public static String getToken() {
    AuthBody authBody = AuthBody.getInstance();

    Response response =
        given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .baseUri(CONFIG.getString("BASE_URL"))
            .body(authBody)
            .log()
            .all()
            .when()
            .post(CONFIG.getString("AUTH_ENDPOINT"))
            .then()
            .log()
            .all()
            .extract()
            .response();

    log.info("token :{}", response.body().jsonPath().getString("token"));
    return response.body().jsonPath().getString("token");
  }
}

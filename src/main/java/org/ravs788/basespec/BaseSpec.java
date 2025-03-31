package org.ravs788.basespec;

import com.typesafe.config.Config;
import io.restassured.builder.RequestSpecBuilder;
import org.ravs788.config.TestEnvFactory;

public class BaseSpec {
  private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();

  public static RequestSpecBuilder get() {
    return new RequestSpecBuilder()
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .setBaseUri(CONFIG.getString("BASE_URL"));
  }

  public static RequestSpecBuilder get(String token) {
    return get()
        //                .addHeader("Cookie","token="+token)
        .addCookie("token", token);
  }
}

package assertion;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

public class VerifyResponse {
  private Response response;
  private SoftAssertions softAssertions;

  private VerifyResponse(Response response) {
    this.response = response;
    this.softAssertions = new SoftAssertions();
  }

  public static VerifyResponse assertThat(Response response) {
    return new VerifyResponse(response);
  }

  public VerifyResponse statusCodeIs(int statusCode) {
    Assertions.assertThat(response.getStatusCode()).describedAs("statusCode").isEqualTo(statusCode);

    return this;
  }

  public VerifyResponse containsValue(String value) {
    softAssertions
        .assertThat(response.body().asString())
        .describedAs("responseBody")
        .contains(value);

    return this;
  }

  public void assertAll() {
    softAssertions.assertAll();
  }
}

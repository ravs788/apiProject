import static org.junit.jupiter.api.Assertions.*;

import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.ravs788.annotations.SmokeTest;
import org.ravs788.config.TestEnvFactory;
import setup.TestSetup;

@Tag("service-B")
@Slf4j
public class TestServiceB extends TestSetup {

  @SmokeTest
  void assertThatTrueIsTrue() {
    assertTrue(true, "true is true");
  }

  @SmokeTest
  void assertThatTestForChosenEnvRuns() {
    Config CONFIG = TestEnvFactory.getInstance().getConfig();
    String expectedEnv = CONFIG.getString("TEST_ENV");
    log.info("expectedEnv is {}", expectedEnv);
    assertEquals(expectedEnv, CONFIG.getString("TEST_ENV"), "TEST_ENV");
  }
}

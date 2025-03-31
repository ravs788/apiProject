package org.ravs788.extensions;

import com.typesafe.config.Config;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.ravs788.config.TestEnvFactory;
import org.ravs788.extensions.report.PublishResults;
import org.ravs788.extensions.report.TestRunMetaData;

@Slf4j
public class ReportingExtension implements AfterEachCallback {

  private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();
  private static final Boolean PUBLISH_RESULTS_TO_ELASTIC =
      CONFIG.getBoolean("PUBLISH_RESULTS_TO_ELASTIC");

  public void afterEach(ExtensionContext context) throws IOException {
    if (PUBLISH_RESULTS_TO_ELASTIC) {
      TestRunMetaData testRunMetaData = new TestRunMetaData().setBody(context);
      PublishResults.toElastic(testRunMetaData);
    } else {
      log.info("Skipping publishing results to elastic");
    }
  }
}

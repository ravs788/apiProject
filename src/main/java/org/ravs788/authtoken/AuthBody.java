package org.ravs788.authtoken;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.typesafe.config.Config;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.ravs788.config.TestEnvFactory;

@Slf4j
@Data
@NoArgsConstructor
@Builder(setterPrefix = "set")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class AuthBody {
  private String username;
  private String password;
  private static final Config CONFIG = TestEnvFactory.getInstance().getConfig();

  public static AuthBody getInstance() {

    return AuthBody.builder()
        .setUsername(CONFIG.getString("ADMIN_USERNAME"))
        .setPassword(CONFIG.getString("ADMIN_PASSWORD"))
        .build();
  }
}

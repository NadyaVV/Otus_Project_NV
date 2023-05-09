package pageobject;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestLogger {

  public static final Logger LOGGER = LogManager.getLogManager().getLogger(TestLogger.class.getName());

  private TestLogger() {
  }
}

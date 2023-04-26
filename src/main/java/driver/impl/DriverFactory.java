package driver.impl;

import exceptions.DriverTypeNotSupported;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {

  private final String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

  @Override
  public EventFiringWebDriver getDriver() {
    if ("chrome".equals(this.browserType)) {
      return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
    }
    try {
      throw new DriverTypeNotSupported(this.browserType);
    } catch (DriverTypeNotSupported ex) {
      ex.printStackTrace();
      return null;
    }
  }

}

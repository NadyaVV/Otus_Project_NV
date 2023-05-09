package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {

  private final String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

  @Override
  public EventFiringWebDriver getDriver() {
    if (this.browserType.equals("chrome")) {
      return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
    } else if (this.browserType.equals("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      return new EventFiringWebDriver(new FirefoxWebDriver().newDriver());
    } else {
      try {
        throw new DriverTypeNotSupported(this.browserType);
      } catch (DriverTypeNotSupported ex) {
        ex.printStackTrace();
        return null;
      }
    }
  }
}

package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public interface IDriver {
  String REMOTE_URL = System.getProperty("webdriver.base.url");

  WebDriver newDriver();

  default URL getRemoteUrl() {
    try {
      return new URL(REMOTE_URL);
    } catch (MalformedURLException e) {
      return null;
    }
  }

  default void downloadLocalWebDriver(DriverManagerType driverType) throws DriverTypeNotSupported {
    Config wdmConfig = WebDriverManager.globalConfig();
    wdmConfig.setAvoidBrowserDetection(true);

    WebDriverManager.getInstance(driverType).setup();
  }
}

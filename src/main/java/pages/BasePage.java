package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;
import pageobject.PageObject;

public abstract class BasePage<T> extends PageObject<T> {

  private final String baseUrl = System.getProperty("webdriver.base.url", "https://otus.ru");

  public BasePage(WebDriver driver) {
    super(driver);
  }

  private String getPath() {
    Path path = getClass().getAnnotation(Path.class);
    if (path != null) {
      return path.value();
    }
    return "";
  }

  public T open() {
    driver.get(baseUrl + getPath());
    return (T) this;
  }
}

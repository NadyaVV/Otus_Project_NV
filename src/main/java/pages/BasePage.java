package pages;

import annotations.Path;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.PageObject;

public abstract class BasePage<T> extends PageObject<T> {

  private final String baseUrl = System.getProperty("webdriver.base.url", "https://otus.ru");

  @FindBy(tagName = "h1")
  private WebElement header;

  @Step("Check header")
  public T headerShouldBeSameAs(String header) {
    Assertions.assertEquals(header, this.header.getText());

    return (T) this;
  }

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

  @Step("Open Main page")
  public T open() {
    driver.get(baseUrl + getPath());
    return (T) this;
  }
}

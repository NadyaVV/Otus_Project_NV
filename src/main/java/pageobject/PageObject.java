package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class PageObject<T> {


  protected WebDriver driver;
  protected Actions actions;

  public PageObject(WebDriver driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
  }
}

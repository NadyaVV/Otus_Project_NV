package components;

import org.openqa.selenium.WebDriver;
import pageobject.PageObject;

public abstract class AbsBaseComponent extends PageObject<AbsBaseComponent> {

  public AbsBaseComponent(WebDriver driver){
    super(driver);
  }
}

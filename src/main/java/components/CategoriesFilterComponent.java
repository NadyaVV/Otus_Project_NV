package components;

import data.ECoursesCategoryData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoriesFilterComponent extends AbsBaseComponent {

  public CategoriesFilterComponent(WebDriver driver) {
    super(driver);
  }

  private String checkboxInputLocatorTemplate = "//label[text()='%s']/..//input[@type='checkbox']";

  public CategoriesFilterComponent checkboxStatusShouldBe(ECoursesCategoryData coursesCategoryData, boolean expectedCheckboxState) {
    String locator = String.format(checkboxInputLocatorTemplate, coursesCategoryData.getName());
    Assertions.assertEquals(driver.findElement(By.xpath(locator)).isSelected(), expectedCheckboxState, "Checkbox status is wrong");

    return this;
  }
}

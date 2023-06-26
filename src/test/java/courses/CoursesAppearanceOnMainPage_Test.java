package courses;

import annotations.Driver;
import exceptions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class CoursesAppearanceOnMainPage_Test {
  @Driver
  private WebDriver driver;

  @Test
  public void checkboxCategoryCourses() {
    new MainPage(driver)
        .open()
        .findCourseByName("Системный аналитик. Advanced")
        .moveToElementHighlightAndClick("Системный аналитик. Advanced")
        .headerShouldBeSameAs("Системный аналитик. Advanced");
  }

  @Test
  public void checkboxEarliestCourseName() {
    new MainPage(driver)
        .open()
        .checkEarliestCourseName("Delivery Manager");
  }

  @Test
  public void checkboxOldestCourseName() {
    new MainPage(driver)
        .open()
        .checkOldestCourseName("Системный аналитик. Advanced");
  }
}

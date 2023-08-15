package courses;

import annotations.Driver;
import exceptions.UIExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class CoursesAppearanceOnMainPage_Test {
  @Driver
  private WebDriver driver;

  @Test
  @DisplayName("Check header on page for exact course")
  public void checkboxCategoryCourses() {
    new MainPage(driver)
        .open()
        .findCourseByName("Специализация Machine Learning")
        .moveToElementHighlightAndClick("Специализация Machine Learning")
        .headerShouldBeSameAs("Специализация Machine Learning");
  }

  @Test
  @DisplayName("Find the earliest course")
  public void checkboxEarliestCourseName() {
    new MainPage(driver)
        .open()
        .checkEarliestCourseName("Delivery Manager");
  }

  @Test
  @DisplayName("Find the oldest course")
  public void checkboxOldestCourseName() {
    new MainPage(driver)
        .open()
        .checkOldestCourseName("Специализация Machine Learning");
  }
}

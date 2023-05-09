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
        .findCourseByName("Team Lead")
        .moveToElementHighlightAndClick("Team Lead")
        .headerShouldBeSameAs("Team Lead");
  }

  @Test
  public void checkboxEarliestCourseName() {
    new MainPage(driver)
        .open()
        .checkEarliestCourseName("Machine Learning. Advanced");
  }

  @Test
  public void checkboxOldestCourseName() {
    new MainPage(driver)
        .open()
        .checkOldestCourseName("Разработка прикладного ПО на Qt и ОС Аврора");
  }
}

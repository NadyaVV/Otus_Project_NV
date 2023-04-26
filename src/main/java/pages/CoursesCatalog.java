package pages;

import annotations.Path;
import annotations.UrlTemplate;
import annotations.Urls;
import org.openqa.selenium.WebDriver;

@Path("/courses")
@Urls(
    @UrlTemplate(name = "categories", value = "?categories=%1")
)
public class CoursesCatalog extends BasePage<CoursesCatalog> {

  public CoursesCatalog(WebDriver driver) {
    super(driver);
  }
}

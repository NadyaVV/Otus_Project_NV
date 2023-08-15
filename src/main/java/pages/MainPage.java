package pages;

import annotations.Path;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.stream.Collectors;

@Path("/")
public class MainPage extends BasePage<MainPage> {

  public MainPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = ".fgNPoG h5")
  private List<WebElement> getLessonsItemLocator;

  @FindBy(xpath = "//div[contains(@class, 'lessons__new-item-start')]")
  List<WebElement> courseStartDates;

  private static final String COURSE_ITEM = "//div[contains(@class, 'lessons__new-item-title')][contains(text(),'%s')]";

  private static final String COURSE_NAME = "//div[@class= 'lessons__new-item-start']/ancestor::div/div[contains(@class, 'lessons__new-item-title')]";


  private List<GregorianCalendar> courseDates = new ArrayList<>();

  private Map<GregorianCalendar, String> courseDatesAndNames = new HashMap<>();

  @Step("Find course by name")
  public MainPage findCourseByName(String courseName) {
    List<String> actualCourseNames = getLessonsItemLocator.stream().map(WebElement::getText)
        .filter(el -> el.equals(courseName)).collect(Collectors.toList());
    Assertions.assertNotEquals(actualCourseNames.size(), 0, "Actual Course list can't be empty.");
    Assertions.assertEquals(courseName, actualCourseNames.get(0), "Course names are not matched.");
    return this;
  }

  @Step("Get Course Month")
  public int getCourseMonth(String[] splittedDates) {
    switch (splittedDates[2]) {
      case "января":
        return Calendar.JANUARY;
      case "февраля":
        return Calendar.FEBRUARY;
      case "марта":
        return Calendar.MARCH;
      case "апреля":
        return Calendar.APRIL;
      case "мая":
        return Calendar.MAY;
      case "июня":
        return Calendar.JUNE;
      case "июля":
        return Calendar.JULY;
      case "августа":
        return Calendar.AUGUST;
      case "сентября":
        return Calendar.SEPTEMBER;
      case "октября":
        return Calendar.OCTOBER;
      case "ноября":
        return Calendar.NOVEMBER;
      case "декабря":
        return Calendar.DECEMBER;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Step("Find course by date")
  public void fillCourseDates(List<String> coursesStartDates) {
    int year, month, day;

    for (String startDate : coursesStartDates) {
      String[] splittedDates = startDate.split("\\s+");
      year = getCourseYear(splittedDates);
      month = getCourseMonth(splittedDates);
      day = Integer.parseInt(splittedDates[1]);

      courseDates.add(new GregorianCalendar(year, month, day));

    }
  }

  @Step("Get course year")
  public int getCourseYear(String[] splittedDates) {
    if (splittedDates.length <= 3) {
      return Calendar.getInstance().get(Calendar.YEAR);
    } else {
      return Integer.parseInt(splittedDates[3]);
    }
  }

  @Step("Fill course date and name")
  public void fillCourseDatesAndNames() {
    List<WebElement> courseNames = driver.findElements(By.xpath(COURSE_NAME));
    for (int i = 0; i < courseNames.size(); i++) {
      courseDatesAndNames.put(courseDates.get(i), courseNames.get(i).getText());
    }
  }

  @Step("Get oldest course date")
  public GregorianCalendar getOldestCourseDate() {
    GregorianCalendar minGc = new GregorianCalendar();
    minGc.setTime(new Date(Long.MIN_VALUE));
    return courseDates.stream().reduce(minGc, (left, right) -> left.compareTo(right) > 0 ? left : right);
  }

  @Step("Get earliest course date")
  public GregorianCalendar getEarliestCourseDate() {
    GregorianCalendar maxGc = new GregorianCalendar();
    maxGc.setTime(new Date(Long.MAX_VALUE));
    return courseDates.stream().reduce(maxGc, (left, right) -> left.compareTo(right) < 0 ? left : right);
  }

  public List<String> getStartDatesStrings(List<WebElement> coursesStartDate) {
    List<String> coursesStartDates = new ArrayList<>();
    coursesStartDate.forEach(element -> coursesStartDates.add(element.getText()));
    return coursesStartDates;
  }

  @Step("Check earliest course name")
  public void checkEarliestCourseName(String expectedEarliestCourseName) {
    fillCourseDates(getStartDatesStrings(courseStartDates));
    fillCourseDatesAndNames();
    Assertions.assertEquals(expectedEarliestCourseName, courseDatesAndNames.get(getEarliestCourseDate()),
        "Course names don't match!");
  }

  @Step("Check Oldest course name")
  public void checkOldestCourseName(String expectedOldestCourseName) {
    fillCourseDates(getStartDatesStrings(courseStartDates));
    fillCourseDatesAndNames();
    Assertions.assertEquals(expectedOldestCourseName, courseDatesAndNames.get(getOldestCourseDate()),
        "Course names don't match!");
  }

  @Step("Move to element, highlight and click")
  public MainPage moveToElementHighlightAndClick(String text) {
    WebElement element = driver.findElement(By.xpath(String.format(COURSE_ITEM, text)));
    scrollIntoView(element);
    actions.moveToElement(element).build().perform();
    element.click();
    return this;
  }

  public void scrollIntoView(WebElement element){
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  }
}

package pages;

import static pageobject.TestLogger.LOGGER;

import annotations.Path;
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

  @FindBy(css = ".lessons__new-item-title")
  private List<WebElement> getLessonsItemLocator;

  @FindBy(xpath = "//div[contains(@class, 'lessons__new-item-start')]")
  List<WebElement> courseStartDates;

  private static final String COURSE_ITEM = "//div[contains(@class, 'lessons__new-item-title')][contains(text(),'%s')]";

  private static final String COURSE_NAME = "//div[@class= 'lessons__new-item-start']/../../div[contains(@class, 'lessons__new-item-title')]";


  private List<GregorianCalendar> courseDates = new ArrayList<>();

  private Map<GregorianCalendar, String> courseDatesAndNames = new HashMap<>();

  public MainPage findCourseByName(String courseName) {
    List<String> actualCourseNames = getLessonsItemLocator.stream().map(WebElement::getText)
        .filter(el -> el.equals(courseName)).collect(Collectors.toList());
    if (actualCourseNames.isEmpty()) {
      LOGGER.warning(String.format("No courses found by text %s", courseName));
    }
    Assertions.assertEquals(courseName, actualCourseNames.get(0), "Course names are not matched.");
    return this;
  }

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

  public void fillCourseDates(List<String> coursesStartDates) {
    int year, month, day;

    for (String startDate : coursesStartDates) {
      String[] splittedDates = startDate.split(" ");
      year = getCourseYear(splittedDates);
      month = getCourseMonth(splittedDates);
      day = Integer.parseInt(splittedDates[1]);

      courseDates.add(new GregorianCalendar(year, month, day));

    }
  }

  public int getCourseYear(String[] splittedDates) {
    if (splittedDates.length <= 3) {
      return Calendar.getInstance().get(Calendar.YEAR);
    } else {
      return Integer.parseInt(splittedDates[3]);
    }
  }

  public void fillCourseDatesAndNames() {
    List<WebElement> courseNames = driver.findElements(By.xpath(COURSE_NAME));
    for (int i = 0; i < courseNames.size(); i++) {
      courseDatesAndNames.put(courseDates.get(i), courseNames.get(i).getText());
    }
  }

  public GregorianCalendar getOldestCourseDate() {
    GregorianCalendar minGc = new GregorianCalendar();
    minGc.setTime(new Date(Long.MIN_VALUE));
    return courseDates.stream().reduce(minGc, (left, right) -> left.compareTo(right) > 0 ? left : right);
  }

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

  public void checkEarliestCourseName(String expectedEarliestCourseName) {
    fillCourseDates(getStartDatesStrings(courseStartDates));
    fillCourseDatesAndNames();
    Assertions.assertEquals(expectedEarliestCourseName, courseDatesAndNames.get(getEarliestCourseDate()),
        "Course names don't match!");
  }

  public void checkOldestCourseName(String expectedOldestCourseName) {
    fillCourseDates(getStartDatesStrings(courseStartDates));
    fillCourseDatesAndNames();
    Assertions.assertEquals(expectedOldestCourseName, courseDatesAndNames.get(getOldestCourseDate()),
        "Course names don't match!");
  }

  public MainPage moveToElementHighlightAndClick(String text) {
    WebElement element = driver.findElement(By.xpath(String.format(COURSE_ITEM, text)));
    actions.moveToElement(element).build().perform();
    element.click();
    return this;
  }
}

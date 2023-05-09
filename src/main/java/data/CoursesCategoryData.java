package data;

public enum CoursesCategoryData {

  Programming("Программирование");

  private String name;

  CoursesCategoryData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

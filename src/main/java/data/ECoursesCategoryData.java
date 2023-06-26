package data;

public enum ECoursesCategoryData {

  Programming("Программирование");

  private String name;

  ECoursesCategoryData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

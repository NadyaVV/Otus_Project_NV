package data;

public enum EMonths {

  JANUARY("января"),
  FEBRUARY("февраля"),
  MARCH("марта"),
  APRIL("апреля"),
  MAY("мая"),
  JUNE("июня"),
  JULY("июля"),
  AUGUST("августа"),
  SEPTEMBER("сентября"),
  OCTOBER("октября"),
  NOVEMBER("ноября"),
  DECEMBER("декабря");

  private String monthName;

  EMonths(String monthNames) {
    this.monthName = monthNames;
  }

  public String getName() {
    return monthName;
  }
}

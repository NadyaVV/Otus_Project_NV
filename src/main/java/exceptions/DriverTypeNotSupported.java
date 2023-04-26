package exceptions;

public class DriverTypeNotSupported extends Exception {
  public DriverTypeNotSupported(String browserType) {
    super(String.format("Browser %s is not supported", browserType));
  }
}

package lambdasinaction.chap8;

public class Strategy {

  interface ValidationStrategy {
    public boolean execute(String s);
  }

  static class IsAllLowerCase implements ValidationStrategy {
    public boolean execute(String s) {
      return s.matches("[a-z]+");
    }
  }

  static class IsNumeric implements ValidationStrategy {
    public boolean execute(String s) {
      return s.matches("\\d+");
    }
  }

  static class Validator {
    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy v) {
      this.strategy = v;
    }

    public boolean validate(String s) {
      return strategy.execute(s);
    }
  }
}
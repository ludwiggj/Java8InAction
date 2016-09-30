package lambdasinaction.chap10;

import java.util.Objects;

public class Insurance {

  public static final String UNKNOWN = "Unknown";
  public static final String CHEAPO = "Cheapo Co";

  private String name;

  public Insurance(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Insurance)) return false;
    Insurance insurance = (Insurance) o;
    return Objects.equals(name, insurance.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("Insurance { name='%s' }", name);
  }
}

package lambdasinaction.chap1;

public class Apple {
  private int weight = 0;
  private String color = "";

  public Apple(int weight, String color) {
    this.weight = weight;
    this.color = color;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Apple)) return false;

    Apple apple = (Apple) o;

    if (weight != apple.weight) return false;
    return color.equals(apple.color);

  }

  @Override
  public int hashCode() {
    int result = weight;
    result = 31 * result + color.hashCode();
    return result;
  }

  public String toString() {
    return "Apple{" +
        "color='" + color + '\'' +
        ", weight=" + weight +
        '}';
  }
}

package lambdasinaction.chap1;

public class Fruit {
  protected int weight = 0;
  protected String colour = "";

  public Fruit(int weight, String colour) {
    this.colour = colour;
    this.weight = weight;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public String getColour() {
    return colour;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Fruit)) return false;

    Fruit fruit = (Fruit) o;

    if (weight != fruit.weight) return false;
    return colour.equals(fruit.colour);

  }

  @Override
  public int hashCode() {
    int result = weight;
    result = 31 * result + colour.hashCode();
    return result;
  }
}
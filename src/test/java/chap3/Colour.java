package chap3;

public class Colour {
  int red;
  int green;
  int blue;

  public Colour(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Colour)) return false;

    Colour colour = (Colour) o;

    if (red != colour.red) return false;
    if (green != colour.green) return false;
    return blue == colour.blue;

  }

  @Override
  public int hashCode() {
    int result = red;
    result = 31 * result + green;
    result = 31 * result + blue;
    return result;
  }

  @Override
  public String toString() {
    return "Colour {" + "red=" + red + ", green=" + green + ", blue=" + blue + "}";
  }
}

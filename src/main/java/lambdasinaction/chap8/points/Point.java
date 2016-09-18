package lambdasinaction.chap8.points;

import java.util.Comparator;
import java.util.Objects;

import static java.util.Comparator.comparing;

public class Point {
  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point)) return false;
    Point point = (Point) o;
    return x == point.x &&
        y == point.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return String.format("Point {x=%d,y=%d}", x, y);
  }

  public Point moveRightBy(int x) {
    return new Point(this.x + x, this.y);
  }

  public Point moveRightBy10() {
    return moveRightBy(10);
  }

  public Point translate(Point p) {
    return new Point(x + p.getX(), y + p.getY());
  }

  public Point negate() {
    return new Point(-this.x, -this.y);
  }

  public final static Comparator<Point> compareByXAndThenY =
      comparing(Point::getX).thenComparing(Point::getY);
}
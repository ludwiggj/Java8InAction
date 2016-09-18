package lambdasinaction.chap8.points;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Points {

  private List<Point> points;

  public Points(List<Point> points) {
    this.points = points;
  }

  public List<Point> getPoints() {
    return points;
  }

  public Points moveRightBy(int x) {
    List<Point> movedPoints = points.stream()
        .map(p -> p.moveRightBy(x))
        .collect(toList());

    return new Points(movedPoints);
  }

  public Points moveRightByTake2(int x) {
    List<Point> movedPoints = points.stream()
        .map(p -> new Point(p.getX() + x, p.getY()))
        .collect(toList());

    return new Points(movedPoints);
  }

  public Points moveRightBy10() {
    List<Point> movedPoints = points.stream()
        .map(Point::moveRightBy10)
        .collect(toList());

    return new Points(movedPoints);
  }

  public Points moveRightByTake3(int x) {
    StreamMappableWithParameter<Point> s = new StreamMappableWithParameter<>(points.stream());

    return new Points(s.mapWithParameter((p, z) -> p.moveRightBy(z), x).collect(toList()));
  }

  public Points moveRightByTake4(int x) {
    StreamMappableWithParameter<Point> s = new StreamMappableWithParameter<>(points.stream());

    return new Points(s.mapWithParameter(Point::moveRightBy, x).collect(toList()));
  }

  public Points doubleUp() {
    return new Points(points.stream().map(p -> p.translate(p)).collect(toList()));
  }

  public Points negate() {
    return new Points(points.stream().map(Point::negate).collect(toList()));
  }
}
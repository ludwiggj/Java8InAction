package lambdasinaction.chap8.points;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PointTests {

  @Test
  @DisplayName("Move point to right")
  public void shouldMoveRight() {
    Point p1 = new Point(5, 5);
    Point p2 = p1.moveRightBy(10);

    assertThat(p2.getX(), is(15));
    assertThat(p2.getY(), is(5));
  }

  @Test
  @DisplayName("Comparing points")
  public void testComparingTwoPoints() {
    Point p1 = new Point(10, 15);
    Point p2 = new Point(10, 20);
    Point p3 = new Point(8, 20);
    Point p4 = new Point(8, 20);

    assertAll("Point comparisons",
        () -> assertThat(Point.compareByXAndThenY.compare(p1, p2), is(-1)),
        () -> assertThat(Point.compareByXAndThenY.compare(p2, p1), is(1)),
        () -> assertThat(Point.compareByXAndThenY.compare(p2, p3), is(1)),
        () -> assertThat(Point.compareByXAndThenY.compare(p3, p4), is(0))
    );
  }
}
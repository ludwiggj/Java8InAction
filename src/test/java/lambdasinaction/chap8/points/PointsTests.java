package lambdasinaction.chap8.points;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PointsTests {

  @Test
  @DisplayName("Move all points right")
  public void shouldMoveAllPointsRight() {
    List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));
    List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20, 5));

    assertAll("Move all points right",
        () -> assertThat(new Points(points).moveRightBy(10).getPoints(), is(expectedPoints)),
        () -> assertThat(new Points(points).moveRightByTake2(10).getPoints(), is(expectedPoints)),
        () -> assertThat(new Points(points).moveRightBy10().getPoints(), is(expectedPoints)),
        () -> assertThat(new Points(points).moveRightByTake3(10).getPoints(), is(expectedPoints)),
        () -> assertThat(new Points(points).moveRightByTake4(10).getPoints(), is(expectedPoints))
    );
  }

  @Test
  @DisplayName("Double all points")
  public void shouldDoubleAllPoints() {
    List<Point> points = Arrays.asList(new Point(-5, 5), new Point(10, 0));
    List<Point> expectedPoints = Arrays.asList(new Point(-10, 10), new Point(20, 0));

    assertThat(new Points(points).doubleUp().getPoints(), is(expectedPoints));
  }

  @Test
  @DisplayName("Negate all points")
  public void shouldNegateAllPoints() {
    List<Point> points = Arrays.asList(new Point(-5, 5), new Point(10, 0));
    List<Point> expectedPoints = Arrays.asList(new Point(5, -5), new Point(-10, 0));

    assertThat(new Points(points).negate().getPoints(), is(expectedPoints));
  }
}
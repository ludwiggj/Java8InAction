package lambdasinaction.chap8;

import java.util.*;

import lambdasinaction.chap8.points.Point;

public class Debugging {
  private static void mapWithError1() {
    List<Point> points = Arrays.asList(new Point(12, 2), null);
    points.stream().map(p -> p.getX()).forEach(System.out::println);
  }

  private static void mapWithError2() {
    List<Point> points = Arrays.asList(new Point(12, 2), null);
    points.stream().map(Point::getX).forEach(System.out::println);
  }

  private static void mapWithError3() {
    List<Integer> numbers = Arrays.asList(1, 2, 3);
    numbers.stream().map(Debugging::divideByZero).forEach(System.out::println);
  }

  public static int divideByZero(int n) {
    return n / 0;
  }

  public static void main(String[] args) {
//    mapWithError1();
//    mapWithError2();
    mapWithError3();
  }
}
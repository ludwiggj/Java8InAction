package lambdasinaction.chap10;

import java.util.*;

public class Person {

  public Person(Optional<Car> car) {
    this.car = car;
  }

  public Person(Optional<Car> car, int age) {
    this.car = car;
    this.age = age;
  }

  private Optional<Car> car;

  private int age;

  public Optional<Car> getCar() {
    return car;
  }

  public int getAge() {
    return age;
  }
}

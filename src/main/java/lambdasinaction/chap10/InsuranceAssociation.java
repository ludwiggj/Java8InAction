package lambdasinaction.chap10;

import java.util.Optional;

public class InsuranceAssociation {

  public static String getCarInsuranceName(Optional<Person> person) {
    Optional<Car> car = person.flatMap(Person::getCar);
    Optional<Insurance> insurance = car.flatMap(Car::getInsurance);
    Optional<String> insurer = insurance.map(Insurance::getName);

    return insurer.orElse(Insurance.UNKNOWN);
  }

  public static String getCarInsuranceName(Optional<Person> person, int minAge) {
    return person
        .filter(p -> p.getAge() >= minAge)
        .flatMap(Person::getCar)
        .flatMap(Car::getInsurance)
        .map(Insurance::getName)
        .orElse(Insurance.UNKNOWN);
  }
}
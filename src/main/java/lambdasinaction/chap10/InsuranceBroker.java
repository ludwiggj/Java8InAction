package lambdasinaction.chap10;

import java.util.Optional;

import static lambdasinaction.chap10.Insurance.CHEAPO;

public class InsuranceBroker {

  public static Optional<Insurance> findCheapestInsurance(Optional<Person> person, Optional<Car> car) {
    if (person.isPresent() && car.isPresent()) {
      return Optional.of(findCheapestInsurance(person.get(), car.get()));
    } else {
      return Optional.empty();
    }
  }

  public static Optional<Insurance> findCheapestInsuranceIdiomatic(Optional<Person> person, Optional<Car> car) {
    return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
  }

  public static Insurance findCheapestInsurance(Person person, Car car) {
    return new Insurance(CHEAPO);
  }
}

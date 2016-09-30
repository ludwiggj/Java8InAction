package lambdasinaction.chap10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static lambdasinaction.chap10.Insurance.UNKNOWN;
import static lambdasinaction.chap10.InsuranceAssociation.getCarInsuranceName;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class InsuranceAssociationTests {

  @DisplayName("Insurer for un-person")
  @Test
  public void shouldReturnUnknownInsurerForUnPerson() {
    assertThat(getCarInsuranceName(Optional.empty()), is(UNKNOWN));
  }

  @DisplayName("Insurer for car-less person")
  @Test
  public void shouldReturnUnknownInsurerForCarlessPerson() {
    Person carlessPerson = new Person(Optional.empty());

    assertThat(getCarInsuranceName(Optional.of(carlessPerson)), is(UNKNOWN));
  }

  @DisplayName("Insurer for uninsured person")
  @Test
  public void shouldReturnUnknownInsurerForInsurerlessPerson() {
    Car uninsuredCar = new Car(Optional.empty());
    Person uninsuredPerson = new Person(Optional.of(uninsuredCar));

    assertThat(getCarInsuranceName(Optional.of(uninsuredPerson)), is(UNKNOWN));
  }

  @DisplayName("Insurer for insured person")
  @Test
  public void shouldReturnInsurerForInsuredPerson() {
    Car insuredCar = new Car(Optional.of(new Insurance("Admiral")));
    Person insuredPerson = new Person(Optional.of(insuredCar));

    assertThat(getCarInsuranceName(Optional.of(insuredPerson)), is("Admiral"));
  }

  @DisplayName("Insurer for insured person who is too young")
  @Test
  public void shouldReturnUnknownInsurerForInsuredPersonWhoIsTooYoung() {
    Car insuredCar = new Car(Optional.of(new Insurance("Admiral")));
    Person insuredPerson = new Person(Optional.of(insuredCar), 17);

    assertThat(getCarInsuranceName(Optional.of(insuredPerson), 18), is(UNKNOWN));
  }

  @DisplayName("Insurer for insured person who is old enough")
  @Test
  public void shouldReturnInsurerForInsuredPersonWhoIsOldEnough() {
    Car insuredCar = new Car(Optional.of(new Insurance("Admiral")));
    Person insuredPerson = new Person(Optional.of(insuredCar), 18);

    assertThat(getCarInsuranceName(Optional.of(insuredPerson), 18), is("Admiral"));
  }
}
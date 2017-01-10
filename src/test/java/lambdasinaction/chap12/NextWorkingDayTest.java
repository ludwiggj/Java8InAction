package lambdasinaction.chap12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.DayOfWeek.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NextWorkingDayTest {

  @Test
  @DisplayName("Should calculate next working day")
  public void nextWorkingDay() {
    LocalDate date = LocalDate.of(2014, 3, 20);
    assertThat(date.getDayOfWeek(), is(THURSDAY));

    LocalDate nextWorkingDay = date.with(new NextWorkingDay());
    assertThat(nextWorkingDay.getDayOfWeek(), is(FRIDAY));
    assertThat(nextWorkingDay.getDayOfMonth(), is(21));

    date = LocalDate.of(2014, 3, 21);
    assertThat(date.getDayOfWeek(), is(FRIDAY));

    nextWorkingDay = date.with(new NextWorkingDay());
    assertThat(nextWorkingDay.getDayOfWeek(), is(MONDAY));
    assertThat(nextWorkingDay.getDayOfMonth(), is(24));

    date = LocalDate.of(2014, 3, 22);
    assertThat(date.getDayOfWeek(), is(SATURDAY));

    nextWorkingDay = date.with(new NextWorkingDay());
    assertThat(nextWorkingDay.getDayOfWeek(), is(MONDAY));
    assertThat(nextWorkingDay.getDayOfMonth(), is(24));

    date = LocalDate.of(2014, 3, 23);
    assertThat(date.getDayOfWeek(), is(SUNDAY));

    nextWorkingDay = date.with(new NextWorkingDay());
    assertThat(nextWorkingDay.getDayOfWeek(), is(MONDAY));
    assertThat(nextWorkingDay.getDayOfMonth(), is(24));
  }

  @Test
  @DisplayName("Should calculate next working day via lambda")
  public void nextWorkingDayViaLambda() {
    LocalDate date = LocalDate.of(2014, 3, 24);
    assertThat(date.getDayOfWeek(), is(MONDAY));

    LocalDate nextWorkingDay = date.with(temporal -> {
      DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
      int dayToAdd = 1;
      if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
      if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
      return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    });

    assertThat(nextWorkingDay.getDayOfWeek(), is(TUESDAY));
    assertThat(nextWorkingDay.getDayOfMonth(), is(25));
  }

  @Test
  @DisplayName("Should calculate next working day via proper lambda")
  public void nextWorkingDayViaProperLambda() {
    TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
        temporal -> {
          DayOfWeek dow =
              DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
          int dayToAdd = 1;
          if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
          if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
          return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

    LocalDate date = LocalDate.of(2014, 3, 25);
    assertThat(date.getDayOfWeek(), is(TUESDAY));

    LocalDate newDay = date.with(nextWorkingDay);
    assertThat(newDay.getDayOfWeek(), is(WEDNESDAY));
    assertThat(newDay.getDayOfMonth(), is(26));
  }
}
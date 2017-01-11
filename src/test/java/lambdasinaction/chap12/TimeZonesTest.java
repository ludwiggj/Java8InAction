package lambdasinaction.chap12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.TimeZone;

import static java.time.Month.MARCH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TimeZonesTest {

  @Test
  @DisplayName("Time zone workout")
  public void timeZoneWorkout() {
    ZoneId romeZone = ZoneId.of("Europe/Rome");
    System.out.println(String.format("romeZoneId rules [%s]", romeZone.getRules()));

    ZoneId defaultZoneId = TimeZone.getDefault().toZoneId();
    System.out.println(String.format("defaultZoneId rules [%s]", defaultZoneId.getRules()));

    LocalDate date = LocalDate.of(2014, MARCH, 18);
    System.out.println(String.format("18/3/14, atStartOfDay, local time zone [%s]", date.atStartOfDay()));
    System.out.println(String.format("18/3/14, atStartOfDay,  Rome time zone [%s]", date.atStartOfDay(romeZone)));

    LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
    System.out.println(String.format("18/3/14 13:45:00, local time zone [%s]", dateTime));
    System.out.println(String.format("18/3/14 13:45:00,  Rome time zone [%s]", dateTime.atZone(romeZone)));

    System.out.println(String.format("Instant.now(), local time zone [%s]", Instant.now()));
    System.out.println(String.format("Instant.now(),  Rome time zone [%s]", Instant.now().atZone(romeZone)));

    assertThat(Instant.now().atZone(romeZone).toInstant().compareTo(Instant.now()), equalTo(0));
    assertThat(Instant.now().atZone(romeZone).toInstant(), equalTo(Instant.now()));

    LocalDateTime timeFromInstant = LocalDateTime.ofInstant(Instant.now(), romeZone);
    System.out.println(String.format("LocalDateTime from instant, Rome time zone [%s]", timeFromInstant));

    // Be aware that a ZoneOffset defined in the following way doesn't have any Daylight Saving
    // Time management, and for this reason it isn’t suggested in the majority of cases.
    ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");

    OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);
    System.out.println(String.format("18/3/14 13:45:00, New York time zone [%s]", dateTimeInNewYork));

//    Following doesn't compile
//    LocalDateTime anotherDateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
//    Instant instantFromDateTime = anotherDateTime.toInstant(romeZone);

    // But this version does...
    Instant instantInNewYork = dateTime.toInstant(newYorkOffset);
    System.out.println(String.format("18/3/14 13:45:00 as instant, New York time zone [%s]", instantInNewYork));
  }
}
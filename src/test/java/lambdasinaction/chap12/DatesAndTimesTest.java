package lambdasinaction.chap12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.Month.*;
import static java.time.temporal.TemporalAdjusters.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatesAndTimesTest {

  private static final ThreadLocal<DateFormat> formatters = new ThreadLocal<DateFormat>() {
    protected DateFormat initialValue() {
      return new SimpleDateFormat("dd-MMM-yyyy");
    }
  };

  @Test
  @DisplayName("Old date test")
  public void oldDateTest() {
    Date date = new Date(114, 2, 18);
    System.out.println(String.format("Old Date is %s", date));

    System.out.println(String.format("Formatted Old Date is %s", formatters.get().format(date)));

    Calendar calendar = Calendar.getInstance();
    calendar.set(2014, Calendar.FEBRUARY, 18);
    System.out.println(String.format("Calendar is %s", calendar));
  }

  private void testDate(LocalDate date) {
    assertThat(date.getYear(), is(2014));
    assertThat(date.get(ChronoField.YEAR), is(2014));

    assertThat(date.getMonth(), is(MARCH));
    assertThat(date.get(ChronoField.MONTH_OF_YEAR), is(3));

    assertThat(date.getDayOfMonth(), is(18));
    assertThat(date.get(ChronoField.DAY_OF_MONTH), is(18));

    assertThat(date.getDayOfWeek(), is(TUESDAY));
    assertThat(date.lengthOfMonth(), is(31));
    assertThat(date.isLeapYear(), is(false));

    System.out.println(date);
    System.out.println(String.format("Test run at %s", LocalDate.now()));
  }

  @Test
  @DisplayName("Local date workout")
  public void localDateWorkout() {
    testDate(LocalDate.of(2014, 3, 18));
  }

  @Test
  @DisplayName("Local date parsing")
  public void localDateParsing() {
    testDate(LocalDate.parse("2014-03-18"));
  }

  @Test
  @DisplayName("Should throw DateTimeParseException if cannot parse local date")
  public void shouldCatchExceptionThrownWhenCannotParseDateTime() {
    DateTimeParseException rte = assertThrows(
        DateTimeParseException.class, () -> LocalDate.parse("2014-13-18")
    );

    System.out.println(rte.getMessage());
  }

  private void testTime(LocalTime time) {
    assertThat(time.getHour(), is(13));
    assertThat(time.getMinute(), is(45));
    assertThat(time.getSecond(), is(20));

    assertThat(time.get(ChronoField.HOUR_OF_DAY), is(13));
    assertThat(time.get(ChronoField.MINUTE_OF_HOUR), is(45));
    assertThat(time.get(ChronoField.SECOND_OF_MINUTE), is(20));

    assertThat(time.format(DateTimeFormatter.ISO_LOCAL_TIME), is("13:45:20"));

    System.out.println(time);
  }

  @Test
  @DisplayName("Local time workout without seconds")
  public void localTimeWorkoutWithoutSeconds() {
    LocalTime time = LocalTime.of(13, 45); // 13:45:00

    assertThat(time.getHour(), is(13));
    assertThat(time.getMinute(), is(45));
    assertThat(time.getSecond(), is(0));

    System.out.println(time);
  }

  @Test
  @DisplayName("Local time workout with seconds")
  public void localTimeWorkoutWithSeconds() {
    testTime(LocalTime.of(13, 45, 20));
  }

  @Test
  @DisplayName("Local time parsing")
  public void localTimeParsing() {
    testTime(LocalTime.parse("13:45:20"));
  }

  @Test
  @DisplayName("Should throw DateTimeParseException if cannot parse local time")
  public void shouldCatchExceptionThrownWhenCannotParseLocalTime() {
    DateTimeParseException rte = assertThrows(
        DateTimeParseException.class, () -> LocalTime.parse("13:45:61")
    );

    System.out.println(rte.getMessage());
  }

  private void testDateTime(LocalDateTime dateTime) {
    // You can extract the LocalDate or LocalTime component from a LocalDateTime
    // using the toLocalDate and toLocalTime methods
    testDate(dateTime.toLocalDate());
    testTime(dateTime.toLocalTime());
  }

  @Test
  @DisplayName("LocalDateTime workouts")
  public void localDateTimeWorkouts() {
    testDateTime(LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20));

    LocalDate localDate = LocalDate.of(2014, 3, 18);
    LocalTime localTime = LocalTime.of(13, 45, 20);
    testDateTime(LocalDateTime.of(localDate, localTime));

    // Note that it’s possible to create a LocalDateTime by passing a time to a LocalDate
    testDateTime(localDate.atTime(13, 45, 20));
    testDateTime(localDate.atTime(localTime));

    // Or conversely a date to a LocalTime
    testDateTime(localTime.atDate(localDate));
  }

  @Test
  @DisplayName("Instant workout")
  public void instantWorkout() {
    Instant instant1 = Instant.ofEpochSecond(3);
    Instant instant2 = Instant.ofEpochSecond(3, 0);
    Instant instant3 = Instant.ofEpochSecond(2, 1_000_000_000);
    Instant instant4 = Instant.ofEpochSecond(4, -1_000_000_000);

    assertThat(instant1, equalTo(instant2));
    assertThat(instant2, equalTo(instant3));
    assertThat(instant3, equalTo(instant4));

    assertThat(instant1.toString(), is("1970-01-01T00:00:03Z"));

    // Note have to use getLong method here instead of get
    assertThat(Instant.from(Instant.ofEpochSecond(3)).getLong(ChronoField.INSTANT_SECONDS), is(3L));

    assertThat(Instant.parse("1970-01-01T00:00:03Z").getEpochSecond(), is(3L));

    assertThat(Instant.parse("1970-01-01T00:06:03Z").atOffset(ZoneOffset.of("+01:05")).getMinute(), is(11));

    assertThat(Instant.parse("1970-01-01T00:06:03Z").atZone(ZoneId.systemDefault()).getMinute(), is(6));

    assertThat(Instant.parse("1970-01-01T00:06:03Z").atZone(ZoneId.systemDefault()).getMinute(), is(6));

    assertThat(Instant.parse("1970-01-01T00:06:03Z").minus(2, ChronoUnit.SECONDS).getEpochSecond(), is(361L));

    assertThat(Instant.parse("1970-01-01T00:06:03Z").plus(2, ChronoUnit.MINUTES).getEpochSecond(), is(483L));

    assertThat(Instant.parse("1970-01-01T00:06:03Z").with(ChronoField.INSTANT_SECONDS, 7).getEpochSecond(), is(7L));
  }

  @Test
  @DisplayName("Should throw if call invalid get method on an instant")
  public void shouldCatchExceptionThrownWhenCallInvalidGetMethodOnAnInstant() {
    UnsupportedTemporalTypeException rte = assertThrows(
        UnsupportedTemporalTypeException.class, () -> Instant.now().get(ChronoField.DAY_OF_MONTH)
    );
    System.out.println(rte.getMessage());
  }

  @Test
  @DisplayName("Duration workout")
  public void durationWorkout() {
    assertThat(Duration.between(
        LocalTime.of(13, 45, 10),
        LocalTime.of(13, 45, 20)
    ).getSeconds(), is(10L));

    assertThat(Duration.between(
        LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20),
        LocalDateTime.of(2014, Month.MARCH, 18, 13, 46, 20)
    ).getSeconds(), is(60L));

    assertThat(Duration.between(
        Instant.ofEpochSecond(3, 100),
        Instant.ofEpochSecond(3, 250)
    ).getNano(), is(150));

    assertThat(Duration.between(
        LocalTime.of(13, 45, 10),
        LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20)
    ).getSeconds(), is(10L));

    assertThat(Duration.of(3, ChronoUnit.MINUTES).getSeconds(), is(180L));

    assertThat(Duration.ofMinutes(3).getSeconds(), is(180L));

    assertThat(Duration.ofMinutes(3).get(ChronoUnit.SECONDS), is(180L));

    assertThat(Duration.from(Duration.of(3, ChronoUnit.MINUTES)).getSeconds(), is(180L));

    UnsupportedTemporalTypeException rte = assertThrows(
        UnsupportedTemporalTypeException.class, () -> Duration.from(Period.ofDays(1)).getSeconds()
    );
    System.out.println(rte);

    assertThat(Duration.parse("PT3M").getSeconds(), is(180L));

    Temporal laterTime = Duration.ofMinutes(35).addTo(LocalTime.of(13, 45, 10));
    assertThat(laterTime.get(ChronoField.HOUR_OF_DAY), is(14));
    assertThat(laterTime.get(ChronoField.MINUTE_OF_HOUR), is(20));
    assertThat(laterTime.get(ChronoField.SECOND_OF_MINUTE), is(10));

    Temporal earlierTime = Duration.ofMinutes(55).subtractFrom(LocalTime.of(13, 45, 10));
    assertThat(earlierTime.get(ChronoField.HOUR_OF_DAY), is(12));
    assertThat(earlierTime.get(ChronoField.MINUTE_OF_HOUR), is(50));
    assertThat(earlierTime.get(ChronoField.SECOND_OF_MINUTE), is(10));

    Duration negativeDuration = Duration.between(
        LocalTime.of(13, 45, 20),
        LocalTime.of(13, 45, 10)
    );

    assertThat(negativeDuration.getSeconds(), is(-10L));
    assertThat(negativeDuration.isNegative(), is(true));

    assertThat(negativeDuration.negated().getSeconds(), is(10L));
    assertThat(negativeDuration.negated().isNegative(), is(false));

    assertThat(Duration.between(
        LocalTime.of(13, 45, 10),
        LocalTime.of(13, 45, 10)
    ).isZero(), is(true));

    assertThat(Duration.ofMinutes(35).minus(Duration.ofSeconds(120)).get(ChronoUnit.SECONDS), is(1980L));

    assertThat(Duration.ofSeconds(35).multipliedBy(3).get(ChronoUnit.SECONDS), is(105L));

    assertThat(Duration.ofSeconds(35).plus(3, ChronoUnit.SECONDS).get(ChronoUnit.SECONDS), is(38L));
  }

  @Test
  @DisplayName("Duration throws DateTimeException if mix wrong types")
  public void durationMixing() {
    DateTimeException rte = assertThrows(
        DateTimeException.class, () -> Duration.between(
            LocalTime.of(13, 45, 10),
            Instant.ofEpochSecond(3, 250)
        )
    );
    System.out.println(rte.getMessage());

    rte = assertThrows(
        DateTimeException.class, () -> Duration.between(
            LocalTime.of(13, 45, 10),
            LocalDate.of(2014, 3, 18)
        )
    );
    System.out.println(rte.getMessage());

    rte = assertThrows(
        DateTimeException.class, () -> Duration.between(
            LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20),
            LocalTime.of(13, 45, 10)
        )
    );
    System.out.println(rte.getMessage());
  }

  @Test
  @DisplayName("Period workout")
  public void periodWorkout() {
    assertThat(Period.between(
        LocalDate.of(2014, 3, 8),
        LocalDate.of(2014, 3, 18)
    ).getDays(), is(10));

    assertThat(Period.ofDays(10).getDays(), is(10));
    assertThat(Period.ofWeeks(3).getDays(), is(21));

    Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    assertThat(twoYearsSixMonthsOneDay.getYears(), is(2));
    assertThat(twoYearsSixMonthsOneDay.getMonths(), is(6));
    assertThat(twoYearsSixMonthsOneDay.getDays(), is(1));
  }

  @Test
  @DisplayName("Date manipulation")
  public void dateManipulation() {
    LocalDate date1 = LocalDate.of(2014, 3, 18);

    LocalDate date2 = date1.withYear(2011);
    assertThat(date2.getYear(), is(2011));
    assertThat(date2.getDayOfMonth(), is(18));
    assertThat(date2.getMonth(), is(MARCH));

    LocalDate date3 = date2.withDayOfMonth(25);
    assertThat(date3.getYear(), is(2011));
    assertThat(date3.getDayOfMonth(), is(25));
    assertThat(date3.getMonth(), is(MARCH));

    LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
    assertThat(date4.getYear(), is(2011));
    assertThat(date4.getDayOfMonth(), is(25));
    assertThat(date4.getMonth(), is(SEPTEMBER));

    LocalDate date5 = date4.plusWeeks(1);
    assertThat(date5.getYear(), is(2011));
    assertThat(date5.getDayOfMonth(), is(2));
    assertThat(date5.getMonth(), is(OCTOBER));

    LocalDate date6 = date5.minusYears(3);
    assertThat(date6.getYear(), is(2008));
    assertThat(date6.getDayOfMonth(), is(2));
    assertThat(date6.getMonth(), is(OCTOBER));

    LocalDate date7 = date6.plus(6, ChronoUnit.MONTHS);
    assertThat(date7.getYear(), is(2009));
    assertThat(date7.getDayOfMonth(), is(2));
    assertThat(date7.getMonth(), is(APRIL));

    LocalDate date8 = date1.with(ChronoField.MONTH_OF_YEAR, 9).plusYears(2).minusDays(10);
    assertThat(date8.get(ChronoField.YEAR), is(2016));
    assertThat(date8.get(ChronoField.MONTH_OF_YEAR), is(9));
    assertThat(date8.get(ChronoField.DAY_OF_MONTH), is(8));
  }

  @Test
  @DisplayName("Date manipulation via temporal adjuster")
  public void dateManipulationViaTemporalAdjuster() {
    LocalDate date1 = LocalDate.of(2014, 3, 14);
    testDate(date1.with(nextOrSame(DayOfWeek.TUESDAY)));

    assertThat(date1.with(lastDayOfMonth()).getDayOfMonth(), is(31));

    assertThat(date1.with(dayOfWeekInMonth(2, TUESDAY)).getDayOfMonth(), is(11));

    assertThat(date1.with(firstInMonth(TUESDAY)).getDayOfMonth(), is(4));

    assertThat(date1.with(lastInMonth(TUESDAY)).getDayOfMonth(), is(25));

    assertThat(date1.with(next(TUESDAY)).getDayOfMonth(), is(18));
    assertThat(date1.with(nextOrSame(TUESDAY)).getDayOfMonth(), is(18));

    assertThat(date1.with(next(FRIDAY)).getDayOfMonth(), is(21));
    assertThat(date1.with(nextOrSame(FRIDAY)).getDayOfMonth(), is(14));
  }

  @Test
  @DisplayName("Should convert date to Japanese format")
  public void shouldConvertDateToJapaneseFormat() {
    LocalDate date = LocalDate.of(2014, 3, 18);
    JapaneseDate japaneseDate = JapaneseDate.from(date);
    assertThat(japaneseDate.toString(), is("Japanese Heisei 26-03-18"));
  }

  @Test
  @DisplayName("Date formatting and parsing")
  public void shouldFormatAndParseDates() {
    LocalDate date = LocalDate.of(2014, 3, 18);

    assertThat(date.format(DateTimeFormatter.BASIC_ISO_DATE), is("20140318"));
    assertThat(date.format(DateTimeFormatter.ISO_LOCAL_DATE), is("2014-03-18"));

    assertThat(LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE), equalTo(date));
    assertThat(LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE), equalTo(date));
  }

  @Test
  @DisplayName("Date formatting and parsing with custom pattern")
  public void shouldFormatAndParseDatesWithCustomPattern() {
    LocalDate date = LocalDate.of(2014, 3, 18);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    assertThat(date.format(formatter), is("18/03/2014"));
    assertThat(LocalDate.parse("18/03/2014", formatter), equalTo(date));
  }

  @Test
  @DisplayName("Date formatting and parsing an Italian date")
  public void shouldFormatAndParseDatesInItalian() {
    LocalDate date = LocalDate.of(2014, 3, 18);

    DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);

    assertThat(date.format(italianFormatter), is("18. marzo 2014"));
    assertThat(LocalDate.parse("18. marzo 2014", italianFormatter), equalTo(date));
  }

  @Test
  @DisplayName("Date formatting and parsing an Italian date via custom formatter")
  public void shouldFormatAndParseDatesInItalianViaCustomFormatter() {
    LocalDate date = LocalDate.of(2014, 3, 18);

    DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
        .appendText(ChronoField.DAY_OF_MONTH)
        .appendLiteral(". ")
        .appendText(ChronoField.MONTH_OF_YEAR)
        .appendLiteral(" ")
        .appendText(ChronoField.YEAR)
        .parseCaseInsensitive()
        .toFormatter(Locale.ITALIAN);

    assertThat(date.format(italianFormatter), is("18. marzo 2014"));
    assertThat(LocalDate.parse("18. marzo 2014", italianFormatter), equalTo(date));
  }
}
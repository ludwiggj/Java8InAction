package lambdasinaction.chap3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.expectThrows;

import static lambdasinaction.chap3.ExecuteAround.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Execute around tests to read file content")
public class ExecuteAroundTests {

  @Test
  @DisplayName("Read a line via rigid code")
  public void shouldReadALineFromAFile() throws IOException {
    assertThat(processFileLimited(), is("Java"));
  }

  @Test
  @DisplayName("Read a line via lambda")
  public void shouldReadALineFromAFileViaLambda() throws IOException {
    assertThat(processFile((BufferedReader b) -> b.readLine()), is("Java"));
  }

  @Test
  @DisplayName("Read two lines via lambda")
  public void shouldReadTwoLinesFromAFileViaLambda() throws IOException {
    assertThat(processFile((BufferedReader b) -> b.readLine() + " " + b.readLine()), is("Java 8"));
  }

  @Test
  @DisplayName("Read two lines via lambda, take 2")
  public void shouldReadTwoLinesFromAFileViaLambdaTake2() {
    assertThat(processFileWithoutCheckedException((BufferedReader b) -> b.readLine() + " " + b.readLine()), is("Java 8"));
  }

  @Test
  @DisplayName("Should catch exception thrown when process file")
  public void shouldCatchExceptionThrownWhenProcessFile() {
    RuntimeException rte = expectThrows(RuntimeException.class, () -> {
      processFileWithoutCheckedException((BufferedReader b) -> {
        throw new IOException("Oh");
      });
    });

    assertThat(rte.getMessage(), is("Bugger"));
  }

  @Test
  @DisplayName("Should catch exception when process non-existent file")
  public void shouldCatchExceptionWhenProcessNonExistentFile() {
    RuntimeException rte = expectThrows(RuntimeException.class, () -> {
      processFileWithoutCheckedException2("fileThatDoesNotExist");
    });

    assertThat(rte.getMessage(), is("Oh dear null"));
  }
}
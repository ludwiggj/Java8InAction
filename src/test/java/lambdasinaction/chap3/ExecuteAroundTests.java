package lambdasinaction.chap3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static lambdasinaction.chap3.ExecuteAround.processFile;
import static lambdasinaction.chap3.ExecuteAround.processFileLimited;
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
}
package lambdasinaction.chap3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Function;

public class ExecuteAround {

  public static final String DATA_FILE_NAME = "lambdasinaction/chap3/data.txt";

  private static BufferedReader getResource(String resource) {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    InputStream is = cl.getResourceAsStream(resource);
    return new BufferedReader(new InputStreamReader(is));
  }

  public static String processFileLimited() throws IOException {
    try (BufferedReader br = getResource(DATA_FILE_NAME)) {
      return br.readLine();
    }
  }

  @FunctionalInterface
  public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = getResource(DATA_FILE_NAME)) {
      return p.process(br);
    }
  }

  public static String processFileWithoutCheckedException(BufferedReaderProcessor p) {
    try (BufferedReader br = getResource(DATA_FILE_NAME)) {
      return p.process(br);
    } catch (IOException e) {
      throw new RuntimeException("Bugger");
    }
  }

  // Catch exception explicitly if don't want it in lambda's signature
  private static Function<String, String> f = (String resourceName) -> {
    try {
      BufferedReader br = getResource(resourceName);
      return br.readLine();
    } catch (IOException e) {
      throw new RuntimeException("Oh dear");
    } catch (NullPointerException npe) {
      throw new RuntimeException(("Oh dear null"));
    }
  };

  public static String processFileWithoutCheckedException2(String resourceName) {
    return f.apply(resourceName);
  }
}
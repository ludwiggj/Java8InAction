package lambdasinaction.chap3;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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

  // Catch exception explicitly if don't want it in lambda's signature
  private static Function<String, String> f = (String resourceName) -> {
    try {
      BufferedReader br = getResource(resourceName);
      return br.readLine();
    } catch (IOException e) {
      throw new RuntimeException("Oh dear");
    }
  };

  public static void main(String... args) throws IOException {
    System.out.println(f.apply("lambdasinaction/chap3/data.txt"));

    // Special void-compatibility rule
    // If a lambda has a statement expression as its body, it’s compatible with a function
    // descriptor that returns void (provided the parameter list is compatible too). For
    // example, both of the following lines are legal even though the method add of a List
    // returns a boolean and not void as expected in the Consumer context (T -> void):

    List<Integer> list = Arrays.asList(1, 2, 3);

    // Predicate has a boolean return
    Predicate<Integer> p = i -> list.add(i);

    // Consumer has a void return
    Consumer<Integer> b = i -> list.add(i);

    // Quiz 3.5 - Why doesn't the following compile?
    // Object o = () -> {System.out.println("Tricky example");};
    Runnable r = () -> {System.out.println("Tricky example");};
  }
}
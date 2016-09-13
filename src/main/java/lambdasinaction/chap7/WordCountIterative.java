package lambdasinaction.chap7;

public class WordCountIterative {

  public static int countWordsIteratively(String s) {
    int counter = 0;
    boolean lastSpace = true;
    for (char c : s.toCharArray()) {
      if (Character.isWhitespace(c)) {
        lastSpace = true;
      } else {
        if (lastSpace) counter++;
        lastSpace = Character.isWhitespace(c);
      }
    }
    return counter;
  }
}
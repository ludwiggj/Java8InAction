package lambdasinaction.chap9.ambiguous.quiz_9_3;

public class C implements B, A {
  public static void main(String... args) {
    // Error: class lambdasinaction.chap9.ambiguous.quiz_9_3.C inherits unrelated defaults for getNumber() from types
    // lambdasinaction.chap9.ambiguous.quiz_9_3.B and lambdasinaction.chap9.ambiguous.quiz_9_3.A

    System.out.println(new C().getNumber());
  }

  // Fix by over-riding getNumber() in this class
  @Override
  public Integer getNumber() {
    return null;
  }
}
package lambdasinaction.chap9.ambiguous.case_9_4_3;

public class C implements B, A {

  // C must implement hello, as A and B are not related
  public void hello() {
    // Call A's version of default method
    A.super.hello();

    // Call B's version
    B.super.hello();
  }

  public static void main(String... args) {
    new C().hello();
  }
}
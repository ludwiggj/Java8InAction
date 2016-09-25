package lambdasinaction.chap9.ambiguous.case_9_4_4_diamond;

public class Diamond {

  interface A {
    default void hello() {
      System.out.println("Hello from A");
    }
  }

  interface B extends A {
  }

  interface C extends A {
  }

  static class D implements B, C {
  }

  public static void main(String... args) {
    // Hello from A
    new D().hello();
  }
}

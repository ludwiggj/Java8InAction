package lambdasinaction.chap2;

public class Threads {
  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(new Runnable() {
      public void run() {
        System.out.println("Hello world");
      }
    });

    Thread t2 = new Thread(() -> System.out.println("Hi there"));

    t1.start();
    t2.start();

    Thread.sleep(250);
  }
}
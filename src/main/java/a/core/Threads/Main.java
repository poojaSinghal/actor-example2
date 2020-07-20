package a.core.Threads;

import java.io.File;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    File file = new File("/Users/pooja/workdir/file1.txt");
    Runnable runnable = new Count(file);
    long startTime = System.currentTimeMillis();

    for (int i = 0; i < 1; i++) {
      Thread thread =new Thread(runnable);
      thread.start();
      thread.join();
    }

    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.println("Threads processing time " + elapsedTime + "milliseconds");
  }
}

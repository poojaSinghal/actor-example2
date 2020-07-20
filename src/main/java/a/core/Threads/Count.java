package a.core.Threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Count implements Runnable{
  File file;

  public Count(File file){
    this.file = file;
  }

  @Override
  public void run() {
    int i = 0;
    int wordCount = 0;

    try {
      FileReader reader = new FileReader(file);
      BufferedReader buffer = new BufferedReader(reader);
      String line;
      while ((line = buffer.readLine()) != null) {
        String[] wordList = line.split(",");
        wordCount += wordList.length;
      }
      System.out.println("Number of words in " + Thread.currentThread().getName() + "-" + wordCount);

      reader.close();
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}

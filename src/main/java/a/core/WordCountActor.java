package a.core;

import akka.actor.AbstractActor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordCountActor extends AbstractActor {


  public Receive createReceive() {
    return receiveBuilder().match(File.class, this::handleFile).build();
  }

  private void handleFile(File r) throws IOException {
    int i = 0;
    int wordCount = 0;

    try {
      FileReader reader = new FileReader(r);
      BufferedReader buffer = new BufferedReader(reader);
      String line;
      while ((line = buffer.readLine()) != null) {
        String[] wordList = line.split(",");
        wordCount += wordList.length;
      }
      System.out.println("Number of words in " + Thread.currentThread().getName() + "-" + wordCount);

      reader.close();
      getSender().tell(wordCount, getSelf());
    } catch (Exception ex) {
      getSender().tell(new akka.actor.Status.Failure(ex), getSelf());
      throw ex;
    }
  }
}

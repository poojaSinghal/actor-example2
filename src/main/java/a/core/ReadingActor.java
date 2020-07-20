package a.core;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ReadingActor extends AbstractActor {
  private String text;

  public static Props props(String text) {
    return Props.create(ReadingActor.class, text);
  }

  public Receive createReceive() {
    return receiveBuilder().matchEquals("printit", p -> {
      System.out.println("The address of this actor is: " + getSelf());
    }).build();
  }
}
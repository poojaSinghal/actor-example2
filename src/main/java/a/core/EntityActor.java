//package a.core;
//
//import static Threads.EntityModel.*;
//
//import akka.actor.AbstractActor;
//import akka.actor.Props;
//
//import java.io.IOException;
//
//public class EntityActor extends AbstractActor {
//  Entity entity;
//
//  public static Props props(Entity entity) {
//    return Props.create(EntityActor.class, entity);
//  }
//
//  @Override
//  public Receive createReceive() {
//    return receiveBuilder().match(ReadRequest.class, this::handleRead)
//        .match(WriteRequest.class,this::handleWrite).build();
//
//  }
//
//  private void handleWrite(WriteRequest p) {
//    try {
//      entity = p.getUpdate();
//      getSender().tell(entity, getSelf());
//    } catch (Exception ex) {
//      getSender().tell(new akka.actor.Status.Failure(ex), getSelf());
//      throw ex;
//    }
//
//  }
//
//  private void handleRead(ReadRequest p) throws IOException {
//    try {
//      getSender().tell(entity, getSelf());
//    } catch (Exception ex) {
//      getSender().tell(new akka.actor.Status.Failure(ex), getSelf());
//      throw ex;
//    }
//  }
//}

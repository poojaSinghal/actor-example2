//package a.core;
//
//import Threads.EntityModel.Entity;
//import akka.actor.AbstractActor;
//import akka.actor.Props;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class EntityServiceActor extends AbstractActor {
//
//  Map<String, EntityActor> entityActorMap = new HashMap<>();
//
//  public static Props props(Entity entity) {
//    return Props.create(EntityActor.class, entity);
//  }
//  @Override
//  public Receive createReceive() {
//    return null;
//  }
//}

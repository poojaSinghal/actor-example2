package a.db;

import static a.db.Entities.entities;
import static a.db.EntityThreadDatabase.readEntityFromDB;
import static a.db.EntityThreadDatabase.writeEntityToDb;

import a.core.Threads.EntityModel.Entity;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class EntityActorDatabase extends AbstractActor {

  public static final  ActorRef actorRef;
  static {
    ActorSystem system = ActorSystem.create("test-system");
    actorRef = system.actorOf(Props.create(EntityActorDatabase.class), "my-actor");

//    Cluster.get(system).subscribe(clusterListener,
//        ClusterDomainEvent.class);
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().match(String.class, this::handleRead)
        .match(Entity.class,this::handleWrite).build();
  }

  private void handleRead(String entityId) throws InterruptedException {
    if(!entities.containsKey(entityId)){
      Entity entity = readEntityFromDB(entityId);
      entities.put(entityId,entity);
    }
    getSender().tell(entities.get(entityId), getSelf());
  }

  private void handleWrite (Entity entity) throws InterruptedException {
    String entityId = entity.getId();
    writeEntityToDb(entity);
    entities.put(entityId,entity);
    getSender().tell(entity, getSelf());
  }
}

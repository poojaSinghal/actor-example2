//package a.core;
//
//import static akka.pattern.Patterns.*;
//
//import a.core.Threads.EntityModel.Entity;
//import a.core.Threads.EntityModel.ReadRequest;
//import akka.actor.ActorRef;
//import akka.actor.ActorSystem;
//import akka.actor.Props;
//import scala.compat.java8.FutureConverters;
//
//import java.io.File;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
//public class Main {
//
//  static ActorRef entityService;
//  public static void main(String[] args) throws ExecutionException, InterruptedException {
//    ActorSystem system = ActorSystem.create("test-system");
//
//    ActorRef wordCountActor
//        = system.actorOf(Props.create(WordCountActor.class), "my-actor");
//
//    long startTime = System.currentTimeMillis();
//
//    File file = new File("/Users/pooja/workdir/file1.txt");
//    CompletableFuture<Object> future = FutureConverters.toJava(ask(wordCountActor, file, 1000)).toCompletableFuture();
//
//    future.get();
//
//    entityService = system.actorOf(Props.create(EntityServiceActor.class), "entityService");
//
//    long elapsedTime = System.currentTimeMillis() - startTime;
//    System.out.println("Threads processing time " + elapsedTime + "milliseconds");
//
//    system.terminate();
//
//  }
//
//  public Entity readEntity(String entityId) throws ExecutionException, InterruptedException {
//    CompletableFuture<Entity> future = FutureConverters.<Entity>toJava(ask(entityService, new ReadRequest(entityId), 1000)).toCompletableFuture();
//
//
//  }
//
//  public void upsertEntity(String entityId){
//
//  }
//
//
//}

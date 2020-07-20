package a.resources;

import static akka.pattern.Patterns.ask;

import a.core.Threads.EntityModel.Entity;
import a.db.EntityActorDatabase;
import scala.compat.java8.FutureConverters;

import java.util.concurrent.CompletableFuture;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("/actor")
public class EntityActorResource {
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void readEntity(@Suspended AsyncResponse asyncResponse, @PathParam("id") String id) {
    FutureConverters.toJava(ask(EntityActorDatabase.actorRef, id, 10000)).thenAccept(asyncResponse::resume);
  }

  @Path("/write")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public void userInfoAsync(@Suspended AsyncResponse asyncResponse, Entity entity) {
    FutureConverters.toJava(ask(EntityActorDatabase.actorRef, entity, 10000)).thenAccept(asyncResponse::resume);
  }
}

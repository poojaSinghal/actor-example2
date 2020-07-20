package a.resources;

import a.core.Threads.EntityModel.Entity;
import a.db.EntityThreadDatabase;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("/thread")
public class EntityThreadResource {
//  @GET
//  @Path("/{id}")
//  @Produces(MediaType.APPLICATION_JSON)
//  public void userInfoAsync(@Suspended AsyncResponse asyncResponse,
//      @PathParam("id") String id) {
//
//  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Entity readEntity(@PathParam("id") String id) throws InterruptedException {
    return EntityThreadDatabase.readEntity(id);
  }

  @Path("/write")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Entity writeEntity(Entity entity) throws InterruptedException {
    return EntityThreadDatabase.writeEntity(entity);
  }
}

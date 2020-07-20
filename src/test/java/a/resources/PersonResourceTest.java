package a.resources;

import a.core.Operation;
import a.core.Person;
import a.core.ReadOperation;
import a.core.Threads.EntityModel.Entity;
import a.core.WriteOperation;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
class PersonResourceTest {

  private static final ResourceExtension EXT =
      ResourceExtension.builder().addResource(new EntityThreadResource()).addResource(new EntityActorResource()).build();


  interface MyClient {
    Response write(Entity entity);

    Entity read(String entityId);
  }


  static class ThreadMyClient implements MyClient {

    public Response write(Entity entity) {
      return EXT.target("/thread").request(MediaType.APPLICATION_JSON_TYPE).put(javax.ws.rs.client.Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
    }

    public Entity read(String entityId) {
      return EXT.target("/thread/" + entityId).request().get(Entity.class);
    }
  }


  static class ActorMyClient implements MyClient {

    public Response write(Entity entity) {
      return EXT.target("/actor").request(MediaType.APPLICATION_JSON_TYPE).put(javax.ws.rs.client.Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
    }

    public Entity read(String entityId) {
      return EXT.target("/actor/" + entityId).request().get(Entity.class);
    }
  }


  static class PersonRunnable implements Runnable {
    private final Person person;
    private WebTarget client;

    public PersonRunnable(WebTarget client, int i) {
      this.client = client;
      person = Person.generatePerson(String.valueOf(i%100), new Entity(String.valueOf(i%100), "entity name" + i % 100, new HashMap<>()));
    }

    @Override
    public void run() {
      for (Operation operation : person.operationList) {
        if (operation instanceof ReadOperation) {
          client.path(((ReadOperation) operation).getEntityId()).request(MediaType.APPLICATION_JSON_TYPE).get(Entity.class);

        } else if (operation instanceof WriteOperation) {
          client.path("write").request(MediaType.APPLICATION_JSON_TYPE)
              .put(javax.ws.rs.client.Entity.json(((WriteOperation) operation).getEntity()), Entity.class);
        }
//        try {
//          Thread.sleep(50);
//        } catch (InterruptedException e) {
//          throw new RuntimeException();
//        }
      }
    }
  }


  static class Result {
    long time;
    long success;
    long failed;

    public Result(long time, long success, long failed) {
      this.time = time;
      this.success = success;
      this.failed = failed;
    }

    @Override
    public String toString() {
      return "Result{" + "time=" + time + ", success=" + success + ", failed=" + failed + '}';
    }
  }

  private Result benchmark(WebTarget client, int numThreads) {
    long start = System.currentTimeMillis();
    List<PersonRunnable> runnables = new ArrayList<>();
    for (int i = 0; i < numThreads; i++) {
      runnables.add(new PersonRunnable(client, i));
    }
    ExecutorService pool = Executors.newFixedThreadPool(50);
    List<Future<?>> futures = runnables.stream().map(pool::submit).collect(Collectors.toList());
    List<Boolean> results = futures.stream().map(x -> {
      try {
        x.get();
        return true;
      } catch (InterruptedException e) {
        return false;
      } catch (ExecutionException e) {
        e.printStackTrace();
        return false;
      }
    }).collect(Collectors.toList());
    pool.shutdown();
    return new Result(System.currentTimeMillis() - start, results.stream().filter(x -> x).count(), results.stream().filter(x -> !x).count());
  }

  Client client = ClientBuilder.newClient();

  int numPersons = 10000;

  @Test
  void testActorPerformance() {
    System.out.println("Actors: " + benchmark(client.target("http://localhost:8080/actor/"), numPersons));
  }

  @Test
  void testThreadPerformance() {
    System.out.println("Threads: " + benchmark(client.target("http://localhost:8080/thread/"), numPersons));
  }
}
package a.db;

import static a.db.Entities.entities;

import a.core.Threads.EntityModel.Entity;

public class EntityThreadDatabase {

  public static synchronized Entity readEntity(String entityId) throws InterruptedException {
    if(!entities.containsKey(entityId)){
      Entity entity = readEntityFromDB(entityId);
      entities.put(entityId,entity);
      return entity;
    }
    return entities.get(entityId);
  }

  public static synchronized Entity writeEntity(Entity entity) throws InterruptedException {
    String entityId = entity.getId();
      writeEntityToDb(entity);
      entities.put(entityId,entity);
      return entity;
  }

  public static void writeEntityToDb(Entity entity) throws InterruptedException {
    Thread.sleep(10);
  }

  public static Entity readEntityFromDB(String entityId) throws InterruptedException {
    Thread.sleep(5);
    return null;
  }
}

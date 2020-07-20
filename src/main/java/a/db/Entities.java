package a.db;

import a.core.Threads.EntityModel.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Entities {
  public static final Map<String, Entity> entities = new ConcurrentHashMap<>();
}

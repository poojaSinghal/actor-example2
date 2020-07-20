package a.core.Threads;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

public class EntityModel {

  @Data
  @JsonSerialize
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Entity {
    String id;
    String name;
    Map<String, String> properties;
  }


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ReadRequest {
    String entityId;
  }


  @Getter
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class WriteRequest {
    String entityId;
    Entity update;
  }
}
package a.core;

import a.core.Threads.EntityModel.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WriteOperation implements Operation {
  Entity entity;
}

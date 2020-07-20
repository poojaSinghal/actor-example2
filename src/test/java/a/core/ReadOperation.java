package a.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadOperation implements Operation {
  String entityId;
}

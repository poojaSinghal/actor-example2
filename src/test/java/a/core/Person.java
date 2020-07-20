package a.core;

import static java.util.Arrays.asList;

import a.core.Threads.EntityModel.Entity;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Person {

  public List<Operation> operationList;

  public static Person generatePerson(String entityId, Entity entity) {
    ReadOperation readOperation = new ReadOperation(entityId);
    WriteOperation writeOperation = new WriteOperation(entity);
    List<Operation> operations =
        asList(writeOperation,
            readOperation, writeOperation, readOperation, writeOperation, readOperation, readOperation, readOperation, writeOperation,
//            readOperation, writeOperation, readOperation, writeOperation, readOperation, readOperation, readOperation, writeOperation,
//            readOperation, writeOperation, readOperation, writeOperation, readOperation, readOperation, readOperation, writeOperation,
//            readOperation, writeOperation, readOperation, writeOperation, readOperation, readOperation, readOperation, writeOperation,
//            readOperation, writeOperation, readOperation, writeOperation, readOperation, readOperation, readOperation, writeOperation,
//            readOperation, writeOperation, readOperation, writeOperation, readOperation, readOperation, readOperation, writeOperation,
//            readOperation, writeOperation, readOperation, writeOperation, readOperation, readOperation, readOperation, writeOperation,
//            readOperation, writeOperation, readOperation, writeOperation, readOperation, readOperation, readOperation, writeOperation,
            readOperation);
    return new Person(operations);
  }
}

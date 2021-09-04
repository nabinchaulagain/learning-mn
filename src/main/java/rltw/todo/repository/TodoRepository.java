package rltw.todo.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import rltw.todo.model.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Long> {
}

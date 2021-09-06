package rltw.todo.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import rltw.todo.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public boolean existsByUsername(String username);

    public Optional<User> findByUsername(String username);
}

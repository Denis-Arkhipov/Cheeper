package com.denar.cheeper.datalayer.repositories;

import com.denar.cheeper.datalayer.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    @Query(value = "SELECT * FROM chat_users u WHERE u.username <> :username",
            nativeQuery = true)
    List<User> findAllExceptMe(@Param("username") String username);
    User findByUsername(String username);
}

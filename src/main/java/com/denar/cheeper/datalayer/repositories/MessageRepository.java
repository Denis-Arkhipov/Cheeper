package com.denar.cheeper.datalayer.repositories;

import com.denar.cheeper.datalayer.entities.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query(value = "SELECT * FROM messages m WHERE m.message_date > :date",
            nativeQuery = true)
    List<Message> findAfterDate(@Param("date") String messageDate);
    List<Message> findAll();
}

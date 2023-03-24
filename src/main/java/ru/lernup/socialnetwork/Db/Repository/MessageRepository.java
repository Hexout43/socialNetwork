package ru.lernup.socialnetwork.Db.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.socialnetwork.Db.Entity.Message;
import ru.lernup.socialnetwork.Db.Entity.Person;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> getMessagesByIdRecipient(Person person);
    List<Message> getMessagesByIdAuthor(Person person);
}

package ru.lernup.socialnetwork.Db.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> getPostsByPerson(Person person);
    List<Post> getPostsByHeaderContainsIgnoreCase(String header);
    List<Post> getPostsByBodyContainsIgnoreCase(String body);
}

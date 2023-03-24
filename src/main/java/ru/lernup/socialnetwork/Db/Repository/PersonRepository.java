package ru.lernup.socialnetwork.Db.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.socialnetwork.Db.Entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
}

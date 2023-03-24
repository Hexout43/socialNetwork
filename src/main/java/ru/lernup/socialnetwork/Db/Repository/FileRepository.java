package ru.lernup.socialnetwork.Db.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.socialnetwork.Db.Entity.FilePerson;
import ru.lernup.socialnetwork.Db.Entity.Person;

import java.util.List;

public interface FileRepository extends JpaRepository<FilePerson,Long> {
    FilePerson getFilePersonByNameContains(String name);
    FilePerson getFilePersonByPersonAndNameContains(Person person,String name);
}

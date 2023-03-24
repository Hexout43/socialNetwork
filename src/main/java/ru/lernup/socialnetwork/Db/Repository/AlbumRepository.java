package ru.lernup.socialnetwork.Db.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.socialnetwork.Db.Entity.Album;
import ru.lernup.socialnetwork.Db.Entity.Person;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long> {
    List<Album> getAlbumsByPerson(Person person);
}

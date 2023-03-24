package ru.lernup.socialnetwork.Db.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lernup.socialnetwork.Db.Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByLogin(String login);
}

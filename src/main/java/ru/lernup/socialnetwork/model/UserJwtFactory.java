package ru.lernup.socialnetwork.model;



import ru.lernup.socialnetwork.Db.Entity.User;
import ru.lernup.socialnetwork.model.UserJwt;

import java.util.stream.Collectors;

public class UserJwtFactory {
    public static UserJwt createUser(User user){
        return UserJwt.builder()
                .login(user.getLogin())
                .pass(user.getPassword())
                .roles(user.getRoles().stream().map(role -> {
                    return new UserRole(role.getName());
                }).collect(Collectors.toList()))
                .build();
    }
}

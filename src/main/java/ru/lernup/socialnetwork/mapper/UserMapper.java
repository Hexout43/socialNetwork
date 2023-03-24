package ru.lernup.socialnetwork.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.lernup.socialnetwork.Db.Entity.Role;
import ru.lernup.socialnetwork.Db.Entity.User;
import ru.lernup.socialnetwork.view.UserRegister;

import java.util.Set;
import java.util.stream.Collectors;
@Component
public class UserMapper {
    private  final BCryptPasswordEncoder encoder;

    public UserMapper(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public User mappedFromView(UserRegister userRegister){
            User user = new User();
            user.setLogin(userRegister.getLogin());
            user.setPassword(encoder.encode(userRegister.getPassword()));
            if(userRegister.getRoles()!= null){
           user.setRoles(userRegister.getRoles().stream().map(roles->{
               Role role = new Role();
               role.setName(roles);
               role.setUsers(Set.of(user));
              return role;
           }).collect(Collectors.toSet()));
            }
            else {
                Role roles = new Role();
                roles.setName("User");
                roles.setUsers(Set.of(user));
                user.setRoles(Set.of(roles));
            }
            return  user;
    }
    public UserRegister mappedToView(User user){
        UserRegister userRegister = new UserRegister();
        userRegister.setLogin(user.getLogin());
        userRegister.setPassword(encoder.encode(user.getPassword()));
        userRegister.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return userRegister;
    }

}

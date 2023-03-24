package ru.lernup.socialnetwork.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lernup.socialnetwork.model.UserJwt;
import ru.lernup.socialnetwork.model.UserJwtFactory;
import ru.lernup.socialnetwork.service.UserService;
@Slf4j
@Service
public class AuthService implements UserDetailsService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserJwt userJwt = UserJwtFactory.createUser(userService.getUserByLogin(username));
        if(userJwt == null){
            throw  new UsernameNotFoundException("Пользователь не найден");
        }
        log.info("find user : {}",userJwt.getUsername());
        return userJwt;

    }
}

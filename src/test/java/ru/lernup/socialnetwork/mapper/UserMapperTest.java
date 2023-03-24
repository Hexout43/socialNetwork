package ru.lernup.socialnetwork.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.lernup.socialnetwork.Db.Entity.Role;
import ru.lernup.socialnetwork.Db.Entity.User;
import ru.lernup.socialnetwork.view.UserRegister;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserMapperTest {
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    UserMapper userMapper;
    @Test
    void mappedFromView_returnValidEntity(){
        //given
        UserRegister userRegister = new UserRegister();
        userRegister.setLogin("mike");
        userRegister.setPassword("123");
        userRegister.setRoles(List.of("User"));
        UserRegister userRegisterThenRoleNull = new UserRegister();
        userRegisterThenRoleNull.setLogin("mike");
        userRegisterThenRoleNull.setPassword("123");
        //when
        var user = userMapper.mappedFromView(userRegister);
        var userNew = userMapper.mappedFromView(userRegisterThenRoleNull);
        //then
        assertEquals(user.getLogin(),userRegister.getLogin());
        assertEquals(user.getPassword(),bCryptPasswordEncoder.encode(userRegister.getPassword()));
        assertArrayEquals(user.getRoles().stream().map(Role::getName).toArray(),userRegister.getRoles().toArray());
        assertEquals(userNew.getLogin(),userRegisterThenRoleNull.getLogin());
        assertEquals(userNew.getPassword(),bCryptPasswordEncoder.encode(userRegisterThenRoleNull.getPassword()));
        assertArrayEquals(userNew.getRoles().stream().map(Role::getName).toArray(), Arrays.stream(new String[]{"User"}).toArray());
    }
    @Test
    void mappedToView_returnValidView(){
        //given
        User user = new User();
        user.setLogin("mike");
        user.setPassword(bCryptPasswordEncoder.encode("123"));
        Role role = new Role();
        role.setName("User");
        role.setUsers(Set.of(user));
        user.setRoles(Set.of(role));

        //when
        var userView = userMapper.mappedToView(user);
        //then
        assertEquals(user.getLogin(),userView.getLogin());
        assertEquals(user.getPassword(),bCryptPasswordEncoder.encode(userView.getPassword()));
        assertArrayEquals(user.getRoles().stream().map(Role::getName).toArray(),userView.getRoles().toArray());


    }

}
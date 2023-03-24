package ru.lernup.socialnetwork.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lernup.socialnetwork.Db.Entity.User;
import ru.lernup.socialnetwork.Db.Repository.UserRepository;
import ru.lernup.socialnetwork.mapper.UserMapper;
import ru.lernup.socialnetwork.view.UserRegister;

@Component
public class UserService {
    private  final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
@Transactional
    public void  registerUser(UserRegister user){
       userRepository.save(userMapper.mappedFromView(user));
    }
    @Transactional
    public boolean deleteUser(Long id){
        User user = userRepository.getReferenceById(id);
        userRepository.delete(user);
        return true;
    }
    public User getUserByLogin(String login){
        return userRepository.getUserByLogin(login);
    }
 }

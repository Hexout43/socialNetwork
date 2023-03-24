package ru.lernup.socialnetwork.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.service.UserService;
import ru.lernup.socialnetwork.view.UserRegister;

@RestController
@RequestMapping("/user")
public class UserController {
  private  final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
    public void register(@RequestBody UserRegister userRegister){
          userService.registerUser(userRegister);
  }
  @DeleteMapping("/delete/{id}")
//  @PreAuthorize("#user.login==authentication.name")
  public  boolean  delite(@PathVariable("id") Long id){
       return   userService.deleteUser(id);
  }


}

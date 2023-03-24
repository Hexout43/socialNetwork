package ru.lernup.socialnetwork.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.service.FriendsService;
import ru.lernup.socialnetwork.service.UserService;
import ru.lernup.socialnetwork.view.Friends;

@RestController
@RequestMapping("/person/{login}/friend")
public class FriendController {
    private final FriendsService friendsService;
    private final UserService userService;

    public FriendController(FriendsService friendsService,
                            UserService userService) {
        this.friendsService = friendsService;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("#login==authentication.name")
    public void addFriend(
                          @RequestParam("id_friend") Long idFriend,
                          @PathVariable("login")String login){
       Long id = userService.getUserByLogin(login).getPerson().getId();
        friendsService.addFriend(id,idFriend);
    }
    @DeleteMapping
    @PreAuthorize("#login==authentication.name")
    public void  deleteFriend(
                              @RequestParam("id_friend") Long idFriend,
                              @PathVariable("login")String login){
        Long id = userService.getUserByLogin(login).getPerson().getId();
        friendsService.deleteFriend(id,idFriend);
    }
    @GetMapping
    public Friends getFriends( @PathVariable("login")String login){
        Long id = userService.getUserByLogin(login).getPerson().getId();
        return friendsService.getAllFriends(id);
    }

}

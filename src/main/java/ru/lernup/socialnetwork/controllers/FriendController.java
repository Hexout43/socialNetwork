package ru.lernup.socialnetwork.controllers;

import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.service.FriendsService;
import ru.lernup.socialnetwork.view.Friends;

@RestController
@RequestMapping("/person/{id}/friend")
public class FriendController {
    private final FriendsService friendsService;

    public FriendController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping
    public void addFriend(@PathVariable("id") Long id,
                          @RequestParam("id_friend") Long idFriend){
        friendsService.addFriend(id,idFriend);
    }
    @DeleteMapping
    public void  deleteFriend(@PathVariable("id")Long id,
                              @RequestParam("id_friend") Long idFriend){
        friendsService.deleteFriend(id,idFriend);
    }
    @GetMapping
    public Friends getFriends(@PathVariable("id") Long id){
        return friendsService.getAllFriends(id);
    }

}

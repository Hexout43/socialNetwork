package ru.lernup.socialnetwork.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.service.MessageService;
import ru.lernup.socialnetwork.service.UserService;
import ru.lernup.socialnetwork.view.MessageView;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/message/{login}/in/")
    @PreAuthorize("#login==authentication.name")
    public List<MessageView> getRecipientMessage(@PathVariable("login") String login){
        Long id =userService.getUserByLogin(login).getPerson().getId();
       return messageService.getAllMessageByRecipient(id);
    }
    @GetMapping("/message/{login}/out/")
    @PreAuthorize("#login==authentication.name")
    public List<MessageView> getAuthorMessage(@PathVariable("login")String login){
        Long id =userService.getUserByLogin(login).getPerson().getId();
        return  messageService.gerAllMessageByAuthor(id);
    }
    @PostMapping("/message/{login}")
    @PreAuthorize("#login==authentication.name")
    public void sendMessage(@RequestBody MessageView messageView,
                            @PathVariable("login")String login){
        if(messageView.getIdAuthor().equals(userService.getUserByLogin(login).getPerson().getId())){
        messageService.sendMessage(messageView);}
    }
}

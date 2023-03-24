package ru.lernup.socialnetwork.controllers;

import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.service.MessageService;
import ru.lernup.socialnetwork.view.MessageView;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message/{id}/in")
    public List<MessageView> getRecipientMessage(@PathVariable("id") Long id){
       return messageService.getAllMessageByRecipient(id);
    }
    @GetMapping("/message/{id}/out")
    public List<MessageView> getAuthorMessage(@PathVariable("id")Long id){
        return  messageService.gerAllMessageByAuthor(id);
    }
    @PostMapping("/message")
    public void sendMessage(@RequestBody MessageView messageView){
        messageService.sendMessage(messageView);
    }
}

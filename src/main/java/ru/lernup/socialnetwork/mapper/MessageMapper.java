package ru.lernup.socialnetwork.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.socialnetwork.Db.Entity.Message;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.UserRepository;
import ru.lernup.socialnetwork.view.MessageView;

import java.util.Date;

@Component
public class MessageMapper {
    private final PersonRepository personRepository;

    public MessageMapper( PersonRepository personRepository) {
        this.personRepository = personRepository;

    }
    public Message mappedFromView(MessageView messageView){
        Message message = new Message();
        Date date = new Date();
        message.setDate(date.toString());
        message.setText(messageView.getText());
        message.setIdAuthor(personRepository.getReferenceById(messageView.getIdAuthor()));
        message.setIdRecipient(personRepository.getReferenceById(messageView.getIdRecipient()));
        return message;
    }
    public MessageView mappedToView(Message message){
        MessageView messageView = new MessageView();
        messageView.setDate(message.getDate());
        messageView.setText(message.getText());
        messageView.setIdAuthor(message.getIdAuthor().getId());
        messageView.setIdRecipient(message.getIdRecipient().getId());
        return messageView;
    }
}

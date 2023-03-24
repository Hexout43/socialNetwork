package ru.lernup.socialnetwork.service;

import org.springframework.stereotype.Service;
import ru.lernup.socialnetwork.Db.Repository.MessageRepository;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.UserRepository;
import ru.lernup.socialnetwork.mapper.MessageMapper;
import ru.lernup.socialnetwork.view.MessageView;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final PersonRepository personRepository;

    public MessageService(MessageRepository messageRepository,
                          MessageMapper messageMapper,
                           PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.personRepository = personRepository;

    }
    public void sendMessage(MessageView messageView){
        messageRepository.save(messageMapper.mappedFromView(messageView));
    }
    public List<MessageView> getAllMessageByRecipient(Long id){
       return messageRepository.getMessagesByIdRecipient(personRepository.getReferenceById(id))
                .stream().map(messageMapper::mappedToView).collect(Collectors.toList());
    }
    public List<MessageView> gerAllMessageByAuthor(Long id){
        return messageRepository.getMessagesByIdAuthor(personRepository.getReferenceById(id))
                .stream().map(messageMapper::mappedToView).collect(Collectors.toList());
    }

}

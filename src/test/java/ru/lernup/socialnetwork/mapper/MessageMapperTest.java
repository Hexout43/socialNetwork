package ru.lernup.socialnetwork.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lernup.socialnetwork.Db.Entity.Message;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Entity.User;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.view.MessageView;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MessageMapperTest {
    MessageView messageView;
    Message message;
    Person author;
    Person recipient;
    @Mock
    PersonRepository personRepository;
    @InjectMocks
    MessageMapper messageMapper;
    void createViewForTest(){
        messageView = new MessageView();
        messageView.setText("123");
        messageView.setIdAuthor(1L);
        messageView.setIdRecipient(2L);
        }
        void createPersonsForTest(){
            User user = new User();
            user.setId(1L);
            User user1 = new User();
            user.setId(2L);
            author = new Person();
            author.setId(1L);
            author.setUser(user);
            recipient = new Person();
            recipient.setId(2L);
            recipient.setUser(user1);
        }

    @Test
    void mappedFromView_WhereContentIsNull_ReturnValidEntity(){
        //given
        createViewForTest();
        createPersonsForTest();
        Mockito.doReturn(author).when(this.personRepository).getReferenceById(1L);
        Mockito.doReturn(recipient).when(this.personRepository).getReferenceById(2L);
    MessageView messageView1 = messageView;
    //when
        var message = messageMapper.mappedFromView(messageView1);
        //then
        assertEquals(message.getText(),messageView1.getText());
        assertEquals(message.getIdAuthor().getId(),messageView1.getIdAuthor());
        assertEquals(message.getIdRecipient().getId(),messageView1.getIdRecipient());

    }
    void createEntityForTest(){
        createPersonsForTest();
        message = new Message();
        message.setIdRecipient(recipient);
        message.setIdAuthor(author);
        message.setText("Hello");

    }
    @Test
    void mappedToView_ReturnValidView(){
        //given
        createEntityForTest();
        Date date = new Date();
        message.setDate(date.toString());
        //when
        var messageViews = messageMapper.mappedToView(message);
        //then
        assertEquals(messageViews.getIdAuthor(),message.getIdAuthor().getUser().getId());
        assertEquals(messageViews.getText(),message.getText());
        assertEquals(messageViews.getIdRecipient(),message.getIdRecipient().getUser().getId());
        assertEquals(messageViews.getDate(),date.toString());
    }

}
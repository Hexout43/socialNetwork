package ru.lernup.socialnetwork.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.service.PersonService;
import ru.lernup.socialnetwork.view.PersonView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FriendMapperTest {
    @Mock
    PersonService personService;
    @InjectMocks
    FriendMapper friendMapper;

    @Test
    void generateFriend() {
        //given
        Person person = new Person();
        person.setId(1L);
        PersonView person1 = new PersonView();
        person1.setIdUser(1L);
        PersonView person2 = new PersonView();
        person2.setIdUser(2L);
        PersonView person3 = new PersonView();
        person3.setIdUser(3L);
        person.setFriends("1 2 3");
        Mockito.doReturn(person1).when(this.personService).getPersonById(1L);
        Mockito.doReturn(person2).when(this.personService).getPersonById(2L);
        Mockito.doReturn(person3).when(this.personService).getPersonById(3L);
        //when
        var friend = friendMapper.generateFriend(person);
        //then
        assertEquals(friend.getIdPerson(),person.getId());
        assertArrayEquals(friend.getFriends().toArray(), List.of(person1,person2,person3).toArray());
    }
}
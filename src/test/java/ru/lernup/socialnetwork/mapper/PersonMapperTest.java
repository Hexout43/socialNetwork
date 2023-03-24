package ru.lernup.socialnetwork.mapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Entity.User;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.UserRepository;
import ru.lernup.socialnetwork.view.PersonView;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PersonMapperTest {
@Mock
    UserRepository userRepository;
@Mock
    PersonRepository personRepository;
@InjectMocks
    PersonMapper personMapper;

@Test
    void mappedFromView_ReturnValidEntity(){
    //given
    User user = new User();
    user.setId(1L);
    Mockito.doReturn(user).when(this.userRepository).getReferenceById(1L);
    PersonView personView = new PersonView();
    personView.setId(1L);
    personView.setName("123");
    personView.setMail("1234");
    personView.setSurname("Mike");
    //when
    var person =
    personMapper.mappedFromView(personView);
    //then
    assertNotNull(person.getUser());
    assertEquals(personView.getId(),person.getUser().getId());
    assertNotNull(person);
    assertEquals(person.getBirthdate(),personView.getBirthDate());
    assertEquals(person.getSurname(),personView.getSurname());
    assertEquals(person.getBriefInformation(),personView.getBriefInformation());
    assertEquals(person.getName(),personView.getName());
    assertEquals(person.getMail(),personView.getMail());
}
@Test
    void mappedToView_ReturnValidView(){
    //given
    User user = new User();
    user.setId(1L);
    Mockito.doReturn(user).when(this.userRepository).getReferenceById(1L);
    Person person = new Person();
    person.setUser(userRepository.getReferenceById(1L));
    person.setName("123");
    person.setSurname("1234");
    person.setBirthdate("1516");
    person.setBriefInformation("urt");
    //when
    var personView = personMapper.mappedToView(person);
    //then
    assertEquals(personView.getId(),person.getUser().getId());
    assertEquals(personView.getName(),person.getName());
    assertEquals(personView.getSurname(),person.getSurname());
    assertEquals(personView.getBriefInformation(),person.getBriefInformation());
    assertEquals(personView.getBirthDate(),person.getBirthdate());
    assertEquals(personView.getMail(),person.getMail());

}
}
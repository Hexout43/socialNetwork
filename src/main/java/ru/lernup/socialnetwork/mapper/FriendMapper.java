package ru.lernup.socialnetwork.mapper;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.service.PersonService;
import ru.lernup.socialnetwork.view.Friends;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FriendMapper {
    private final  PersonService personService;

    public FriendMapper(PersonService personService) {
        this.personService = personService;
    }

    public Friends generateFriend(Person person){
        Friends friend = new Friends();
        friend.setIdPerson(person.getId());
        if(person.getFriends()!=null){

        List<String> friends = Arrays.stream(person.getFriends().trim().split(" ")).toList();
             friend.setFriends(friends.stream().map(user->{
               return   personService.getPersonById(Long.parseLong(user));
             }).collect(Collectors.toList()));}

             return friend;

    }
}

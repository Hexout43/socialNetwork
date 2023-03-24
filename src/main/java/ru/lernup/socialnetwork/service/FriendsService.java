package ru.lernup.socialnetwork.service;

import org.springframework.stereotype.Service;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.mapper.FriendMapper;
import ru.lernup.socialnetwork.view.Friends;

@Service
public class FriendsService {
   private final FriendMapper friendMapper;

   private final PersonRepository personRepository;

   public FriendsService(FriendMapper friendMapper,

                         PersonRepository personRepository) {
      this.friendMapper = friendMapper;
      this.personRepository = personRepository;
   }

   public void addFriend(Long idPerson,Long idFriend){
      Person person =
       personRepository.getReferenceById(idPerson);
      if(person.getFriends()!=null){
         if (checkFriendInDb(person.getFriends(), idFriend)){
            person.setFriends(person.getFriends() + " " + idFriend);
         }
      }
      else person.setFriends(idFriend.toString().trim());
      personRepository.save(person);
   }
   private boolean checkFriendInDb(String friendString, Long idFriend){
      String[] friends = friendString.trim().split(" ");
      for(String friend:friends){
         if (Long.parseLong(friend)==idFriend){
            return false;
         }
      }
      return true;
   }
   public void deleteFriend(Long idPerson,Long idFriend){
       Person person =
               personRepository.getReferenceById(idPerson);
       person.setFriends(deleteFriendInString(person.getFriends(),idFriend));
       personRepository.save(person);

   }
   private String deleteFriendInString(String friends, Long idFriend){
      StringBuilder builder = new StringBuilder(friends);
      int index= builder.indexOf(idFriend.toString());
      if (index+idFriend.toString().length()!=builder.length()){
      builder.delete(index,index+(idFriend.toString().length())+1);
      }
      else builder.delete(index,builder.length());

      return builder.toString();
   }
   public Friends getAllFriends(Long idPerson){
       return
       friendMapper.generateFriend(personRepository.getReferenceById(idPerson));
   }
}

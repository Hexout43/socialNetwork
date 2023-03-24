package ru.lernup.socialnetwork.mapper;

import org.springframework.stereotype.Component;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.UserRepository;
import ru.lernup.socialnetwork.view.PersonView;

@Component
public class PersonMapper {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;

    public PersonMapper(UserRepository userRepository,
                        PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    public Person mappedFromView(PersonView personView){
        Person person = new Person();
        person.setId(personView.getId());
        person.setName(personView.getName());
        person.setSurname(personView.getSurname());
        person.setBirthdate(personView.getBirthDate());
        person.setBriefInformation(personView.getBriefInformation());
        person.setUser(userRepository.getReferenceById(personView.getIdUser()));
        person.setMail(personView.getMail());
        return  person;
    }
    public PersonView mappedToView(Person person){
        PersonView personView = new PersonView();
        personView.setId(person.getId());
        personView.setName(person.getName());
        personView.setSurname(person.getSurname());
        personView.setBirthDate(person.getBirthdate());
        personView.setBriefInformation(person.getBriefInformation());
        personView.setIdUser(person.getUser().getId());
        personView.setMail(person.getMail());
        return personView;
    }

}

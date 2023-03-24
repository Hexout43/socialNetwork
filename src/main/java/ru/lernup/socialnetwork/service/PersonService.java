package ru.lernup.socialnetwork.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lernup.socialnetwork.Db.Entity.FilePerson;
import ru.lernup.socialnetwork.Db.Entity.Person;
import ru.lernup.socialnetwork.Db.Entity.User;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;
import ru.lernup.socialnetwork.Db.Repository.UserRepository;
import ru.lernup.socialnetwork.mapper.PersonMapper;
import ru.lernup.socialnetwork.view.PersonView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    @Value("${server.port}")
    private String port;

    public PersonService(PersonMapper personMapper,
                          PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;

    }
    public void createPerson(PersonView personView,Long id){

        Person person = personMapper.mappedFromView(personView);
       personRepository.save(person);
    }
    public void updatePerson(PersonView personView){
        Person person = personRepository.getReferenceById(personView.getId());
        Person personIn = personMapper.mappedFromView(personView);
        if (personIn.getName()!=null&&!personIn.getName().equals(person.getName())){
            person.setName(personIn.getName());
        }
        if (personIn.getBriefInformation()!=null&&!personIn.getBriefInformation().equals(person.getBriefInformation())){
            person.setBriefInformation(personIn.getBriefInformation());
        }
        if (personIn.getBirthdate()!=null&&!personIn.getBirthdate().equals(person.getBirthdate())){
            person.setBirthdate(personIn.getBirthdate());
        }
        if (personIn.getSurname()!=null&&!personIn.getSurname().equals(person.getSurname())){
            person.setSurname(personIn.getSurname());
        }
        if (personIn.getMail()!=null&&!personIn.getMail().equals(person.getMail())){
            person.setMail(personIn.getMail());
        }
        personRepository.save(person);
    }
    public PersonView getPersonById(Long id){
        return personMapper.mappedToView(personRepository.getReferenceById(id));
    }
    public List<String> getAllFilePerson(Long id ){
        Person person = personRepository.getReferenceById(id);
        List<FilePerson> nameFiles = person.getFilePeople();
        return
        nameFiles.stream().map(filePerson -> {
          return   "http://localhost:" +port + "/file/img/"+id + "/" +
                  filePerson.getName();
        }).collect(Collectors.toList());
    }

}

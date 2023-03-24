package ru.lernup.socialnetwork.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.service.PersonService;
import ru.lernup.socialnetwork.service.UserService;
import ru.lernup.socialnetwork.view.PersonView;

import java.util.List;

@RestController
@RequestMapping("/person/{login}")
public class PersonController {
    private final PersonService personService;
    private final UserService userService;

    public PersonController(PersonService personService,
                            UserService userService) {

        this.personService = personService;
        this.userService = userService;
    }

    @PostMapping("/create")
    @PreAuthorize("#login==authentication.name")
    public void createPerson(@RequestBody PersonView personView, @PathVariable("login") String login){

        Long id = userService.getUserByLogin(login).getId();
        if(personView.getIdUser()==null){
            personView.setIdUser(id);
            personService.createPerson(personView,id);
        }
        else if (personView.getIdUser().equals(id)) {
            personService.createPerson(personView,id);
        }

    }
    @PutMapping("/update")
    @PreAuthorize("#login==authentication.name")
    public  void updatePerson(@RequestBody PersonView personView,@PathVariable("login") String login){
        Long id = userService.getUserByLogin(login).getId();
        if(personView.getIdUser().equals(id)){
        personService.updatePerson(personView);
        }
    }
    @GetMapping("")
    public PersonView getPerson(@PathVariable("login") String login){
        Long id = userService.getUserByLogin(login).getPerson().getId();
        return personService.getPersonById(id);
    }
    @GetMapping(value = "/files")
    public  List<String> getAllFilePerson(@PathVariable("login") String login){
        Long id = userService.getUserByLogin(login).getPerson().getId();
        return  personService.getAllFilePerson(id);
    }


}

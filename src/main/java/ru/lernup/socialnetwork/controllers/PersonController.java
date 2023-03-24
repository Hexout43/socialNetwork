package ru.lernup.socialnetwork.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.lernup.socialnetwork.service.PersonService;
import ru.lernup.socialnetwork.view.PersonView;
import ru.lernup.socialnetwork.view.Verification;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/{id}/create")
    public void createPerson(@RequestBody PersonView personView, @PathVariable("id") Long id){
        personService.createPerson(personView,id);
    }
    @PutMapping("/{id}/update")
    public  void updatePerson(@RequestBody PersonView personView){
        personService.updatePerson(personView);
    }
    @GetMapping("/{id}")
    public PersonView getPerson(@PathVariable("id")Long id){
        return personService.getPersonById(id);
    }
    @GetMapping(value = "/{id}/files")
    public  List<String> getAllFilePerson(@PathVariable("id") Long id){
        return  personService.getAllFilePerson(id);
    }
    private boolean checkMail(PersonView personView){
        return personView.getMail()!=null&&
        !personService.getPersonById(personView.getId()).getMail().equals(personView.getMail());
    }

}

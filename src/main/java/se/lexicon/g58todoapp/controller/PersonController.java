package se.lexicon.g58todoapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.g58todoapp.entity.Person;
import se.lexicon.g58todoapp.repo.PersonRepository;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/hello")
    public String hello () {
        return "Hello from PersonController!";
    }

    @RequestMapping("/people")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }





}

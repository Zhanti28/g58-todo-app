package se.lexicon.g58todoapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.lexicon.g58todoapp.dto.PersonDto;
import se.lexicon.g58todoapp.entity.Person;
import se.lexicon.g58todoapp.exception.PersonNotFoundException;
import se.lexicon.g58todoapp.repo.PersonRepository;
import se.lexicon.notify.model.Email;
import se.lexicon.notify.service.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonService {

    // TODO: Expand Service Layer methods

    PersonRepository personRepository;
    MessageService<Email> messageService;

    public PersonService(PersonRepository personRepository, MessageService<Email> messageService) {
        this.personRepository = personRepository;
        this.messageService = messageService;
    }

    public void createPerson(PersonDto personDto) {

        Person person = new Person(personDto.name(), personDto.email(),personDto.birthDate());


        person = personRepository.save(person);


        /*if (person.getId() != null){
            boolean sentMessage = messageService.sendMessage(new Email(
                    person.getEmail(),
                    "Welcome 🫡",
                    """
                            Hello, %s
                            Thank you for signing up to our App.
                            We hope you enjoy using it. 🎉
                            """.formatted(person.getName())));

            if (!sentMessage){
                log.error("Failed to send welcome email to: {}", person.getEmail());
            }else{
                log.info("Successfully sent welcome email to: {}",person.getEmail());
            }
        }*/
    }


    // Finding all from data storage and Converting to DTO
    public List<PersonDto> findAll(){
        return personRepository.findAll().stream()
                .map(person -> PersonDto.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .email(person.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    public PersonDto findById(Long id){
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Person not found"));
        return  PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .birthDate(person.getBirthDate())
                .build();
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
    public PersonDto update(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        person.setName(personDto.name());
        person.setEmail(personDto.email());
        Person updated = personRepository.save(person);
        log.info("Updated person: {}", updated);

        return PersonDto.builder()
                .id(updated.getId())
                .name(updated.getName())
                .email(updated.getEmail())
                .build();
    }
    public PersonDto findByEmail(String email) {

        Person person = personRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .build();
    }


}

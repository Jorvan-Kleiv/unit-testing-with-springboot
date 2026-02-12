package dev.jorvan.testing.services;

import dev.jorvan.testing.models.Person;
import dev.jorvan.testing.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person with id " + id + " not found"));
    }
    public Person save(Person person) {
        return personRepository.save(person);
    }
    public Person update(Long id, Person person) {
        Person existing = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person with id " + id + " not found"));
        existing.setFirstName(person.getFirstName());
        existing.setLastName(person.getLastName());
        existing.setCity(person.getCity());
        existing.setPhoneNumber(person.getPhoneNumber());
        return personRepository.save(existing);
    }
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}

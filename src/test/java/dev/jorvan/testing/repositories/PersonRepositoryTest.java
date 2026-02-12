package dev.jorvan.testing.repositories;

import dev.jorvan.testing.models.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("SHOULD RETURN ALL PERSON")
    void shouldReturnAllPersons() {
        List<Person> persons = personRepository.findAll();
        assertNotNull(persons);
        assertEquals(4, persons.size());
    }
    @Test
    @DisplayName("SHOULD RETURN PERSON BY ID")
    void shouldReturnPersonById() {
        Optional<Person> person = personRepository.findById(1L);
        assertNotNull(person);
        assertTrue(person.isPresent());
        assertEquals("Jorvan", person.get().getFirstName());
    }
    @Test
    @DisplayName("SHOULD RETURN A CREATED USER")
    void shouldReturnCreatedUser() {
        Person person = new Person();
        person.setFirstName("Markus");
        person.setLastName("Kane");
        person.setCity("Berlin");
        person.setPhoneNumber("123-456-7890");
        Person saved = personRepository.save(person);
        assertNotNull(saved);
        assertEquals("Markus", saved.getFirstName());
        assertEquals("Kane", saved.getLastName());
        assertEquals("Berlin", saved.getCity());
        assertEquals("123-456-7890", saved.getPhoneNumber());
    }
    @Test
    @DisplayName("SHOULD DELETE PERSON")
    void shouldDeletePerson() {
        personRepository.deleteById(1L);
        assertFalse(personRepository.findById(1L).isPresent());
    }


}
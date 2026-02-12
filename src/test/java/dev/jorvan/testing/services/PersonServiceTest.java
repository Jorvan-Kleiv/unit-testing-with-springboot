package dev.jorvan.testing.services;

import dev.jorvan.testing.models.Person;
import dev.jorvan.testing.repositories.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    @Test
    @DisplayName("SHOULD RETURNS ALL USERS")
    void shouldDisplayAllPersons() {
        Person person2 = new Person(2L, "Jane", "Smith", "Lyon", "+33987654321");
        Person person3 = new Person(3L, "Bob", "Johnson", "Marseille", "+33555555555");
        Person person1 = new Person(1L, "John", "Doe", "Paris", "+33123456789");
        Person person4 = new Person(4L, "Alice", "Williams", "Toulouse", "+33666666666");
        when(personRepository.findAll()).thenReturn(List.of(person1, person2, person3, person4));

        List<Person> persons = personService.findAll();
        assertThat(persons).hasSize(4).containsExactly(person1, person2, person3, person4);
    }
    @Test
    @DisplayName("SHOULD RETURN USER BY ID")
    void shouldFindPersonById() {
        Person person = new Person(1L, "John", "Doe", "Paris", "+33-123-456-789");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        assertThat(personService.findById(1L)).isEqualTo(person);
    }
    @Test
    @DisplayName("SHOULD RETURN CREATED USER")
    void shouldReturnCreatedPerson() {
        Person person = new Person(1L,"Jane", "Doe", "Birmingham", "+33-123-456-789");
        when(personRepository.save(person)).thenReturn(person);
        assertThat(personService.save(person)).isEqualTo(person);
    }
    @Test
    @DisplayName("SHOULD RETURN UPDATE USER")
    void shouldUpdatePerson() {
        Person person = new Person(1L, "John", "Down", "Paris", "+33-123-456-789");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);
        assertThat(personService.update(1L, person)).isEqualTo(person);
    }
    @Test
    @DisplayName("SHOULD DELETE USER")
    void shouldDeletePerson() {
        personService.delete(1L);
        verify(personRepository).deleteById(1L);
    }
}

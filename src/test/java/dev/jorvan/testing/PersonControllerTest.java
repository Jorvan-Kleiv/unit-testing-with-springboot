package dev.jorvan.testing;

import dev.jorvan.testing.models.Person;
import dev.jorvan.testing.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PersonService personService;

    @Test
    void shouldGetAllPersons() throws Exception {
        Person person2 = new Person(2L, "Jane", "Smith", "Lyon", "+33987654321");
        Person person3 = new Person(3L, "Bob", "Johnson", "Marseille", "+33555555555");
        Person person1 = new Person(1L, "John", "Doe", "Paris", "+33123456789");
        Person person4 = new Person(4L, "Alice", "Williams", "Toulouse", "+33666666666");
        when(personService.findAll()).thenReturn(List.of(person1, person2, person3, person4));

        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }
    @Test
    void shouldGetPersonById() throws Exception {
        Person person1 = new Person(1L, "John", "Doe", "Paris", "+33123456789");
        when(personService.findById(1L)).thenReturn(person1);
        mockMvc.perform(get("/api/persons/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(person1.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person1.getLastName()))
                .andExpect(jsonPath("$.city").value(person1.getCity()));
    }
    @Test
    void shouldDeletePersonById() throws Exception {
        personService.delete(1L);
        mockMvc.perform(delete("/api/persons/1"))
                .andExpect(status().isNoContent());
    }
    @Test
    void shouldSavePerson() throws Exception {
        String json = """
                {
                "id": 1,
                "firstName": "John",
                "lastName": "Smith",
                "city": "Lyon",
                "phoneNumber": "+33123456789"
                }
                """;
        Person person1 = new Person(1L, "John", "Smith", "Lyon", "+33123456789");
        when(personService.save(person1)).thenReturn(person1);
        mockMvc.perform(post("/api/persons").contentType(APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }
}
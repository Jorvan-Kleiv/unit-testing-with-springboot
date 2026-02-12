package dev.jorvan.testing;

import dev.jorvan.testing.models.Person;
import dev.jorvan.testing.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService service;

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(service.save(person), HttpStatus.CREATED);
    }
    @PutMapping("/person/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
        return ResponseEntity.ok(service.update(id, person));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

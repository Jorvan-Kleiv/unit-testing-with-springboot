# Person Management API - Unit Testing Demo 🧪

This repository features a Spring Boot application focused on the management of person records, specifically designed to demonstrate **robust unit and integration testing** practices for saving and validating data.

## 🚀 Purpose
The main goal of this project is to showcase how to verify the "Save Person" business logic and API endpoints using the modern Java testing ecosystem.

## 🛠️ Tech Stack
* **Java 25**
* **Spring Boot 4.0.2**
* **JUnit 5** & **AssertJ** (Core testing framework)
* **Mockito** (Mocking dependencies)
* **MockMvc** (REST endpoint simulation)
* **H2 Database** (In-memory database for integration tests)

## 🧪 Testing Strategy

The application implements a layered testing approach to ensure reliability:

### 1. Web Layer Testing (Controller)
Using `MockMvc`, we test the REST endpoints without starting a full HTTP server. We verify:
* HTTP Status Codes (e.g., `201 Created`).
* JSON Response structure using `JsonPath`.
* Proper deserialization of request bodies.

### 2. Service Layer Testing
Using `Mockito` to isolate business logic. We stub the repository layer to:
* Ensure the `save()` method is called with the correct parameters.
* Validate data processing before it reaches the database.

## 📋 Code Example: Saving a Person

```java
@Test
void shouldSavePerson() throws Exception {
    // Arrange
    Person savedPerson = new Person(1L, "John", "Smith", "Lyon", "+33123456789");
    when(personService.save(any(Person.class))).thenReturn(savedPerson);

    // Act & Assert
    mockMvc.perform(post("/api/persons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(savedPerson)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.firstName").value("John"));
}
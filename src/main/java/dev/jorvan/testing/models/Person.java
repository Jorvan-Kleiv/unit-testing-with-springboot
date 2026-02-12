package dev.jorvan.testing.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "The first name is mandatory")
    @Size(min = 2, max = 50, message = "The first name length should be between {min} and {max}")
    private String firstName;
    @NotNull(message = "The last name is mandatory")
    @Size(min = 2, max = 50, message = "The last name length should be between {min} and {max}")
    private String lastName;
    @NotNull(message = "The city name is mandatory")
    private String city;
    @NotNull(message = "The phone number is mandatory")
    private String phoneNumber;
}



package model;

import enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Entity
@Table(name = "pessoas")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Setter
    @Column(nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    @Setter
    @Column(nullable = false)
    @NotBlank
    private String password;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Setter
    @Getter
    @NotBlank
    @Size(min = 6, message = "A frase secreta deve ter no minimo 6 caracteres")
    @Column(nullable = false, name = "secret_phrase")
    private String secretPhrase;


    public Person() {}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}

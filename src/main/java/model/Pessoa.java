package model;

import enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    @NotBlank
    private String password;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Pessoa() {}

    public Pessoa(String nome, String email, String password, Role role) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

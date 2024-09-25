package service;

import dto.EmailDTO;
import dto.PersonRequestDTO;
import dto.PersonResponseDTO;
import dto.PersonUpdateDTO;
import exceptions.EmailAlreadyExistsException;
import exceptions.PersonNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;


    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<?> createPerson(@Valid PersonRequestDTO personRequestDTO) {

        if (personRepository.findByEmail(personRequestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }

        Person person = new Person();
        person.setNome(personRequestDTO.getName());
        person.setEmail(personRequestDTO.getEmail());
        person.setRole(personRequestDTO.getRole());

        String hashedPassword = passwordEncoder.encode(personRequestDTO.getPassword());
        person.setPassword(hashedPassword);

        String hashedPhrase = passwordEncoder.encode(personRequestDTO.getSecretPhrase());
        person.setSecretPhrase(hashedPhrase);


        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<?> deleteByEmail(String email) {

        personRepository.findByEmail(email).orElseThrow(() -> new PersonNotFoundException("Pessoa não encontrada com o e-mail: " + email));

        personRepository.deleteByEmail(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Transactional
    public ResponseEntity<?> updatePerson(@Valid PersonUpdateDTO personUpdateDTO) {

        Person person = personRepository.findByEmail(personUpdateDTO.getOldEmail()).orElseThrow(() -> new PersonNotFoundException("Pessoa não encontrada com o e-mail: " + personUpdateDTO.getOldEmail()));

        if (personUpdateDTO.getName() != null) {
            person.setNome(personUpdateDTO.getName());
        }

        if (personUpdateDTO.getNewEmail() != null) {
            if (person.getEmail().equals(personUpdateDTO.getNewEmail())) {
                throw new EmailAlreadyExistsException("O novo e-mail deve ser diferente do e-mail já cadastrado.");
            }

            personRepository.findByEmail(personUpdateDTO.getNewEmail()).ifPresent(p -> {
                throw new EmailAlreadyExistsException("Email já cadastrado");
            });
            person.setEmail(personUpdateDTO.getNewEmail());
        }

        if (personUpdateDTO.getNewPassword() != null) {
            if (!passwordEncoder.matches(personUpdateDTO.getNewPassword(), person.getPassword())) {
                if (personUpdateDTO.getSecretPhrase() == null) {
                    throw new IllegalArgumentException("Para alterar a senha você deve fornecer a frase secreta");
                }
                if (passwordEncoder.matches(personUpdateDTO.getSecretPhrase(), person.getSecretPhrase())) {
                    String hashedPassword = passwordEncoder.encode(personUpdateDTO.getNewPassword());
                    person.setPassword(hashedPassword);

                } else {
                    throw new IllegalArgumentException("Frase secreta incorreta");
                }
            } else {
                throw new IllegalArgumentException("A nova senha deve ser diferente da atual");
            }
        }
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    public ResponseEntity<PersonResponseDTO> readByEmail(EmailDTO emailDTO) {

        Person person = personRepository.findByEmail(emailDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com o e-mail: " + emailDTO.getEmail()));
        PersonResponseDTO personResponseDTO = convertToPessoaResponseDTO(person);
        return ResponseEntity.ok(personResponseDTO);
    }

    private PersonResponseDTO convertToPessoaResponseDTO(Person person) {
        return new PersonResponseDTO(person.getEmail(), person.getNome(), person.getRole());
    }
}

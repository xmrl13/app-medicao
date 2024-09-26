package service;

import dto.EmailDTO;
import dto.UserRequestDTO;
import dto.UserResponseDTO;
import dto.UserUpdateDTO;
import enums.Role;
import exceptions.EmailAlreadyExistsException;
import exceptions.InvalidSecretPhraseException;
import exceptions.UserNotFoundException;
import exceptions.UnauthorizedException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<?> createUser(@Valid UserRequestDTO userRequestDTO) {

        if (getCurrentUserRole().canCreateUser()) {
            if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
                throw new EmailAlreadyExistsException("Email já cadastrado");
            }

            User user = new User();
            user.setNome(userRequestDTO.getName());
            user.setEmail(userRequestDTO.getEmail());
            user.setRole(userRequestDTO.getRole());

            String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
            user.setPassword(hashedPassword);

            String hashedPhrase = passwordEncoder.encode(userRequestDTO.getSecretPhrase());
            user.setSecretPhrase(hashedPhrase);


            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            throw new UnauthorizedException("Você não tem permissão para criar usuários.");
        }
    }

    @Transactional
    public ResponseEntity<?> deleteByEmail(String email) {

        if (getCurrentUserRole().canDeleteUser()) {

            userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Pessoa não encontrada com o e-mail: " + email));

            userRepository.deleteByEmail(email);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            throw new UnauthorizedException("Você não tem autorização para utilizar esse recurso");
        }
    }


    @Transactional
    public ResponseEntity<?> updateUser(@Valid UserUpdateDTO userUpdateDTO) {

        if (getCurrentUserRole().canUpdateUser()) {

            User user = userRepository.findByEmail(userUpdateDTO.getOldEmail()).orElseThrow(() -> new UserNotFoundException("Pessoa não encontrada com o e-mail: " + userUpdateDTO.getOldEmail()));

            if (userUpdateDTO.getName() != null) {
                user.setNome(userUpdateDTO.getName());
            }

            if (userUpdateDTO.getNewEmail() != null) {
                if (user.getEmail().equals(userUpdateDTO.getNewEmail())) {
                    throw new EmailAlreadyExistsException("O novo e-mail deve ser diferente do e-mail já cadastrado.");
                }

                userRepository.findByEmail(userUpdateDTO.getNewEmail()).ifPresent(p -> {
                    throw new EmailAlreadyExistsException("Email já cadastrado");
                });
                user.setEmail(userUpdateDTO.getNewEmail());
            }

            if (userUpdateDTO.getNewPassword() != null) {
                if (!passwordEncoder.matches(userUpdateDTO.getNewPassword(), user.getPassword())) {
                    if (userUpdateDTO.getSecretPhrase() == null) {
                        throw new IllegalArgumentException("Para alterar a senha você deve fornecer a frase secreta");
                    }
                    if (passwordEncoder.matches(userUpdateDTO.getSecretPhrase(), user.getSecretPhrase())) {
                        String hashedPassword = passwordEncoder.encode(userUpdateDTO.getNewPassword());
                        user.setPassword(hashedPassword);

                    } else {
                        throw new InvalidSecretPhraseException("Frase secreta incorreta");
                    }
                } else {
                    throw new IllegalArgumentException("A nova senha deve ser diferente da atual");
                }
            }
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } else {
            throw new UnauthorizedException("Você não tem autorização para utilizar esse recurso");
        }
    }

    public ResponseEntity<?> readByEmail(EmailDTO emailDTO) {

        if (getCurrentUserRole().canReadUser()) {
            User user = userRepository.findByEmail(emailDTO.getEmail()).orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com o e-mail: " + emailDTO.getEmail()));
            UserResponseDTO userResponseDTO = convertToUserResponseDTO(user);
            return ResponseEntity.ok(userResponseDTO);
        } else {
            throw new UnauthorizedException("Você não tem autorização para utilizar esse recurso");
        }
    }

    private UserResponseDTO convertToUserResponseDTO(User user) {
        return new UserResponseDTO(user.getEmail(), user.getNome(), user.getRole());
    }


    private Role getCurrentUserRole() {
        return (Role) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}

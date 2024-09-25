package security.service;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.PersonRepository;
import security.auth.AuthRequest;
import security.auth.AuthResponse;
import security.jwt.JwtTokenProvider;
import security.model.CustomUserDetails;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException(""));
        System.out.println("Carregando usuário: " + person.getEmail());
        System.out.println("Role do usuário: " + person.getRole());
        return new CustomUserDetails(person.getEmail(), person.getPassword(), person.getRole());
    }

    public AuthResponse login(AuthRequest authRequest) {

        CustomUserDetails userDetails = (CustomUserDetails) loadUserByUsername(authRequest.getEmail());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        String token = jwtTokenProvider.generateToken(userDetails.getUsername(), userDetails.getRole());

        return new AuthResponse(token);

    }
}


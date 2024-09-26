package security.service;

import model.User;
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
import repository.UserRepository;
import security.auth.AuthRequest;
import security.auth.AuthResponse;
import security.jwt.JwtTokenProvider;
import security.model.CustomUserDetails;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException(""));
        System.out.println("Carregando usuário: " + user.getEmail());
        System.out.println("Role do usuário: " + user.getRole());
        return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRole());
    }

    public AuthResponse login(AuthRequest authRequest) {

        CustomUserDetails userDetails = (CustomUserDetails) loadUserByUsername(authRequest.getEmail());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        String token = jwtTokenProvider.generateToken(userDetails.getUsername(), userDetails.getRole());

        return new AuthResponse(token);

    }
}


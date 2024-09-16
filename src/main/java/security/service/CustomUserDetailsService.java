package security.service;

import model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.PessoaRepository;
import security.auth.AuthRequest;
import security.auth.AuthResponse;
import security.jwt.JwtTokenProvider;
import security.model.CustomUserDetails;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Carrega o usuário a partir do repositório
        Pessoa pessoa = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        // Cria e retorna uma instância de CustomUserDetails
        return new CustomUserDetails(pessoa.getEmail(), pessoa.getPassword(), pessoa.getRole());
    }

    public AuthResponse login(AuthRequest authRequest) {
        try {
            // Autenticar o usuário
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            // Carregar os detalhes do usuário
            CustomUserDetails userDetails = (CustomUserDetails) loadUserByUsername(authRequest.getEmail());

            // Gerar o token JWT com base no nome de usuário e role
            String token = jwtTokenProvider.generateToken(userDetails.getUsername(), userDetails.getRole());

            return new AuthResponse(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Erro ao autenticar usuário", e);
        }
    }
}

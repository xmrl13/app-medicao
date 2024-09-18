package security.jwt;

import enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role.getRoleName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(key)
                .compact();
    }

    // Extrai o username (subject) do token
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Método para validar se o token é válido (não expirado e com username válido)
    public boolean validateToken(String token) {
        return (getUsernameFromToken(token) != null && !isTokenExpired(token));
    }

    // Verifica se o token expirou
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrai a data de expiração do token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Método genérico para extrair qualquer claim do token
    public <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

}

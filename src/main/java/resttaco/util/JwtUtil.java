package resttaco.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${tokenSignature}")
    private String signature;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(signature)
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractOneClaim(String token, Function<Claims, T> claimResolver) {
        Claims allClaims = extractAllClaims(token);
        return claimResolver.apply(allClaims);
    }

    public String extractUsername(String token) {
        return extractOneClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractOneClaim(token, Claims::getExpiration);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !extractExpiration(token).before(new Date()));
    }
}

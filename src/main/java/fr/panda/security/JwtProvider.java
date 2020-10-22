        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.panda.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import javax.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author gtu
 */
@Service
public class JwtProvider {
    
    private Key key;
    
    @PostConstruct
    public void init(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
    
    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername())
                .signWith(key).compact();
    }
    
    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
}

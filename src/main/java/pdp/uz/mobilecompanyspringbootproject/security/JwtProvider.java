package pdp.uz.mobilecompanyspringbootproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import pdp.uz.mobilecompanyspringbootproject.entity.Role;

import java.util.Date;
import java.util.Set;


@Component
public class JwtProvider {

    private static final long expireTime = 1000*360*98;
    private static final String secretSuz = "maxfiysuzHechKimBilmasin";

    public String generateToken(String username, Set<Role> roles){
        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS256, secretSuz)
                .compact();
        return token;

    }

    public boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretSuz)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getEmailFromToken(String token){
        try {
            String email = Jwts
                    .parser()
                    .setSigningKey(secretSuz)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;
        }catch (Exception e){
            return null;
        }
    }

}

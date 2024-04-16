package com.user.jwt;
import java.util.Date;


import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component 
public class JWTTokenHelper { 
	

  
    long JWT_TOKEN_VALIDITY_MILLIS =  5 * 60000; // 5 mins 
    String secret = "fasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADX"; 
 
    //retrieve username from jwt token 
    public String getUsernameFromToken(String token) { 
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject(); 
    } 
 
    //check if the token has expired 
    private Boolean isTokenExpired(String token) { 
     final Date expiration = Jwts.parser()
    		 .setSigningKey(secret)
    		 .parseClaimsJws(token)
    		 .getBody()
    		 .getExpiration(); 
     
     return expiration.before(new Date());

    } 
 
    //generate JWT using the HS512 algorithm and secret key. 
    public String generateToken(String userName) { 
return Jwts 
.builder() 
.setSubject(userName) 
.setIssuedAt(new Date(System.currentTimeMillis())) 
               .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_MILLIS)) 
               .signWith(SignatureAlgorithm.HS512, secret) 
 .compact(); 
    } 
 
    //validate token i.e. user name and time interval validation. 
    public Boolean validateToken(String token, String userName) { 
        final String username = getUsernameFromToken(token); 
        return (username.equals(userName) && !isTokenExpired(token)); 
    } 
} 



package com.petsuite.security;

import com.petsuite.Services.model.JwtAuthenticationToken;
import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
        super("/api/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        
      //  try{
            String header = httpServletRequest.getHeader("Authorization");

        if (header == null || !header.startsWith("Token "))
        {System.out.println("el token no tiene algo");
            throw new RuntimeException("JWT Token is missing");
        }  

    

        String authenticationToken = header.substring(6);

        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        System.out.println("el token  tiene algo lolxd");
        Authentication myFilter= getAuthenticationManager().authenticate(token);
            System.out.println("nuestras myFilter"+ myFilter);
        return getAuthenticationManager().authenticate(token);
         //}
         
         /* catch (io.jsonwebtoken.ExpiredJwtException ex) {
        System.out.println("esta expirado");
        httpServletRequest.setAttribute("expired", ex.getMessage());
        
    }*/
         
        
        
      
   
        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}

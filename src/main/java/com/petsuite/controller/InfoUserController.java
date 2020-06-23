package com.petsuite.controller;

import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.services.GetAllDataService;
import com.petsuite.Services.services.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://petsuite.netlify.app", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS})
public class InfoUserController {

    @Autowired
	GetAllDataService getAllData;

    @Autowired
    LoginService loginService;

    @GetMapping("/all")
    public List<InfoUser> getAllUsers() { return getAllData.getAllUsers(); }
    
    
     @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object clientLogin(final InfoUser_Dto user, final HttpServletRequest request){
        return user;

   /* @RequestMapping("/login")
    @ResponseBody
    @CrossOrigin(origins = "https://petsuite.netlify.app", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS})
    public Object clientLogin(@Valid @RequestBody InfoUser_Dto user){ */
        
        //return loginService.clientLogin(user); 
    }

    private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
       


 


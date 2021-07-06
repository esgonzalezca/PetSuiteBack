package com.petsuite.controller;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.InfoUser_Dto;
import com.petsuite.Services.model.InfoUser;
import com.petsuite.Services.model.ReCaptcha;
import com.petsuite.Services.services.GetAllDataService;
import com.petsuite.Services.services.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://petsuite.netlify.app", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS})
public class InfoUserController {

	@Value("${recaptcha.secret}")
	private String recaptchaSecret;

	@Value("${recaptcha.url}")
	private String recaptchaServerURL;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Autowired
	public RestTemplate restTemplate;

    @Autowired
	GetAllDataService getAllData;

    @Autowired
    LoginService loginService;

    @GetMapping("/all")
    public List<InfoUser> getAllUsers() { return getAllData.getAllUsers(); }

	@RequestMapping(value = "/captcha", method = RequestMethod.POST)
	@ResponseBody
	public Boolean clientcaptcha(@RequestBody Cadena token, final HttpServletRequest request){

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("secret",recaptchaSecret);
		map.add("response",token.getCadena());

		HttpEntity<MultiValueMap<String,String>> requested = new HttpEntity<>(map,headers);

		System.out.println("------------------captcha-------------------------");
		System.out.println(token.getCadena());
		System.out.println("-------------------------------------------");

		ReCaptcha response = restTemplate.postForObject(recaptchaServerURL, requested, ReCaptcha.class);

    	System.out.println("------------------captcha-------------------------");
		System.out.println(response);
		System.out.println(response.isSuccess());
		System.out.println("-------------------------------------------");

    	return response.isSuccess();

	}
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object clientLogin( @RequestBody InfoUser_Dto user, final HttpServletRequest request){
        

   /* @RequestMapping("/login")
    @ResponseBody
    @CrossOrigin(origins = "https://petsuite.netlify.app", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS})
    public Object clientLogin(@Valid @RequestBody InfoUser_Dto user){ */
        
        return loginService.clientLogin(user); }

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
       


 


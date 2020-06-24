package com.petsuite.controller;

import com.petsuite.Services.basics.Flotante;
import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.services.*;
import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.basics.CadenaDoble;
import com.petsuite.Services.model.Client;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.model.WalkPetition;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/clients")
@CrossOrigin(origins = "https://petsuite.netlify.app", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS})
public class ClientController {

    @Autowired
    SearchDogDayCareService searchDogDayCare;
    
    @Autowired
    ShowInvoiceDogCareService showInvoiceDogCare;

    @Autowired
    UpdateService updateService;

    @Autowired
    RegisterService registerService;

    @Autowired
    GetAllDataService getAllData;

    @Autowired
    FindDogService findDogService;

    @Autowired
    GetServicesService getServicesService;

    @Autowired
    RequestPetitionService requestPetitionService;
    
    @Autowired
    ShowInvoiceDogCareService showInvoiceDogCareService;


    @GetMapping("/all")
    public List<Client> getAllClients() {
        System.out.println("Get sin token");
        return getAllData.getAllClients(); }

    @PostMapping("/seeEndedCares")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public @ResponseBody
        List<DogDayCareInvoice_Dto> showInovicesByStatus(@Valid @RequestBody CadenaDoble cadena) { return showInvoiceDogCare.showInovicesByStatus(cadena); }

    @PostMapping("/load")//Retorna una estructura de tipo client vacia si ya esta utilizado el nombre de usuario
    public Client_Dto createClient(@Valid @RequestBody Client_Dto client) { 
        
        return registerService.createClient(client); }

    
    @RequestMapping(value = "/dogList", method = RequestMethod.POST)
    @ResponseBody
    public List<Dog> myDogList(final Cadena user, final HttpServletRequest request){
    
    
   
        System.out.println("Post sin tokens");
        return findDogService.myDogList(user);
    }

    @GetMapping("/allDogDayCares")
    public List<DogDayCare_Dto> getAllDogDayCares() { return getAllData.getAllDogDayCares(); }
    
    @GetMapping("/myServicesAvailables")
    public List<DogDaycareService> getMyServices(@RequestParam(value = "user") String user) { return getServicesService.getMyServices(user); }

    @PostMapping("/mypetition")
    public List<WalkPetition> myPetition(@Valid @RequestBody String user){ return requestPetitionService.myPetition(user); }

    @PostMapping("/update")
    public Client_Dto updateAll(@Valid @RequestBody Client_Dto user_dto){ return  updateService.UpdateClient(user_dto); }

    @PostMapping("/searchdaycarebynameandservice")
    public List<DogDayCare_Dto> searchDayCareByNameAndService(@Valid @RequestBody Cadena name){ return searchDogDayCare.searchDayCareByNameAndService(name); }

    @PostMapping("/searchdaycarebyname")
    public List<DogDayCare_Dto> searchDayCareByName(@Valid @RequestBody Cadena name){ return searchDogDayCare.searchDayCareByName(name); }

    @PostMapping("/searchdaycarebyservice")
    public List<DogDayCare_Dto> searchDayCareByService(@Valid @RequestBody Cadena name){ return searchDogDayCare.searchDayCareByService(name); }

    @PostMapping("/searchdaycarebyscore")
    public List<DogDayCare_Dto> searchDayCareByScore(@Valid @RequestBody Flotante score){ return searchDogDayCare.searchDayCareByScore(score); }

    @PostMapping("/mywalker")
    public DogWalker_Dto walkerInPetition(@Valid @RequestBody Cadena user){ return  requestPetitionService.walkerInPetition(user); }
    
    //para ver las listas que están en Aceptado
    @PostMapping(value = "/CaresListStatus")
    public List<DogDayCareInvoice_Dto> PendingDogList(@Valid @RequestBody CadenaDoble cadenaDoble){ return showInvoiceDogCareService.showInovicesByStatus(cadenaDoble); }
    

    public String getClientJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("CLIENT");

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

        return "Token " + token;
    }

}




package com.petsuite.Services.services;

import com.petsuite.Services.dto.Client_Dto;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.DogWalker_Dto;
import com.petsuite.Services.dto.Dog_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RegisterServiceTest {

    @InjectMocks
    RegisterService registerService;

    @Mock
    InfoUserRepository infoUserRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    DogWalkerRepository dogWalkerRepository;

    @Mock
    DogDaycareRepository dogDaycareRepository;

    @Mock
    DogRepository dogRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createWalker()
    {
        DogWalker_Dto dogWalker_dto = new DogWalker_Dto();

        dogWalker_dto.setUser("htovars");
        dogWalker_dto.setDog_walker_score(3f);
        dogWalker_dto.setDog_walker_phone("3154672289");
        dogWalker_dto.setDog_walker_name("Hubert");
        dogWalker_dto.setDog_walker_e_mail("htovars@unal.edu.co");
        dogWalker_dto.setPassword("123gbjkh43");

        when(infoUserRepository.existsById(anyString())).thenReturn(false);

        when(dogWalkerRepository.save(any())).thenReturn(null);

        assertEquals("Hubert",registerService.createWalker(dogWalker_dto).getDog_walker_name());
    }

    @Test
    void createDogDaycare()
    {
        DogDayCare_Dto dogDayCare_dto = new DogDayCare_Dto();

        dogDayCare_dto.setDog_daycare_name("Cositas");
        dogDayCare_dto.setDog_daycare_e_mail("cositas@gmail.com");
        dogDayCare_dto.setDog_daycare_phone("315666899");
        dogDayCare_dto.setDog_daycare_tax(5f);
        dogDayCare_dto.setDog_daycare_price_base(100f);
        dogDayCare_dto.setUser("cos99");
        dogDayCare_dto.setDog_daycare_type(true);
        dogDayCare_dto.setDog_daycare_score(3.0f);
        dogDayCare_dto.setDog_daycare_address("calle cumbial");
        dogDayCare_dto.setPassword("122easgawfr");

        when(infoUserRepository.existsById(anyString())).thenReturn(false);

        when(dogDaycareRepository.save(any())).thenReturn(null);

        assertEquals("Cositas",registerService.createDogDaycare(dogDayCare_dto).getDog_daycare_name());

    }

    @Test
    void createClient()
    {
        Client_Dto client_dto = new Client_Dto();

        client_dto.setUser("htovars");
        client_dto.setPassword("1234");
        client_dto.setClient_address("casa de la esquina");
        client_dto.setClient_phone("3154672289");
        client_dto.setClient_name("Hubert");
        client_dto.setClient_e_mail("htovars@unal.edu.co");

        when(infoUserRepository.existsById(anyString())).thenReturn(false);

        when(clientRepository.save(any())).thenReturn(null);

        assertEquals("Hubert",registerService.createClient(client_dto).getClient_name());
    }

    @Test
    void createDog()
    {

        Dog_Dto dog_dto = new Dog_Dto();

        dog_dto.setDog_name("Laika");
        dog_dto.setDog_id(2);
        dog_dto.setDog_age(10);
        dog_dto.setDog_height(40f);
        dog_dto.setDog_weight(50f);
        dog_dto.setDog_notes("loca");
        dog_dto.setDog_race("pincher");
        dog_dto.setClient_id("htovars");

        Dog dog = new Dog();

        dog.setUser("htovars");
        dog.setDog_id(2);
        dog.setDog_age(10);
        dog.setDog_name("Laika");
        dog.setDog_notes("loca");
        dog.setDog_height(40f);
        dog.setDog_weight(50f);
        dog.setDog_race("pincher");

        when(dogRepository.save(any())).thenReturn(dog);

        assertEquals("loca",registerService.createDog(dog_dto).getDog_notes());

    }
}
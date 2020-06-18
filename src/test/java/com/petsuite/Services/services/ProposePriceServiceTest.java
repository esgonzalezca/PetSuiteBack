package com.petsuite.Services.services;

import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.WalkPetition_Dto;
import com.petsuite.Services.model.Dog;
import com.petsuite.Services.model.DogDaycare;
import com.petsuite.Services.model.DogDaycareService;
import com.petsuite.Services.model.WalkPetition;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogDaycareServiceRepository;
import com.petsuite.Services.repository.DogRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ProposePriceServiceTest {

    @InjectMocks
    ProposePriceService proposePriceService;

    @Mock
    DogRepository dogRepository;

    @Mock
    DogDaycareRepository dogDaycareRepository;

    @Mock
    DogDaycareServiceRepository dogDaycareServiceRepository;

    @Mock
    WalkPetitionRepository walkPetitionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void proposePrice()
    {
        WalkPetition_Dto walkPetition_dto = new WalkPetition_Dto();

        walkPetition_dto.setUser("htovars");
        walkPetition_dto.setDog_id(1);
        walkPetition_dto.setPrecio_proposal(500);
        walkPetition_dto.setWalk_petition_id(1);
        walkPetition_dto.setWalk_petition_walker_user("ncontreras");

        WalkPetition petition = new WalkPetition();

        petition.setUser("htovars");
        petition.setWalk_petition_id(1);

        when(walkPetitionRepository.findPetitionsById(anyInt())).thenReturn(petition);

        when(walkPetitionRepository.save(any())).thenReturn(null);

        assertEquals(walkPetition_dto.getWalk_petition_walker_user(),proposePriceService.proposePrice(walkPetition_dto).getWalk_petition_walker_user());
    }

    @Test
    void requestPriceDogDayCareInvoice()
    {
        DogDaycareService daycareService1 = new DogDaycareService();
        daycareService1.setDogdaycare_Service_Name("parque de juegos");
        daycareService1.setDog_daycare_service_id(1);
        daycareService1.setDogdaycare_Service_Price(500);

        DogDaycareService daycareService2 = new DogDaycareService();
        daycareService2.setDogdaycare_Service_Name("ejercicio");
        daycareService2.setDog_daycare_service_id(2);
        daycareService2.setDogdaycare_Service_Price(500);

        Set<DogDaycareService> services =  new HashSet<DogDaycareService>();

        services.add(daycareService1);
        services.add(daycareService2);

        List<Integer> servicesID = new ArrayList<>();

        servicesID.add(daycareService1.getDog_daycare_service_id());
        servicesID.add(daycareService2.getDog_daycare_service_id());

        DogDayCareInvoice_Dto dogDayCareInvoice_dto = new DogDayCareInvoice_Dto();

        dogDayCareInvoice_dto.setDog_daycare_invoice_date("2019-04-28 22:32:38");
        dogDayCareInvoice_dto.setDog_daycare_invoice_price(500f);
        dogDayCareInvoice_dto.setDog_daycare_invoice_duration(5f);
        dogDayCareInvoice_dto.setDog_daycare_invoice_dogdaycare_id("htovars");
        dogDayCareInvoice_dto.setDog_daycare_invoice_dog_id(1);
        dogDayCareInvoice_dto.setDog_daycare_invoice_client_id("ncontreras");
        dogDayCareInvoice_dto.setDog_daycare_invoice_services(servicesID);

        Dog dog = new Dog();

        dog.setUser("ncontreras");
        dog.setDog_id(1);
        dog.setDog_age(20);
        dog.setDog_name("Laika");
        dog.setDog_notes("chiquita");
        dog.setDog_height(10f);
        dog.setDog_weight(30f);
        dog.setDog_race("pincher");

        DogDaycare dogDaycare =  new DogDaycare();

        dogDaycare.setDog_daycare_score(5f);
        dogDaycare.setUser("htovars");
        dogDaycare.setDog_daycare_type(true);
        dogDaycare.setDog_daycare_address("calle la palmita");
        dogDaycare.setDog_daycare_base_price(1000f);
        dogDaycare.setDog_daycare_tax(100f);
        dogDaycare.setDogDaycareServices(services);

        when(dogRepository.findByDogId(anyInt())).thenReturn(dog);

        when(dogDaycareRepository.findById(anyString())).thenReturn(java.util.Optional.of(dogDaycare));

        when(dogDaycareServiceRepository.findById(anyInt())).thenReturn(java.util.Optional.of(daycareService1),java.util.Optional.of(daycareService2));

        assertEquals(java.util.Optional.of(6500f),java.util.Optional.of(proposePriceService.requestPriceDogDayCareInvoice(dogDayCareInvoice_dto).getDog_daycare_invoice_price()));

    }
}
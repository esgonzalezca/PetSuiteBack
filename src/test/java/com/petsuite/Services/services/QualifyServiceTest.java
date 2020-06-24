package com.petsuite.Services.services;

import com.petsuite.Services.basics.Cadena;
import com.petsuite.Services.dto.DogDayCareInvoice_Dto;
import com.petsuite.Services.dto.WalkInvoice_Dto;
import com.petsuite.Services.model.DogWalker;
import com.petsuite.Services.repository.DogDaycareInvoiceRepository;
import com.petsuite.Services.repository.DogDaycareRepository;
import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class QualifyServiceTest {

    @InjectMocks
    QualifyService qualifyService;

    @Mock
    DogDaycareInvoiceRepository dogDaycareInvoiceRepository;

    @Mock
    DogDaycareRepository dogDaycareRepository;

    @Mock
    DogWalkerRepository dogWalkerRepository;

    @Mock
    WalkInvoiceRepository walkInvoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void qualifyDogDayCare()
    {
        DogDayCareInvoice_Dto dogDayCareInvoice_dto = new DogDayCareInvoice_Dto();

        dogDayCareInvoice_dto.setDog_daycare_invoice_date("2019-04-28 22:32:38");
        dogDayCareInvoice_dto.setDog_daycare_invoice_price(500f);
        dogDayCareInvoice_dto.setDog_daycare_invoice_duration(5f);
        dogDayCareInvoice_dto.setDog_daycare_invoice_dogdaycare_id("htovars");
        dogDayCareInvoice_dto.setDog_daycare_invoice_dog_id(1);
        dogDayCareInvoice_dto.setDog_daycare_invoice_client_id("ncontreras");
        dogDayCareInvoice_dto.setDog_daycare_invoice_id(1);
        dogDayCareInvoice_dto.setDog_daycare_invoice_score(5f);

        when(dogDaycareInvoiceRepository.scoreDogDaycare(anyFloat(),anyInt())).thenReturn(1);

        when(dogDaycareInvoiceRepository.scoreAvg(any())).thenReturn(5f);

        when( dogDaycareRepository.updateScore(anyFloat(),anyString())).thenReturn(1);

        assertEquals("Guardería calificada correctamente",qualifyService.qualifyDogDayCare(dogDayCareInvoice_dto).getCadena());
    }

    @Test
    void scoreDogWalker()
    {
        WalkInvoice_Dto walkInvoice_dto = new WalkInvoice_Dto();

        walkInvoice_dto.setClient_id("ncontreras");
        walkInvoice_dto.setDog_id(2);
        walkInvoice_dto.setDog_walker_id("htovars");
        walkInvoice_dto.setWalk_invoice_price(10000f);
        walkInvoice_dto.setWalk_invoice_status("Aceptado");
        walkInvoice_dto.setWalker_score(4f);
        walkInvoice_dto.setWalk_invoice_id(1);

        when( walkInvoiceRepository.scoreWalker(anyFloat(),anyInt())).thenReturn(1);

        when(walkInvoiceRepository.scoreAvg(anyString())).thenReturn(4.5f);

        when(dogWalkerRepository.updateScore(anyFloat(),anyString())).thenReturn(1);

        assertEquals("Paseador calificado correctamente",qualifyService.scoreDogWalker(walkInvoice_dto).getCadena());

    }

    @Test
    void getQualifications()
    {
        Cadena user = new Cadena();

        user.setCadena("htovars");

        Optional<DogWalker> dogWalker = Optional.of(new DogWalker());

        dogWalker.get().setDog_walker_score(5f);
        dogWalker.get().setUser("htovars");

        when(dogWalkerRepository.findById(any())).thenReturn(dogWalker);

        assertEquals(dogWalker.get().getDog_walker_score(),qualifyService.getQualifications(user).getFlotante());
    }
}
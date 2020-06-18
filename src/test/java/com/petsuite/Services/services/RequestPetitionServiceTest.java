package com.petsuite.Services.services;

import com.petsuite.Services.repository.DogWalkerRepository;
import com.petsuite.Services.repository.WalkInvoiceRepository;
import com.petsuite.Services.repository.WalkPetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RequestPetitionServiceTest {

    @InjectMocks
    RequestPetitionService requestPetitionService;

    @Mock
    WalkPetitionRepository walkPetitionRepository;

    @Mock
    DogWalkerRepository dogWalkerRepository;

    @Mock
    WalkInvoiceRepository walkInvoiceRepository;

    @Mock
    CreateInvoiceService createInvoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createPeititon() {
    }

    @Test
    void myPetition() {
    }

    @Test
    void walkerInPetition() {
    }

    @Test
    void deletePetition() {
    }

    @Test
    void denyOrAcceptPetition() {
    }
}
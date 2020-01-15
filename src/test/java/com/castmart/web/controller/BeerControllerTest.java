package com.castmart.web.controller;

import com.castmart.domain.Beer;
import com.castmart.web.model.BeerDTO;
import com.castmart.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private static String RESOURCE = "/api/v1/beer/";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(RESOURCE+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {

        String serialized = objectMapper.writeValueAsString(validBeerDTO());

        mockMvc.perform(
                post(RESOURCE)
                .content(serialized)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        String id = UUID.randomUUID().toString();
        BeerDTO beerDTO = BeerDTO.builder()
                .id(UUID.fromString(id))
                .beerName("Coronita")
                .beerStyle(BeerStyleEnum.LAGER)
                .quantityOnHand(1000)
                .createdDate(OffsetDateTime.now())
                .price(new BigDecimal(16.0))
                .build();

        mockMvc.perform(
                put(RESOURCE+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(beerDTO)))
                .andExpect(status().isNoContent());
    }

//    @Test
//    void testValidationErrorHandler() throws Exception {
//
//        String serialized = objectMapper.writeValueAsString(invalidDTO());
//
//        mockMvc.perform(
//                post(RESOURCE)
//                        .content(serialized)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }

    public BeerDTO validBeerDTO() {
        return BeerDTO.builder()
                .beerName("Coronita")
                .beerStyle(BeerStyleEnum.LAGER)
                .price(new BigDecimal(11.11))
                .upc(881188L)
                .build();
    }

    public BeerDTO invalidDTO() {
        return BeerDTO.builder()
                .beerName("Coronita")
                .beerStyle(BeerStyleEnum.LAGER)
                .price(new BigDecimal(-11.11))
                .upc(881188L)
                .build();
    }
}
package com.castmart.web.controller;

import com.castmart.web.model.BeerDTO;
import com.castmart.web.model.BeerStyleEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@NotNull @PathVariable("beerId") UUID beerId) {
        // TODO impl
        return new ResponseEntity(BeerDTO.builder().id(beerId).beerName("Coronita").beerStyle(BeerStyleEnum.LAGER).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @NotNull @RequestBody BeerDTO beerDTO) {
        BeerDTO dto = BeerDTO.builder().id(UUID.randomUUID()).beerName(beerDTO.getBeerName()).beerStyle(BeerStyleEnum.LAGER).build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/"+dto.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDTO beerDTO) {
        // TODO impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        // TODO impl
    }

}

package com.castmart.bootstrap;

import com.castmart.domain.Beer;
import com.castmart.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository repository) {
        this.beerRepository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .name("Corona")
                    .beerStyle("LAGER")
                    .upc(122333333222111L)
                    .price(new BigDecimal("12.95"))
                    .build()
            );

            beerRepository.save(Beer.builder()
                    .name("Victoria")
                    .beerStyle("PALE_ALE")
                    .upc(122333333222990L)
                    .price(new BigDecimal("13.55"))
                    .build()
            );
        }
        System.out.println("Loaded Beers: " + beerRepository.count());
    }
}

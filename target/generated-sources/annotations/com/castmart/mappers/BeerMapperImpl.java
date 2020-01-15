package com.castmart.mappers;

import com.castmart.domain.Beer;
import com.castmart.domain.Beer.BeerBuilder;
import com.castmart.web.model.BeerDTO;
import com.castmart.web.model.BeerDTO.BeerDTOBuilder;
import com.castmart.web.model.BeerStyleEnum;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-14T19:58:23-0600",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class BeerMapperImpl implements BeerMapper {

    private final DateMapper dateMapper = new DateMapper();

    @Override
    public BeerDTO beerToBeerDTO(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDTOBuilder beerDTO = BeerDTO.builder();

        beerDTO.id( beer.getId() );
        if ( beer.getVersion() != null ) {
            beerDTO.version( beer.getVersion().intValue() );
        }
        beerDTO.createdDate( dateMapper.asOffsetDateTime( beer.getCreatedDate() ) );
        beerDTO.lastModifiedDate( dateMapper.asOffsetDateTime( beer.getLastModifiedDate() ) );
        if ( beer.getBeerStyle() != null ) {
            beerDTO.beerStyle( Enum.valueOf( BeerStyleEnum.class, beer.getBeerStyle() ) );
        }
        beerDTO.upc( beer.getUpc() );
        beerDTO.price( beer.getPrice() );

        return beerDTO.build();
    }

    @Override
    public Beer beerDTOToBeer(BeerDTO beer) {
        if ( beer == null ) {
            return null;
        }

        BeerBuilder beer1 = Beer.builder();

        beer1.id( beer.getId() );
        if ( beer.getVersion() != null ) {
            beer1.version( beer.getVersion().longValue() );
        }
        beer1.createdDate( dateMapper.asTimestamp( beer.getCreatedDate() ) );
        beer1.lastModifiedDate( dateMapper.asTimestamp( beer.getLastModifiedDate() ) );
        if ( beer.getBeerStyle() != null ) {
            beer1.beerStyle( beer.getBeerStyle().name() );
        }
        beer1.upc( beer.getUpc() );
        beer1.price( beer.getPrice() );

        return beer1.build();
    }
}

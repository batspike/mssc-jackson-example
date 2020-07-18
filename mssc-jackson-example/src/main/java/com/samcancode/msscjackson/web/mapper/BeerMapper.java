package com.samcancode.msscjackson.web.mapper;

import org.mapstruct.Mapper;

import com.samcancode.msscjackson.domain.Beer;
import com.samcancode.msscjackson.web.model.BeerDto;

@Mapper(uses= {DateMapper.class})
public interface BeerMapper {
	BeerDto beerToBeerDto(Beer beer);
	Beer    beerDtoToBeer(BeerDto beerDto);
}

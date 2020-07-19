package com.samcancode.msscjackson.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	@Autowired
	ObjectMapper objectMapper;
	

	BeerDto getSampleBeerDto() {
		return BeerDto.builder()
						.beerName("Sample Beer")
						.beerStyle(BeerStyleEnum.ALE)
						.createdDate(OffsetDateTime.now())
						.lastModifiedDate(OffsetDateTime.now())
						.price(new BigDecimal("12.99"))
						.upc(123200001L)
						.build();
	}
}

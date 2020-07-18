package com.samcancode.msscjackson.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samcancode.msscjackson.domain.Beer;
import com.samcancode.msscjackson.repository.BeerRepository;
import com.samcancode.msscjackson.web.mapper.BeerMapper;
import com.samcancode.msscjackson.web.model.BeerDto;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
	private final BeerMapper beerMapper;
	private final BeerRepository beerRepo;
	
	public BeerController(BeerMapper beerMapper, BeerRepository beerRepo) {
		this.beerMapper = beerMapper;
		this.beerRepo = beerRepo;
	}
	
	@GetMapping("/first")
	public ResponseEntity<BeerDto> getFirstBeer() {
		List<Beer> beers = new ArrayList<>();
		beerRepo.findAll().forEach(b -> beers.add(b));
		
		return new ResponseEntity<>(beerMapper.beerToBeerDto(beers.get(0)), HttpStatus.OK);
	}
	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
		Beer beer = beerRepo.findById(beerId).get();
		return new ResponseEntity<>(beerMapper.beerToBeerDto(beer), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
		Beer beer = beerMapper.beerDtoToBeer(beerDto);
		Beer savedBeer = beerRepo.save(beer);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeerById(@PathVariable("beerId") UUID beerId,
												  @Valid @RequestBody BeerDto beerDto) {
		beerRepo.findById(beerId).ifPresent(beer -> {
			beer.setBeerName(beerDto.getBeerName());
			beer.setBeerStyle(beerDto.getBeerStyle().name());
			beer.setPrice(beerDto.getPrice());
			beer.setUpc(beerDto.getUpc());
			
			beerRepo.save(beer);
		});
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{beerId}")
	public ResponseEntity<BeerDto> deleteBeerById(@PathVariable("beerId") UUID beerId) {
		beerRepo.deleteById(beerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}

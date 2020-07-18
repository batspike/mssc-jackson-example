package com.samcancode.msscjackson.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.samcancode.msscjackson.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer,UUID>{

}

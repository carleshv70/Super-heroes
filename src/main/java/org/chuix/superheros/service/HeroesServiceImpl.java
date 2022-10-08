package org.chuix.superheros.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.chuix.superheros.mapper.HeroMapper;
import org.chuix.superheros.model.dto.HeroDto;
import org.chuix.superheros.model.entities.Hero;
import org.chuix.superheros.repositories.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HeroesServiceImpl implements HeroesService {

	@Autowired
	private HeroMapper mapper;
	
	@Autowired
	private HeroesRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Hero> getHeroes() {
		return this.repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Hero getHeroById(Integer id) {
		
		Optional<Hero> optHero = this.repository.findById(id);
		
		return optHero.isPresent()? optHero.get() : null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Hero> getHeroesByName(String name) {
		return this.repository.findAllByNameContainingOrderByName(name);
	}

	@Override
	@Transactional
	public Hero addHero(HeroDto newHeroDto) {
		
		Hero newHero = this.mapper.mapDtoToEntity(newHeroDto);
		
		if (newHero.getId() != null ) {
			throw new IllegalArgumentException("The id field cannot be informed ");
		}
		
		return this.repository.save(newHero);
	}

	@Override
	@Transactional
	public Hero updateHero(Integer id, HeroDto heroDto) {
		
		if (id == null ) {
			throw new IllegalArgumentException("The id param must be informed ");
		}

		
		if (!heroDto.getId().equals(id) ) {
			throw new IllegalArgumentException("The id field does not have a correct value");
		}
		
		Optional<Hero> optHero = this.repository.findById(id);
		
		if (!optHero.isPresent()) {
			throw new IllegalArgumentException("Does not exist any hero with this id");
		}
		
		Hero searchedHero = optHero.get();
		this.mapper.update(searchedHero, heroDto);
		
		return this.repository.save(searchedHero);
	}

	@Override
	@Transactional
	public Hero deleteHero(Integer id) {
		if (id == null ) {
			throw new IllegalArgumentException("The id param must be informed ");
		}
		
		Optional<Hero> optHero = this.repository.findById(id);
		
		if (!optHero.isPresent()) {
			throw new IllegalArgumentException("Does not exist any hero with this id");
		}
		
		// The is a logic delete process so record is not delete, only save delete_at field 
		Hero searchedHero = optHero.get();
		searchedHero.setDeleteAt(LocalDate.now());
		
		
		return this.repository.save(searchedHero);
		//this.repository.delete(searchedHero);
		//return searchedHero;
		
	}

}

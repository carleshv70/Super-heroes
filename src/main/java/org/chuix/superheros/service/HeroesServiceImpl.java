package org.chuix.superheros.service;

import java.util.List;
import java.util.Optional;

import org.chuix.superheros.model.entities.Hero;
import org.chuix.superheros.repositories.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroesServiceImpl implements HeroesService {

	@Autowired
	private HeroesRepository repository;
	
	@Override
	public List<Hero> getHeroes() {
		return this.repository.findAll();
	}

	@Override
	public Hero getHeroById(Long id) {
		
		Optional<Hero> optHero = this.repository.findById(id);
		
		return optHero.isPresent()? optHero.get() : null;
	}

	@Override
	public List<Hero> getHeroesByName(String name) {
		return this.repository.findAllByNameContainingOrderByName(name);
	}

}

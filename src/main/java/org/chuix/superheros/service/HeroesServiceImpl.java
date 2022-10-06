package org.chuix.superheros.service;

import java.util.List;

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
		return this.repository.findById(id).orElse(null);
	}

}

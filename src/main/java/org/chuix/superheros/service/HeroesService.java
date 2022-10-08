package org.chuix.superheros.service;

import java.util.List;

import org.chuix.superheros.model.dto.HeroDto;
import org.chuix.superheros.model.entities.Hero;

public interface HeroesService {
	
	List<Hero> getHeroes();
	List<Hero> getHeroesByName( String criteria);
	Hero getHeroById( Long id);
	Hero addHero( HeroDto newHeroDto);
	Hero updateHero(Integer id, HeroDto heroDto);

}

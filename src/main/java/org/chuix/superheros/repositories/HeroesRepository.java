package org.chuix.superheros.repositories;

import java.util.List;

import org.chuix.superheros.model.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroesRepository extends JpaRepository<Hero, Long> {
	
	List<Hero> findAll();
	

}

package org.chuix.superheros.repositories;

import java.util.List;
import java.util.Optional;

import org.chuix.superheros.model.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroesRepository extends JpaRepository<Hero, Long> {
	
	List<Hero> findAll();
	Optional<Hero> findById(Long id);
	List<Hero> findAllByNameContainingOrderByName(String name);
	
	

}

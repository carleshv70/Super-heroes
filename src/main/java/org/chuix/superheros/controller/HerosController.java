package org.chuix.superheros.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.chuix.superheros.mapper.HeroMapper;
import org.chuix.superheros.model.dto.HeroDto;
import org.chuix.superheros.service.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class HerosController {

	@Autowired
	public HeroesService service;

	@Autowired
	HeroMapper mapper;

	@PostMapping("/heroes/")
	public Map<String, Object> addHero(@RequestBody @Valid HeroDto heroDto) {

		Map<String, Object> response = new HashMap<>();

		try {

			HeroDto newHero = this.mapper.mapEntityToDto(this.service.addHero(this.mapper.mapDtoToEntity(heroDto)));
			response.put("hero", newHero);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The hero has not been save correctly");
		}
		response.put("result", "the new record has been created");
		return response;
	}

	@GetMapping("/heroes/")
	@ResponseStatus(value = HttpStatus.OK)
	public Map<String, Object> getHeroes() {

		Map<String, Object> response = new HashMap<>();

		List<HeroDto> heroes = this.service.getHeroes().stream().map(e -> this.mapper.mapEntityToDto(e))
				.collect(Collectors.toList());

		response.put("heroes:", heroes);
		return response;
	}

	@GetMapping("/heroes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Map<String, Object> getHeroesById(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		HeroDto hero = this.mapper.mapEntityToDto(this.service.getHeroById(id));

		if (hero != null) {
			response.put("hero:", hero);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are not any heroes with this id.");
		}
		return response;
	}

	@GetMapping("/heroes/search")
	@ResponseStatus(value = HttpStatus.OK)
	public Map<String, Object> getHeroesByName(@RequestParam(name = "name") String criteria) {

		Map<String, Object> response = new HashMap<>();

		List<HeroDto> heroes = this.service.getHeroesByName(criteria).stream().map(e -> this.mapper.mapEntityToDto(e))
				.collect(Collectors.toList());

		response.put("heroes:", heroes);
		return response;
	}

}

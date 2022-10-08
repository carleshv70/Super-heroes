package org.chuix.superheros.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.chuix.superheros.mapper.HeroMapper;
import org.chuix.superheros.model.dto.HeroDto;
import org.chuix.superheros.service.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(
	origins = {"http://localhost"},
	methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
	allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class HerosController {

	@Autowired
	public HeroesService service;

	@Autowired
	HeroMapper mapper;
	
	@DeleteMapping("/hero/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Map<String, Object> deleteHero(@PathVariable @NotNull Integer id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			HeroDto deleteHeroDto = this.mapper.mapEntityToDto( this.service.deleteHero(id));
			response.put("deletedHero", deleteHeroDto);
			response.put("result", String.format("The hero with name = %s and id = %d has been delete correctly", deleteHeroDto.getName(), deleteHeroDto.getId()));
		} catch (DataAccessException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the hero has not been delete");
		}
		return   response;
	}
	
	@PutMapping("/heroes/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Map<String, Object> updateHero(@PathVariable Integer id, @RequestBody @Valid HeroDto heroDto) {
		Map<String, Object> response = new HashMap<>();

		try {

			HeroDto newHero = this.mapper.mapEntityToDto(this.service.updateHero(id, heroDto));
			response.put("hero", newHero);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The hero has not been save correctly");
		}
		response.put("result", "the record has been update correctly");
		return response;
	}

	@PostMapping("/heroes/")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Map<String, Object> addHero(@RequestBody @Valid HeroDto heroDto) {

		Map<String, Object> response = new HashMap<>();

		try {

			HeroDto newHero = this.mapper.mapEntityToDto(this.service.addHero(heroDto));
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
	public Map<String, Object> getHeroesById(@PathVariable Integer id) {

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

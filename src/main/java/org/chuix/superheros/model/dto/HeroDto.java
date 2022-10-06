package org.chuix.superheros.model.dto;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class HeroDto implements Serializable {
	
	private Long id;
	private String name;
	private String power;
	private OwnerEnum owner;
	
	private static final long serialVersionUID = 7995822981994942804L;
}

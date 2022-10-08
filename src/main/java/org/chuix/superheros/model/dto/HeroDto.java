package org.chuix.superheros.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class HeroDto implements Serializable {
	
	private Integer id;
	
	@NotNull(message = "The name field has to be informed")
	private String name;
	private String power;
	private OwnerEnum owner;
	
	private static final long serialVersionUID = 7995822981994942804L;
}

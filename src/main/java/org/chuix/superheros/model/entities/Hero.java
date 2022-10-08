package org.chuix.superheros.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "HEROES")
@Where(clause = "delete_at is null ")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero implements Serializable  {
	
	private static final long serialVersionUID = 1287737847037002170L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique = true)
	@NotNull(message =  "This field is required and it cannot be null")
	private String name;
	
	private String power; 
	
	private String owner;

	@Column(name = "create_at")
	private LocalDate createAt;
	
	@Column(name = "delete_at")
	private LocalDate deleteAt;
	
	@PrePersist
	public void prePersist() {
		this.createAt = LocalDate.now();
	}
}

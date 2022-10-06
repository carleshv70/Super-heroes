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

import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Table( name = "HEROES")
@Where(clause = "delete_at is null ")
@Data
public class Hero implements Serializable  {
	
	private static final long serialVersionUID = 1287737847037002170L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name= "NAME", unique = true)
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

package com.jinhyeon.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
	@Id
	private Integer id;
	private String firstName;
	private String lastName;
}

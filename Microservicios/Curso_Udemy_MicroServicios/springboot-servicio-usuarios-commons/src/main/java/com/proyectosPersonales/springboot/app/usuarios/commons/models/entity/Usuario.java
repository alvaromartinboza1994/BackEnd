package com.proyectosPersonales.springboot.app.usuarios.commons.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;// BCrypt

	private Boolean enabled;
	private String nombre;
	private String apellido;

	@Column(unique = true, length = 100)
	private String email;

	@ManyToMany(fetch = FetchType.LAZY) //a medida que se le llamen a los get, se realizan las consultas a la BBDD
	@JoinTable(name="usuarios_roles", joinColumns = @JoinColumn(name="usuario_id"),
		inverseJoinColumns = @JoinColumn(name="rol_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})}) 
	private List<Rol> roles;//se crea usuarios_roles
	
}

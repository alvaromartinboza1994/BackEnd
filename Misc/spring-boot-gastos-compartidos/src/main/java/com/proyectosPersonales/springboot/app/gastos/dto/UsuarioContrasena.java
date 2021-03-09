package com.proyectosPersonales.springboot.app.gastos.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsuarioContrasena {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLogin")
	private Integer idLogin;
	
	@JoinColumn(name = "codUsuario")
    @OneToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@NotNull
	@Column(name = "contraseña")
	private String contraseña;

}

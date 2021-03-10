package com.proyectosPersonales.springboot.app.commons.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class GrupoPK implements Serializable{

	private static final long serialVersionUID = -2098096820389554766L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idGrupo")
	private Integer idGrupo;
	
	@Column(name = "nombreGrupo")
	private String nombreGrupo;

}

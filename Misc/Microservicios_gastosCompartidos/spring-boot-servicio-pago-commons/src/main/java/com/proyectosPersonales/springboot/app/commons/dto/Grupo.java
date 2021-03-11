package com.proyectosPersonales.springboot.app.commons.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Grupo implements Serializable{
	
	private static final long serialVersionUID = -1059451015436510860L;

	@EmbeddedId
	private GrupoPK id;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumns({
		@JoinColumn(name = "idGrupo"), @JoinColumn(name = "nombreGrupo")
	})
	private List<Usuario> participantes;

}

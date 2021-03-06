package com.proyectosPersonales.springboot.app.gastos.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
public class UsuarioPk implements Serializable {

	private static final long serialVersionUID = -5667245328553235366L;

	private Integer idUsuario;

	private String nombre;

	private String apellidos;

}

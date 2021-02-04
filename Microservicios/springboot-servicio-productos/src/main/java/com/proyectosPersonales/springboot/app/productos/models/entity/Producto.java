package com.proyectosPersonales.springboot.app.productos.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable{ //serializable convierte entidad en bytes, para guardar en sesión en HTTP

	private static final long serialVersionUID = 3996009717939666696L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //llave autoincremental
	private Long id;
	
	private String nombre;
	private Double precio;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	public void prueba() {
		Producto producto = new Producto.ProductoBuilder().build();
	}

}

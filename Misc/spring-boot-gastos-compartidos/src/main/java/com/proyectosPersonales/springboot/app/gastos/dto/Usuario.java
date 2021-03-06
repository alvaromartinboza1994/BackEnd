
package com.proyectosPersonales.springboot.app.gastos.dto;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = -6191704983287394829L;

	@EmbeddedId
	private UsuarioPk id;

	/*
	 * @Nullable
	 * 
	 * @OneToMany(mappedBy = "idAmigo")
	 * 
	 * @Column(name = "misAmigos") private List<Amigo> listaAmigos;
	 * 
	 * @Nullable
	 * 
	 * @OneToMany(mappedBy = "pagador", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL)
	 * 
	 * @Column(name = "misPagos") private List<Pago> misPagos;
	 * 
	 * @Nullable
	 * 
	 * @ManyToMany(cascade = { CascadeType.ALL })
	 * 
	 * @JoinTable(name = "Usuario_Pagos", joinColumns = { @JoinColumn(name =
	 * "idUsuario"), @JoinColumn(name = "nombre"), @JoinColumn(name = "apellidos")
	 * }, inverseJoinColumns = { @JoinColumn(name = "idPago") })
	 * 
	 * @Column(name = "misDeudas") private List<Pago> misDeudas;
	 */

	
}

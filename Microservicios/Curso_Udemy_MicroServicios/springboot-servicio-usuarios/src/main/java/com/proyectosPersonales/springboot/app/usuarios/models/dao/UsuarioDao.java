package com.proyectosPersonales.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proyectosPersonales.springboot.app.usuarios.models.entity.Usuario;

@RepositoryRestResource(path="usuarios") //exporta el CRUD repository a un endpoint de nuestra API REST. En path le indicamos el endpoint donde vamos a usar el crud repository
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{ //PagindAndSorting hereda de CrudRepository <clase, tipo clave primaria>
//además permite ordenar y otras características
	public Usuario findByUsername(String username); 

	@Query("select u from Usuario u where u.username =?1") //and u.email=?2
	public Usuario obtenerPorUsername(String username /*, String email*/);
}
